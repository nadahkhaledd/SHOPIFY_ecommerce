package org.example.entity;

import org.example.enums.CustomerStatus;
import org.example.enums.Gender;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("1")
public class Customer extends User {
     private CustomerStatus status=CustomerStatus.DEACTIVATED;
     private int passwordAttempts=0;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private List<Address> addresses;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private List<Rate> Rates;

    public Customer() {
    }

    public Customer(int id, String firstName, String lastName, String email, String password, Gender gender, Date dateOfBirth, CustomerStatus status, int passwordAttempts) {
        super(id, firstName, lastName, email, password, gender, dateOfBirth);
        this.status = status;
        this.passwordAttempts = passwordAttempts;
        this.addresses = new ArrayList<>();
    }

    public Customer(int id, String firstname, String lastname, String email, String password, Gender gender, Date dateOfBirth, CustomerStatus status, int passwordAttempts, List<Address> addresses) {
        super(id, firstname, lastname, email, password, gender, dateOfBirth);
        this.status = status;
        this.passwordAttempts = passwordAttempts;
        this.addresses = addresses;
    }

    public Customer(String firstname, String lastname, String email, String password, Gender gender, Date dateOfBirth, CustomerStatus status, int passwordAttempts, List<Address> addresses) {
        super(firstname, lastname, email, password, gender, dateOfBirth);
        this.status = status;
        this.passwordAttempts = passwordAttempts;
        this.addresses = addresses;
    }

    public Customer(int id, String firstName, String lastName, String email, String password, Gender gender, Date dateOfBirth, CustomerStatus status) {
        super(id, firstName, lastName, email, password, gender, dateOfBirth);
        this.status = status;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public List<Rate> getRates() {
        return Rates;
    }

    public void setRates(List<Rate> rates) {
        Rates = rates;
    }


    public void setStatus(CustomerStatus status) {
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
