package org.example.repository.customer;

import org.example.entity.Customer;

public interface CustomerRepository {
    Customer getCustomerById(int userId);

}
