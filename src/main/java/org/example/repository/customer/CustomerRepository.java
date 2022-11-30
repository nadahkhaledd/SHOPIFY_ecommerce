package org.example.repository.customer;

import org.example.entity.Address;
import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.entity.ShoppingCartProducts;

import java.util.List;

public interface CustomerRepository {

    void addAddress(Customer customer, Address address);
    List<Address> getUserAddresses(Customer customer);
    int updateAddress(Address address);
    int deleteAddress(Address address);
    void addToCart(Product product, Customer customer, ShoppingCartProducts shoppingCartProduct);
    int updateProductQuantityInCart(int shoppingCartProductId, int newQuantity);
    int removeFromCart(int shoppingCartProductId);
}
