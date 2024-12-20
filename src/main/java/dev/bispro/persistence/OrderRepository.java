package dev.bispro.persistence;

import dev.bispro.domain.Order;
import dev.bispro.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Order.OrderId> {


}
