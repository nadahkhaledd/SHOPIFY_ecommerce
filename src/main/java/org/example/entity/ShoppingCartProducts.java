package org.example.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Entity
public class ShoppingCartProducts {

    private int id;
    private int productQuantity;
    private Product product;
    private User user;
    public ShoppingCartProducts() {}

    public ShoppingCartProducts(int productQuantity, Product product, User user) {
        this.productQuantity = productQuantity;
        this.product = product;
        this.user = user;
    }

    @ManyToOne()
    @JoinColumn(name = "user_id")

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name="product_id", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
