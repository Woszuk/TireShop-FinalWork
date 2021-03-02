package pl.hejnar.tireshop.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2)
    private String firstName;
    @Size(min = 2)
    private String lastName;
    @Pattern(regexp = "^[A-Za-z0-9]+@[a-z0-9]{2,}(\\.[a-z]{2,})+|[A-Za-z0-9]+\\.[A-Za-z0-9]+@[a-z0-9]{2,}(\\.[a-z]{2,})+")
    @Column(unique = true)
    private String email;
    @Size(min = 3)
    @Column(unique = true)
    @Pattern(regexp = "^[a-zA-Z]([a-zA-Z0-9])+")
    private String username;
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}")
    private String password;
    @Pattern(regexp = "(\\+48\\s?)?([0-9]{3}\\s?){2}[0-9]{3}")
    private String phoneNumber;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Role> roles;
    @OneToOne
    private Address address;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public User setRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public User setAddress(Address address) {
        this.address = address;
        return this;
    }
}
