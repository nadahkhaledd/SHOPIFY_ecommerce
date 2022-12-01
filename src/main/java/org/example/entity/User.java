package org.example.entity;

import org.example.enums.Gender;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(nullable = false, length = 30)
    private String firstName;
    @NotNull
    @Column(nullable = false, length = 30)
    private String lastName;
    @NotNull
    @Column(nullable = false, unique = true,length = 150)
    private String email;
    @NotNull
    @Column(nullable = false)
    private String password;
    @NotNull
    @Column(nullable = false)
    private Gender gender;
    @NotNull
    private Date dateOfBirth;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "user_id")
    private Set<ShoppingCartProducts> shoppingCartProducts;

    public Set<ShoppingCartProducts> getShoppingCartProducts() {
        return shoppingCartProducts;
    }

    public void setShoppingCartProducts(Set<ShoppingCartProducts> shoppingCartProducts) {
        this.shoppingCartProducts = shoppingCartProducts;
    }

    public User() {
    }

    public User(String firstname, String lastname, String email, String password, Gender gender, Date dateOfBirth) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
