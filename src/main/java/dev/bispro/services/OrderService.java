package dev.bispro.services;

import dev.bispro.dtos.OrderDTO;

import java.util.List;

public interface OrderService {


    OrderDTO findByOrderId(Long orderId);


    OrderDTO createOrder(OrderDTO orderDTO);


    OrderDTO updateOrder(Long orderId, OrderDTO orderDTO);


    void deleteOrder(Long orderId);


    List<OrderDTO> getAllOrders();
}
