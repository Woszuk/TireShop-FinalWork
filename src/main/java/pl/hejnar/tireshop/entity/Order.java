package pl.hejnar.tireshop.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchase_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private BigDecimal totalPrice;
    @ManyToOne
    private User user;
    @OneToMany
    private List<OrderProduct> orderProducts = new ArrayList<>();
    @OneToOne
    private Delivery delivery;
    @OneToOne
    private PaymentMethod paymentMethod;
    @ManyToOne
    private OrderAddress orderAddress;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public Order setId(Long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Order setUser(User user) {
        this.user = user;
        return this;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public Order setDelivery(Delivery delivery) {
        this.delivery = delivery;
        return this;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Order setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    @PrePersist
    public void prePersist() {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String formattedDate = LocalDateTime.now().format(myFormatObj);
        date = formattedDate;
    }

    public String getDate() {
        return date;
    }

    public Order setDate(String date) {
        this.date = date;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Order setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public Order setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
        return this;
    }

    public OrderAddress getOrderAddress() {
        return orderAddress;
    }

    public Order setOrderAddress(OrderAddress orderAddress) {
        this.orderAddress = orderAddress;
        return this;
    }
}
