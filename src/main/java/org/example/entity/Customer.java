package org.example.entity;

import org.example.enums.Gender;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Customer extends User {
    private String status;
     private int passwordAttempts;
 //   @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private List<Address> addresses;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private List<Rate> Rates;

    public Customer() {
    }

    public Customer(String firstname, String lastname, String email, String password, Gender gender, Date dateOfBirth, String status, int passwordAttempts, List<Address> addresses) {
        super(firstname, lastname, email, password, gender, dateOfBirth);
        this.status = status;
        this.passwordAttempts = passwordAttempts;
        this.addresses = addresses;
    }

    public List<Rate> getRates() {
        return Rates;
    }

    public void setRates(List<Rate> rates) {
        Rates = rates;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPasswordAttempts() {
        return passwordAttempts;
    }

    public void setPasswordAttempts(int passwordAttempts) {
        this.passwordAttempts = passwordAttempts;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
