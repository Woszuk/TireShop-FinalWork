package pl.hejnar.tireshop.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrderProduct extends Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Order order;

    public OrderProduct() {
    }

    public OrderProduct(String img, String type, String brand, String model, String size, BigDecimal price, int quantity, User user, Order order) {
        super(img, type, brand, model, size, price, quantity);
        this.user = user;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public OrderProduct setId(Long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public OrderProduct setUser(User user) {
        this.user = user;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public OrderProduct setOrder(Order order) {
        this.order = order;
        return this;
    }


}
