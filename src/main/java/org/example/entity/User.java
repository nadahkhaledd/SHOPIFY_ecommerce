package org.example.entity;

import org.example.enums.CustomerStatus;
import org.example.enums.Gender;

import javax.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.JoinColumnOrFormula;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity(name="User")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type",
        discriminatorType = DiscriminatorType.INTEGER)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @NotBlank
    @Column(nullable = false, length = 30)
    private String firstName;
    @NotNull
    @Column(nullable = false, length = 30)
    private String lastName;

    @Column
    private int passwordAttempts=0;
    @NotNull
    @Column(nullable = false, unique = true,length = 150)
    private String email;
    @NotNull
    @Column(nullable = false)
    private String password;
    @NotNull
    private Gender gender;
    @NotNull
    private Date dateOfBirth;

    @Column
    private CustomerStatus status=CustomerStatus.DEACTIVATED;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
    private Set<ShoppingCartProducts> shoppingCartProducts;

    public Set<ShoppingCartProducts> getShoppingCartProducts() {
        return shoppingCartProducts;
    }

    public void setShoppingCartProducts(Set<ShoppingCartProducts> shoppingCartProducts) {
        this.shoppingCartProducts = shoppingCartProducts;
    }

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, Gender gender, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public CustomerStatus getStatus() {
        return status;
    }
    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPasswordAttempts() {
        return passwordAttempts;
    }

    public void setPasswordAttempts(int passwordAttempts) {
        this.passwordAttempts = passwordAttempts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
