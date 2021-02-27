package pl.hejnar.tireshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.hejnar.tireshop.entity.Address;
import pl.hejnar.tireshop.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.type=?1")
    List<Product> findAllByType (String type);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.quantity=(p.quantity-?1) WHERE p.id=?2")
    void updateQuantity(int quantity, Long id);
}
