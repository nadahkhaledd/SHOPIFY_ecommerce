package org.example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Address {
    @Id
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
}
