package org.example.repository.shoppingcartproducts;

import org.example.entity.Customer;
import org.example.entity.ShoppingCartProducts;

import java.util.List;

public interface ShoppingCartProductsRepository {
    List<ShoppingCartProducts> viewCart(Customer customer);
    void addToCart(ShoppingCartProducts shoppingCartProduct);
    int updateProductQuantityInCart(int shoppingCartProductId, int newQuantity);
    int removeFromCart(int shoppingCartProductId);
    double calculateTotal(Customer customer);
}
