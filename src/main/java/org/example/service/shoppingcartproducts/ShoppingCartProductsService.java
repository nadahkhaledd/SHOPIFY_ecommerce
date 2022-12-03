package org.example.service.shoppingcartproducts;

import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.entity.ShoppingCartProducts;
import org.example.entity.User;

import java.util.List;

public interface ShoppingCartProductsService {
    List<ShoppingCartProducts> viewCart(int userId);
    ShoppingCartProducts getCartItem(Product product, User user);
    ShoppingCartProducts getCartItem(int cartItemId);
    void addToCart(ShoppingCartProducts shoppingCartProduct);
    boolean updateProductQuantityInCart(int shoppingCartProductId, int newQuantity);
    boolean removeFromCart(int shoppingCartProductId);
    double calculateTotal(int userId);

}
