package org.example.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="order_details")
public class OderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="order_id")
    @NotNull
    private Long orderId;
    @Column(name = "product_name")
    @NotNull
    private String productName;
    @Column(name = "product_price")
    @NotNull
    private String productPrice;
    @Column(name = "product_image")
    @NotNull
    private String productImage;

    public OderDetails() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OderDetails(Long orderId, String productName, String productPrice, String productImage) {
        this.orderId = orderId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImage = productImage;
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

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
