package pl.hejnar.tireshop.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String description;

    public PaymentMethod() {
    }

    public Long getId() {
        return id;
    }

    public PaymentMethod setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public PaymentMethod setType(String type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PaymentMethod setDescription(String description) {
        this.description = description;
        return this;
    }
}

