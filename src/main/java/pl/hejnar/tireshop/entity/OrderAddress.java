package pl.hejnar.tireshop.entity;

import javax.persistence.*;

@Entity
public class OrderAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String street;
    private String numberOfBuilding;
    private String apartmentNumber;
    private String postalCode;
    private String city;

    public OrderAddress() {

    }

    public long getId() {
        return id;
    }

    public OrderAddress setId(long id) {
        this.id = id;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public OrderAddress setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getNumberOfBuilding() {
        return numberOfBuilding;
    }

    public OrderAddress setNumberOfBuilding(String numberOfBuilding) {
        this.numberOfBuilding = numberOfBuilding;
        return this;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public OrderAddress setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public OrderAddress setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public OrderAddress setCity(String city) {
        this.city = city;
        return this;
    }
}
