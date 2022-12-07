package org.example.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="order_details")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;



    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Column(name = "product_name")
    @NotNull
    private String productName;
    @Column(name = "product_price")
    @NotNull
    private double productPrice;
    @Column(name = "product_image")
    @NotNull
    private String productImage;
    @Column(name = "product_quantity")
    @NotNull
    private int productQuantity;



    public OrderDetails() {
    }
    public OrderDetails(Order order, String productName, double productPrice, String productImage, int productQuantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.order = order;
        this.productQuantity = productQuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
