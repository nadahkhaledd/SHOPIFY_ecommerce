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
        if(address == null)
            throw new NullPointerException();
        return repository.addAddress(address);
    }

    @Override
    public Response<List<Address>> getUserAddresses(int userId) {
        if(userId < 1)
            throw new IllegalArgumentException();
        return repository.getUserAddresses(userId);
    }

    @Override
    public Response<Address> getAddressById(int addressId) {
        if(addressId < 1)
            throw new IllegalArgumentException();
        return repository.getAddressById(addressId);
    }

    @Override
    public Response<Address> updateAddress(Address address) {
        if(address == null)
            throw new NullPointerException();
        return repository.updateAddress(address);
    }

    @Override
    public Response<Boolean> deleteAddress(int addressId) {
        if(addressId < 1)
            throw new IllegalArgumentException();
        int affectedRows = repository.deleteAddress(addressId).getObjectToBeReturned();
        return new Response<>("Done", 200, false, false, affectedRows == 1);
    }
}
