package ecse428.backend.dao;

import ecse428.backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderByOrderID(Long orderID);

    List<Order> findOrdersByCustomerEmail(String customerEmail);
}
