package org.example.service.customer;

import org.example.entity.Address;
import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.entity.ShoppingCartProducts;
import org.example.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addAddress(Customer customer, Address address) {
        repository.addAddress(customer, address);
    }

    @Override
    public List<Address> getUserAddresses(Customer customer) {
        return repository.getUserAddresses(customer);
    }

    @Override
    public boolean updateAddress(Address address) {
        int affectedRows = repository.updateAddress(address);
        return affectedRows == 1;
    }

    @Override
    public boolean deleteAddress(Address address) {
        int affectedRows = repository.deleteAddress(address);
        return affectedRows == 1;
    }

    @Override
    public void addToCart(Product product, Customer customer, ShoppingCartProducts shoppingCartProduct) {
        repository.addToCart(product, customer, shoppingCartProduct);
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
