package org.example.repository.customer;

import org.example.entity.Address;
import org.example.entity.ShoppingCartProducts;

import java.util.List;

public interface CustomerRepository {

    void addAddress(Address address);
    List<Address> getAllAddresses(int customerId);
    int updateAddress(int addressId, Address address);
    int deleteAddress(int addressId);
    void addToCart(ShoppingCartProducts shoppingCartProduct);
    int updateProductQuantityInCart(int shoppingCartProductId, int newQuantity);
    int removeFromCart(int shoppingCartProductId);
}
