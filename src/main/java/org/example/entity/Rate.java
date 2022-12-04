package org.example.entity;

import javax.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Rate {
    private int id;
    private int rate;
    private Customer customer;
    private Product product;
    private String description;

    public Rate() {
    }

    public Rate(int rate,String description,Customer customer, Product product) {
        this.customer = customer;
        this.product = product;
        this.description=description;
        this.rate=rate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rate_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = description;
    }

    @JoinColumn(name = "customer_id")
    @ManyToOne
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    @JoinColumn(name = "product_id")
    @ManyToOne
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", rate=" + rate +
                ", customer=" + customer +
                ", product=" + product +
                ", description='" + description + '\'' +
                '}';
    }
}
