package org.example.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String street ;
    @NotNull
    private int buildingNumber;
    @NotNull
    private String city;

    @JoinColumn(name = "customer_id")
    @ManyToOne
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "address")
    private List<Order> order;


    public Address() {
    }

    public Address(String street, int buildingNumber, String city) {
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }
}
