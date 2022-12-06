package org.example.repository.address;

import org.example.entity.Address;
import org.example.entity.Customer;
import org.example.entity.User;
import org.example.model.Response;

import java.util.List;

public interface AddressRepository {
    Response addAddress(Address address);
    Response<List<Address>> getUserAddresses(int userId);
    Response<Address> getAddress(int addressId);
    Response<Address> updateAddress(Address address);
    Response<Address> deleteAddress(int addressId);
}
