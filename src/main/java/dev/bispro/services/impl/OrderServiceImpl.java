package dev.bispro.services.impl;

import dev.bispro.domain.Order;
import dev.bispro.persistence.OrderRepository;
import dev.bispro.services.OrderService;
import dev.bispro.services.exceptions.ServiceLayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order findByOrderId(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> ServiceLayerException.notFound("Order not found with ID: " + orderId));
    }

    @Override
    public Order createOrder(Order order) {
        if (order == null) {
            throw ServiceLayerException.forInvalidArgument("Order cannot be null");
        }
        validateOrder(order);
        try {
            return orderRepository.save(order);
        } catch (Exception e) {
            throw ServiceLayerException.forCreateError("Error creating Order: " + e.getMessage());
        }
    }

    @Override
    public Order updateOrder(Long orderId, Order order) {
        validateOrder(order);
        Optional<Order> existingOrder = orderRepository.findById(orderId);
        if (existingOrder.isPresent()) {
            try {
                Order updatedOrder = updateOrderFields(order, existingOrder.get());
                return orderRepository.save(updatedOrder);
            } catch (Exception e) {
                throw ServiceLayerException.forUpdateError("Error updating Order: " + e.getMessage());
            }
        } else {
            throw ServiceLayerException.notFound("Order not found with ID: " + orderId);
        }
    }

    private Order updateOrderFields(Order order, Order existingOrder) {
        existingOrder.setTotal(order.getTotal());
        existingOrder.setNet(order.getNet());
        existingOrder.setDatetime(order.getDatetime());
        return existingOrder;
    }

    @Override
    public void deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw ServiceLayerException.notFound("Order not found with ID: " + orderId);
        }
        try {
            orderRepository.deleteById(orderId);
        } catch (Exception e) {
            throw ServiceLayerException.forDeleteError("Error deleting Order with ID [" + orderId + "]: " + e.getMessage());
        }
    }

    @Override
    public List<Order> getAllOrders() {
        try {
            return orderRepository.findAll();
        } catch (Exception e) {
            throw ServiceLayerException.forGetError("Error retrieving all Orders: " + e.getMessage());
        }
    }

    private void validateOrder(Order order) {
        if (order.getTotal() < 0) {
            throw ServiceLayerException.forInvalidArgument("Total amount cannot be negative");
        }
        if (order.getNet() < 0) {
            throw ServiceLayerException.forInvalidArgument("Net amount cannot be negative");
        }
        if (order.getDatetime() == null || order.getDatetime().before(new Date())) {
            throw ServiceLayerException.forInvalidArgument("Datetime cannot be null or in the past");
        }
    }
}
