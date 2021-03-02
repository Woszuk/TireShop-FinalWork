package pl.hejnar.tireshop.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@MappedSuperclass
public class Product {
    private String img;
    private String type;
    private String brand;
    private String model;
    private String size;
    private BigDecimal price;
    private int quantity;

    public Product() {

    }

    public Product(String img, String type, String brand, String model, String size, BigDecimal price, int quantity) {
        this.img = img;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
    }

    public String getImg() {
        return img;
    }

    public Product setImg(String img) {
        this.img = img;
        return this;
    }

    public String getType() {
        return type;
    }

    public Product setType(String type) {
        this.type = type;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public Product setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Product setModel(String model) {
        this.model = model;
        return this;
    }

    public String getSize() {
        return size;
    }

    public Product setSize(String size) {
        this.size = size;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }
}
