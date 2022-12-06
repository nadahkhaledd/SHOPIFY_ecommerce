package org.example.service.address;

import org.example.entity.*;
import org.example.model.Response;
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
    public Response addAddress(Address address) {
        return repository.addAddress(address);
    }

    @Override
    public Response<List<Address>> getUserAddresses(int userId) {
        return repository.getUserAddresses(userId);
    }

    @Override
    public Response<Address> getAddress(int addressId) { return repository.getAddress(addressId); }

    @Override
    public Response<Address> updateAddress(Address address) {
        return repository.updateAddress(address);
    }

    @Override
    public Response<Address> deleteAddress(int addressId) {
        return repository.deleteAddress(addressId);
    }
}
