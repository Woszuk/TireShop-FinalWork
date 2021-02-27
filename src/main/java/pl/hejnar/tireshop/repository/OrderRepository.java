package pl.hejnar.tireshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.hejnar.tireshop.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
