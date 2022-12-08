package org.example.service.customer;

import org.example.entity.Customer;
import org.example.entity.User;

public interface CustomerService {
    Customer getCustomerById(int userId);
}
