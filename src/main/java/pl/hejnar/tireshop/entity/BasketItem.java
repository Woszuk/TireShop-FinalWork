package pl.hejnar.tireshop.entity;

import javax.persistence.*;

@Entity
public class BasketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    @ManyToOne
    private ShopProduct product;
    @ManyToOne
    private User user;

    public BasketItem() {
    }

    public Long getId() {
        return id;
    }

    public BasketItem setId(Long id) {
        this.id = id;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public BasketItem setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public ShopProduct getProduct() {
        return product;
    }

    public BasketItem setProduct(ShopProduct product) {
        this.product = product;
        return this;
    }

    public User getUser() {
        return user;
    }

    public BasketItem setUser(User user) {
        this.user = user;
        return this;
    }
}
