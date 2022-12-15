package service;

import helpers.HelperMethods;
import org.example.entity.Customer;
import org.example.repository.customer.CustomerRepository;
import org.example.service.customer.CustomerService;
import org.example.service.customer.CustomerServiceImpl;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {
    private CustomerService customerService;
    private CustomerRepository customerRepositoryMock;
    private HelperMethods helperMethods;
    public CustomerServiceTest(){
        customerRepositoryMock=
                Mockito.mock(CustomerRepository.class);
        customerService=new CustomerServiceImpl(customerRepositoryMock);
        helperMethods=new HelperMethods();
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCustomerByIdTest_giveNegativeId_expectedIllegalArgException(){
        // arrange
        int userId=-1;
        //act
        customerService.getCustomerById(userId);
    }
    @Test
    public void getCustomerByIdTest_giveValidId_expectedCustomerObject(){
        // arrange
        int userId=1;
        Customer expectedCustomer=helperMethods.initCustomer();
        when(customerRepositoryMock.getCustomerById(anyInt())).thenReturn(expectedCustomer);
        //act
        Customer customerResult= customerService.getCustomerById(userId);
        //assert
        assertEquals(expectedCustomer,customerResult);
    }
}
