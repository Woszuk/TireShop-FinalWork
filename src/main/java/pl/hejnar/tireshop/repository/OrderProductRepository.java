package pl.hejnar.tireshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hejnar.tireshop.entity.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
