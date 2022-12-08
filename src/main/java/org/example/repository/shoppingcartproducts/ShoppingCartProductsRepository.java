package org.example.repository.shoppingcartproducts;

import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.entity.ShoppingCartProducts;
import org.example.entity.User;
import org.example.model.Response;

import java.util.List;

public interface ShoppingCartProductsRepository {
    Response<List<ShoppingCartProducts>> viewCart(int userId);
    Response<ShoppingCartProducts> getCartItem(Product product, User user);
    Response<ShoppingCartProducts> getCartItem(int cartItemId);
    Response addToCart(ShoppingCartProducts shoppingCartProduct);
    Response<Integer> updateProductQuantityInCart(int shoppingCartProductId, int newQuantity);
    Response<Boolean> removeFromCart(int shoppingCartProductId);
    Response<Double> calculateTotal(int userId);
}
