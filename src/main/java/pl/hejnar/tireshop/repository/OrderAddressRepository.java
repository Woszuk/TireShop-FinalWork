package pl.hejnar.tireshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.hejnar.tireshop.entity.OrderAddress;

@Repository
public interface OrderAddressRepository extends JpaRepository<OrderAddress, Long> {
}
