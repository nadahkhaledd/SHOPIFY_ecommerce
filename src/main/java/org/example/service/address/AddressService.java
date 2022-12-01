package org.example.service.address;

import org.example.entity.Address;
import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.entity.ShoppingCartProducts;

import java.util.List;

public interface AddressService {

    void addAddress(Address address);
    List<Address> getUserAddresses(Customer customer);
    boolean updateAddress(Address address);
    boolean deleteAddress(Address address);


}
