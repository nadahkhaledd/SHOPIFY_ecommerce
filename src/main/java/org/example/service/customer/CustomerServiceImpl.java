package org.example.service.customer;

import org.example.entity.Customer;
import org.example.entity.User;
import org.example.repository.customer.CustomerRepository;
import org.example.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//user service , customerService ,encryption service
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
        return customerRepository.getCustomerById(userId);
    }
}
