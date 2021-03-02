package pl.hejnar.tireshop.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class ShopProduct extends Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ShopProduct() {
    }

    public ShopProduct(String img, String type, String brand, String model, String size, BigDecimal price, int quantity) {
        super(img, type, brand, model, size, price, quantity);
    }

    public Long getId() {
        return id;
    }

    public ShopProduct setId(Long id) {
        this.id = id;
        return this;
    }
}
