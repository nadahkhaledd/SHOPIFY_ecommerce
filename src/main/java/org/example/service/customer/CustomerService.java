package org.example.service.customer;

import org.example.entity.Address;
import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.entity.ShoppingCartProducts;

import java.util.List;

public interface CustomerService {

    void addAddress(Customer customer, Address address);
    List<Address> getUserAddresses(Customer customer);
    boolean updateAddress(Address address);
    boolean deleteAddress(Address address);
    void addToCart(Product product, Customer customer, ShoppingCartProducts shoppingCartProduct);
    boolean updateProductQuantityInCart(int shoppingCartProductId, int newQuantity);
    boolean removeFromCart(int shoppingCartProductId);

}
