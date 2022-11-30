package org.example.repository.address;

import org.example.entity.Address;
import org.example.entity.Customer;

import java.util.List;

public interface AddressRepository {
    void addAddress(Customer customer, Address address);
    List<Address> getUserAddresses(Customer customer);
    int updateAddress(Address address);
    int deleteAddress(Address address);
}
