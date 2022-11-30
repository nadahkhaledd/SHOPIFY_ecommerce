package org.example.repository.shoppingcartproducts;

import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.entity.ShoppingCartProducts;

public interface ShoppingCartProductsRepository {

    void addToCart(Product product, Customer customer, ShoppingCartProducts shoppingCartProduct);
    int updateProductQuantityInCart(int shoppingCartProductId, int newQuantity);
    int removeFromCart(int shoppingCartProductId);
    double calculateTotal(Customer customer);
}
