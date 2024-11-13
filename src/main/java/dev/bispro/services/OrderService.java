package dev.bispro.services;

import dev.bispro.domain.Order;
import dev.bispro.dtos.OrderDTO;

import java.util.List;

public interface OrderService {


    OrderDTO findByOrderId(Order.OrderId orderId);


    OrderDTO createOrder(OrderDTO orderDTO);


    OrderDTO updateOrder(Order.OrderId orderId, OrderDTO orderDTO);


    void deleteOrder(Order.OrderId orderId);


    List<OrderDTO> getAllOrders();
}
