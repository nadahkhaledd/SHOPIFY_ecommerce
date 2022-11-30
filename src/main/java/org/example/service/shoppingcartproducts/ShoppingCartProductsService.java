package org.example.service.shoppingcartproducts;

import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.entity.ShoppingCartProducts;

public interface ShoppingCartProductsService {
    void addToCart(Product product, Customer customer, ShoppingCartProducts shoppingCartProduct);
    boolean updateProductQuantityInCart(int shoppingCartProductId, int newQuantity);
    boolean removeFromCart(int shoppingCartProductId);
    double calculateTotal(Customer customer);

}
