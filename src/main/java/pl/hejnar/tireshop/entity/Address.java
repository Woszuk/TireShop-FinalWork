package pl.hejnar.tireshop.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 3)
    @Pattern(regexp = "[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]+")
    private String street;
    @NotEmpty(message = "Pole wymagane")
    @Pattern(regexp = "^[1-9]([0-9]+([a-zA-Z]{1,2})?)|^[1-9]([a-zA-Z]{1,2})?")
    private String numberOfBuilding;
    @Pattern(regexp = "^[a-zA-Z0-9]+|^\\s?")
    private String apartmentNumber;
    @Pattern(regexp = "\\d{2}-\\d{3}")
    private String postalCode;
    @Size(min = 2)
    @Pattern(regexp = "[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]+")
    private String city;
    @OneToOne(mappedBy = "address")
    private User user;


    public long getId() {
        return id;
    }

    public Address setId(long id) {
        this.id = id;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Address setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getNumberOfBuilding() {
        return numberOfBuilding;
    }

    public Address setNumberOfBuilding(String numberOfBuilding) {
        this.numberOfBuilding = numberOfBuilding;
        return this;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public Address setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Address setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Address setUser(User user) {
        this.user = user;
        return this;
    }
}
