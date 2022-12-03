
package org.example.entity;

import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.Date;
import org.example.enums.Gender;
import org.example.enums.CustomerStatus;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;
import javax.persistence.Entity;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.INTEGER)
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(nullable = false, length = 30)
    private String firstName;
    @Column
    private int PasswordAttempts;
    @Column
    private CustomerStatus status;
    @NotNull
    @Column(nullable = false, length = 30)
    private String lastName;
    @NotNull
    @Column(nullable = false, unique = true, length = 150)
    private String email;
    @NotNull
    @Column(nullable = false)
    private String password;
    @NotNull
    @Column(nullable = false)
    private Gender gender;
    @NotNull
    private Date dateOfBirth;
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<ShoppingCartProducts> shoppingCartProducts;

    public CustomerStatus getStatus() {
        return this.status;
    }

    public void setStatus(final CustomerStatus status) {
        this.status = status;
    }

    public Set<ShoppingCartProducts> getShoppingCartProducts() {
        return this.shoppingCartProducts;
    }

    public void setShoppingCartProducts(final Set<ShoppingCartProducts> shoppingCartProducts) {
        this.shoppingCartProducts = shoppingCartProducts;
    }

    public int getPasswordAttempts() {
        return this.PasswordAttempts;
    }

    public void setPasswordAttempts(final int passwordAttempts) {
        this.PasswordAttempts = passwordAttempts;
    }

    public User() {
        this.status = CustomerStatus.DEACTIVATED;
    }

    public User(final String firstname, final String lastname, final String email, final String password, final Gender gender, final Date dateOfBirth) {
        this.status = CustomerStatus.DEACTIVATED;
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(final Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}