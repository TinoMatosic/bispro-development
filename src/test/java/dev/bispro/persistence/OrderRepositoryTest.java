package dev.bispro.persistence;

import dev.bispro.domain.Order;
import dev.bispro.domain.exceptions.DataValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    private Order testOrder;

    @BeforeEach
    void setUp() {
        testOrder = new Order(150.0f, 140.0f, new Date(System.currentTimeMillis() + 10000)); // future date
    }

    @Test
    @DisplayName("Test saving an Order")
    void testSaveOrder() {
        Order savedOrder = orderRepository.save(testOrder);
        assertNotNull(savedOrder.getOrderId(), "Saved order should have an ID");
        assertEquals(150.0f, savedOrder.getTotal(), "Order total should match the expected value");
    }

    @Test
    @DisplayName("Test finding an Order by ID")
    void testFindById() {
        Order savedOrder = orderRepository.save(testOrder);
        Order foundOrder = orderRepository.findById(savedOrder.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        assertEquals(savedOrder.getOrderId(), foundOrder.getOrderId(), "Order IDs should match");
        assertEquals(savedOrder.getTotal(), foundOrder.getTotal(), "Order totals should match");
    }

    @Test
    @DisplayName("Test existsById with an existing Order")
    void testExistsById() {
        Order savedOrder = orderRepository.save(testOrder);
        assertTrue(orderRepository.existsById(savedOrder.getOrderId()), "Order should exist in the database");
    }

    @Test
    @DisplayName("Test deleting an Order by ID")
    void testDeleteById() {
        Order savedOrder = orderRepository.save(testOrder);
        Order.OrderId orderId = savedOrder.getOrderId();

        orderRepository.deleteById(orderId);
        assertFalse(orderRepository.existsById(orderId), "Order should not exist after deletion");
    }

    @Test
    @DisplayName("Test finding non-existent Order by ID")
    void testFindByIdNonExistent() {
        assertFalse(orderRepository.findById(new Order.OrderId(999L)).isPresent(), "Non-existent order ID should return empty Optional");
    }

    @Test
    @DisplayName("Test creating an Order with a negative total - should throw DataValidationException")
    void testOrderWithNegativeTotal() {
        Exception exception = assertThrows(DataValidationException.class, () ->
                new Order(-50.0f, 30.0f, new Date(System.currentTimeMillis() + 10000))
        );
        assertEquals("Total cannot be under 0", exception.getMessage(), "Exception message should match");
    }

    @Test
    @DisplayName("Test creating an Order with a negative net - should throw DataValidationException")
    void testOrderWithNegativeNet() {
        Exception exception = assertThrows(DataValidationException.class, () ->
                new Order(50.0f, -30.0f, new Date(System.currentTimeMillis() + 10000))
        );
        assertEquals("Net cannot be under 0", exception.getMessage(), "Exception message should match");
    }

    @Test
    @DisplayName("Test creating an Order with past datetime - should throw IllegalArgumentException")
    void testOrderWithPastDatetime() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Order(50.0f, 40.0f, new Date(System.currentTimeMillis() - 10000))
        );
        assertEquals("Datetime cannot be null or in the past", exception.getMessage(), "Exception message should match");
    }
}
