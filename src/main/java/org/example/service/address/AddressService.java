package org.example.service.address;

import org.example.entity.*;

import java.util.List;

public interface AddressService {

    void addAddress(Address address);
    List<Address> getUserAddresses(User user);
    boolean updateAddress(Address address);
    boolean deleteAddress(Address address);


}
