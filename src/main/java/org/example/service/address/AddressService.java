package org.example.service.address;

import org.example.entity.*;
import org.example.model.Response;

import java.util.List;

public interface AddressService {

    Response addAddress(Address address);
    Response<List<Address>> getUserAddresses(int userId);
    Response<Address> getAddress(int addressId);
    Response<Address> updateAddress(Address address);
    Response<Address> deleteAddress(int addressId);


}
