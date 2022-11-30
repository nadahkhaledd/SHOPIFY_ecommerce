package org.example.service.customer;

import org.example.entity.Address;
import org.example.entity.ShoppingCartProducts;
import org.example.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addAddress(Address address) {
        repository.addAddress(address);
    }

    @Override
    public boolean updateAddress(int addressId, Address address) {
        int affectedRows = repository.updateAddress(addressId, address);
        return affectedRows == 1;
    }

    @Override
    public boolean deleteAddress(int addressId) {
        int affectedRows = repository.deleteAddress(addressId);
        return affectedRows == 1;
    }

    @Override
    public void addToCart(ShoppingCartProducts shoppingCartProduct) {
        repository.addToCart(shoppingCartProduct);
    }

    @Override
    public boolean updateProductQuantityInCart(int shoppingCartProductId, int newQuantity) {
        int affectedRows = repository.updateProductQuantityInCart(shoppingCartProductId, newQuantity);
        return affectedRows == 1;
    }

    @Override
    public boolean removeFromCart(int shoppingCartProductId) {
        int affectedRows = repository.removeFromCart(shoppingCartProductId);
        return affectedRows == 1;
    }
}
