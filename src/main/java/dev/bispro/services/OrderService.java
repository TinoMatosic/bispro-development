package dev.bispro.services;

import dev.bispro.domain.Order;

import java.util.List;

public interface OrderService {

    Order findByOrderId(Long orderId);

    Order createOrder(Order order);

    Order updateOrder(Long orderId, Order order);

    void deleteOrder(Long orderId);

    List<Order> getAllOrders();

}
