package org.example.service.address;

import org.example.entity.Address;
import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.entity.ShoppingCartProducts;
import org.example.repository.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;

    @Autowired
    public AddressServiceImpl(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addAddress(Address address) {
        repository.addAddress(address);
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
}
