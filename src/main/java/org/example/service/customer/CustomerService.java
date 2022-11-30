package org.example.service.customer;

import org.example.entity.Address;
import org.example.entity.ShoppingCartProducts;

public interface CustomerService {

    void addAddress(Address address);
    boolean updateAddress(int addressId, Address address);
    boolean deleteAddress(int addressId);
    void addToCart(ShoppingCartProducts shoppingCartProduct);
    boolean updateProductQuantityInCart(int shoppingCartProductId, int newQuantity);
    boolean removeFromCart(int shoppingCartProductId);

}
