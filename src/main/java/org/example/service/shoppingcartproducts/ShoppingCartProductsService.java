package org.example.service.shoppingcartproducts;

import org.example.entity.Customer;
import org.example.entity.ShoppingCartProducts;

import java.util.List;

public interface ShoppingCartProductsService {
    List<ShoppingCartProducts> viewCart(Customer customer);
    void addToCart(ShoppingCartProducts shoppingCartProduct);
    boolean updateProductQuantityInCart(int shoppingCartProductId, int newQuantity);
    boolean removeFromCart(int shoppingCartProductId);
    double calculateTotal(Customer customer);

}
