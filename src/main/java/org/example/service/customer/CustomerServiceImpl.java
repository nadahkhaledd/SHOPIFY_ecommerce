package org.example.service.customer;

import org.example.entity.Customer;
import org.example.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository=customerRepository;
    }

    /**
     * @param userId 
     * @return
     */
    @Override
    public Customer getCustomerById(int userId) {
        if(userId<0)
            throw new IllegalArgumentException();
        return customerRepository.getCustomerById(userId);
    }
}
