package org.example.entity;


import javax.persistence.*;
import jakarta.validation.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Product {

    private int id;
    private String name;
    private String imagePath;
    private double price;

    private Category category;

    private String description;
    private int availableQuantity;

    private double rate;

    private List<Rate> rates;

    public Product() {}

    public Product(String name, String imagePath, double price, Category category, int availableQuantity) {
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
        this.category = category;
        this.availableQuantity = availableQuantity;

    }
    @Transient//will not be added as a column in the database
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product",fetch = FetchType.EAGER)
    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", unique = true, nullable = false)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @NotBlank
    @NotNull
    @Column(nullable = false, length = 45,unique = true)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @NotBlank
    @NotNull
    @Column(name = "image_path", nullable = false, length = 300)
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @NotNull
    @Column(nullable = false)
    @Min(1)
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    @NotNull
    @Column(name = "available_quantity", nullable = false)
    @Min(0)
    public int getAvailableQuantity() {
        return availableQuantity;
    }
    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", price=" + price +
                ", availableQuantity=" + availableQuantity +
                '}';
    }
}
