package org.example.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ShoppingCartProducts {

    private int id;
    private int productQuantity;
    private Product product;
    private Customer customer;

    public ShoppingCartProducts() {}

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    @ManyToOne
    @JoinColumn(name="customer_id", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @NotNull
    @Column(name = "product_quantity", nullable = false)
    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
