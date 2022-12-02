package org.example.service.address;

import org.example.entity.*;
import org.example.repository.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public List<Address> getUserAddresses(User user) {
        return repository.getUserAddresses(user);
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
