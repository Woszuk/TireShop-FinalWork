package pl.hejnar.tireshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.hejnar.tireshop.entity.BasketItem;
import pl.hejnar.tireshop.entity.User;

import java.util.List;

@Repository
public interface BasketItemRepository extends JpaRepository<BasketItem, Long> {

    @Query("SELECT bi FROM BasketItem bi JOIN FETCH bi.product WHERE bi.id=?1")
    BasketItem findBasketItemById(Long id);

    @Query("SELECT bi FROM BasketItem bi JOIN FETCH bi.product WHERE bi.user = ?1")
    List<BasketItem> findBasketItemsByUserId(User user);

    @Modifying
    @Transactional
    @Query("UPDATE BasketItem bi SET bi.quantity = ?1 WHERE bi.id=?2")
    void updateBasketItem (int quantity, Long id);

    @Modifying
    @Transactional
    @Query("UPDATE BasketItem bi SET bi.user = ?1 WHERE bi.id=?2")
    void saveUser (User user, Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM BasketItem b WHERE b.id = ?1")
    void deleteItemFromBasket (Long id);
}
