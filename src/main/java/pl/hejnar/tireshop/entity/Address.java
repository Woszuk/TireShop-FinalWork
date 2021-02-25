package pl.hejnar.tireshop.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 3, message = "Minimum 3 litery")
    @Pattern(regexp = "[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]+", message = "Dozwolone tylko litery")
    private String street;
    @NotEmpty(message = "Pole wymagane")
    @Pattern(regexp = "^[1-9]([0-9]+([a-zA-Z]{1,2})?)|^[1-9]([a-zA-Z]{1,2})?", message = "Podaj prawidłowe dane (np.: 1, 1a)")
    private String numberOfBuilding;
    @Pattern(regexp = "^[a-zA-Z0-9]+|^\\s?", message = "Dozwolone tylko cyfry i litery")
    private String apartmentNumber;
    private String postalCode1;
    private String postalCode2;
    @Size(min = 2, message = "Minimum 2 litery")
    @Pattern(regexp = "[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]+", message = "Dozwolone tylko litery")
    private String city;

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

    public String getPostalCode1() {
        return postalCode1;
    }

    public Address setPostalCode1(String postalCode1) {
        this.postalCode1 = postalCode1;
        return this;
    }

    public String getPostalCode2() {
        return postalCode2;
    }

    public Address setPostalCode2(String postalCode2) {
        this.postalCode2 = postalCode2;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }
}
