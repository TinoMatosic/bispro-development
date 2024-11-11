package dev.bispro.services.impl;

import dev.bispro.domain.Order;
import dev.bispro.dtos.OrderDTO;
import dev.bispro.persistence.OrderRepository;
import dev.bispro.services.OrderService;
import dev.bispro.services.exceptions.ServiceLayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDTO findByOrderId(Long orderId) {
        validateOrderId(orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> ServiceLayerException.notFound("Order not found with ID: " + orderId));
        return toOrderDTO(order);
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        if (orderDTO == null) {
            throw ServiceLayerException.forInvalidArgument("Order cannot be null");
        }
        Order order = new Order(orderDTO.getTotal(), orderDTO.getNet(), orderDTO.getDatetime());
        validateOrder(order); // Validates the order before saving
        try {
            Order savedOrder = orderRepository.save(order);
            return toOrderDTO(savedOrder);
        } catch (Exception e) {
            throw ServiceLayerException.forCreateError("Error creating Order: " + e.getMessage());
        }
    }

    @Override
    public OrderDTO updateOrder(Long orderId, OrderDTO orderDTO) {
        validateOrderId(orderId);
        Optional<Order> existingOrder = orderRepository.findById(orderId);
        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            order.setTotal(orderDTO.getTotal());
            order.setNet(orderDTO.getNet());
            order.setDatetime(orderDTO.getDatetime());
            try {
                Order updatedOrder = orderRepository.save(order);
                return toOrderDTO(updatedOrder);
            } catch (Exception e) {
                throw ServiceLayerException.forUpdateError("Error updating Order: " + e.getMessage());
            }
        } else {
            throw ServiceLayerException.notFound("Order not found with ID: " + orderId);
        }
    }

    @Override
    public void deleteOrder(Long orderId) {
        validateOrderId(orderId);
        try {
            if (!orderRepository.existsById(orderId)) {
                throw ServiceLayerException.notFound("Order not found with ID: " + orderId);
            }
            orderRepository.deleteById(orderId);
        } catch (Exception e) {
            throw ServiceLayerException.forDeleteError("Error deleting Order with ID [" + orderId + "]: " + e.getMessage());
        }
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        try {
            return orderRepository.findAll().stream()
                    .map(this::toOrderDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw ServiceLayerException.forGetError("Error retrieving all Orders: " + e.getMessage());
        }
    }

    private OrderDTO toOrderDTO(Order order) {
        return new OrderDTO(
                order.getOrderId(),
                order.getTotal(),
                order.getNet(),
                order.getDatetime()
        );
    }

    private void validateOrderId(Long orderId) {
        if (orderId == null || orderId < 0) {
            throw ServiceLayerException.forInvalidArgument("Invalid order ID: " + orderId);
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
