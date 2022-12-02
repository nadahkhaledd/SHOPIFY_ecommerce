package org.example.repository.address;

import org.example.entity.Address;
import org.example.entity.Customer;
import org.example.entity.User;

import java.util.List;

public interface AddressRepository {
    void addAddress(Address address);
    List<Address> getUserAddresses(User user);
    int updateAddress(Address address);
    int deleteAddress(Address address);
}
