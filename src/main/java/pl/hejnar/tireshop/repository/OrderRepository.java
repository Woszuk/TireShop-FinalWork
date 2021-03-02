package pl.hejnar.tireshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.hejnar.tireshop.entity.Order;
import pl.hejnar.tireshop.entity.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.user =?1 ORDER BY id DESC")
    List<Order> findAllByUser (User user);
}
