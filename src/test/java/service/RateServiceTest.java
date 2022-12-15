package service;

import org.example.entity.Category;
import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.entity.Rate;
import org.example.enums.CustomerStatus;
import org.example.enums.Gender;
import org.example.model.Response;
import org.example.model.UserInputReview;
import org.example.repository.rate.RateRepo;
import org.example.repository.rate.RateRepoImpl;
import org.example.service.customer.CustomerService;
import org.example.service.product.ProductService;
import org.example.service.rate.RateServiceImpl;
import org.junit.Before;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RateServiceTest {
        private RateRepo rateRepositoryMock;
        private RateServiceImpl rateService;
        private CustomerService customerServiceMock;
        private ProductService productServiceMock;
        public RateServiceTest(){
            rateRepositoryMock= Mockito.mock(RateRepoImpl.class);
            productServiceMock=Mockito.mock(ProductService.class);
            customerServiceMock=Mockito.mock(CustomerService.class);
            rateService=new RateServiceImpl(rateRepositoryMock,customerServiceMock,productServiceMock);
        }
        @Before
        public void init() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        public void assignRateToProductTest_sendValidProductResponse_sendValidRateResponse_returnProductResponse(){
            //arrange
            Customer customerTest=new Customer(
                    1,"Hagar", "Ehab", "hagar123@gmail.com", "hello", Gender.female,new Date(), CustomerStatus.ACTIVATED
            );
            when(customerServiceMock.getCustomerById(anyInt())).thenReturn(customerTest);
            Category kidsDresses=new Category();
            kidsDresses.setName("men's dresses");
            kidsDresses.setImagePath("dummy pic");
            Product product1=new Product("tshirt","dummy pic",120.0,kidsDresses,12);
            Response<Product> productResponse=new Response<Product>("Ok",200,false,false,product1);
            when(productServiceMock.getProductById(anyInt())).thenReturn(productResponse);
            when(rateRepositoryMock.addRate(any())).thenReturn(new Response("Done", 200, false,false,null));
            UserInputReview userRate=new UserInputReview(1,1,1,"nice");
            //act
            Response result=rateService.AssignRateToProduct(userRate);
            assertNotNull(result);
            assertEquals(result.getStatusCode(),200);
        }

    @Test
    public void assignRateToProductTest_sendInvalidProductResponse_sendValidRateResponse_returnProductResponse(){
        //arrange
        Customer customerTest=new Customer(
                1,"Hagar", "Ehab", "hagar123@gmail.com", "hello", Gender.female,new Date(), CustomerStatus.ACTIVATED
        );
        when(customerServiceMock.getCustomerById(anyInt())).thenReturn(customerTest);
        Response<Product> productResponse=new Response<Product>("error while processing your request",500,true,true);
        when(productServiceMock.getProductById(anyInt())).thenReturn(productResponse);
        when(rateRepositoryMock.addRate(any())).thenReturn(new Response("Ok", 200, false,false));
        UserInputReview userRate=new UserInputReview(1,1,1,"nice");
        //act
        Response result=rateService.AssignRateToProduct(userRate);
        assertNotNull(result);
        assertEquals(result.getStatusCode(),500);
    }
    @Test
    public void assignRateToProductTest_sendInvalidProductResponse_sendInvalidRateResponse_returnProductResponse(){
        //arrange
        Customer customerTest=new Customer(
                1,"Hagar", "Ehab", "hagar123@gmail.com", "hello", Gender.female,new Date(), CustomerStatus.ACTIVATED
        );
        when(customerServiceMock.getCustomerById(anyInt())).thenReturn(customerTest);
        Response<Product> productResponse=new Response<Product>("error while processing your request",500,true,true);
        when(productServiceMock.getProductById(anyInt())).thenReturn(productResponse);
        when(rateRepositoryMock.addRate(any())).thenReturn(new Response ("error while processing your request",500,true,true));
        UserInputReview userRate=new UserInputReview(1,1,1,"nice");
        //act
        Response result=rateService.AssignRateToProduct(userRate);
        assertNotNull(result);
        assertEquals(result.getStatusCode(),500);
    }
    @Test
    public void assignRateToProductTest_sendValidProductResponse_sendInvalidRateResponse_returnProductResponse(){
        //arrange
        Customer customerTest=new Customer(
                1,"Hagar", "Ehab", "hagar123@gmail.com", "hello", Gender.female,new Date(), CustomerStatus.ACTIVATED
        );
        when(customerServiceMock.getCustomerById(anyInt())).thenReturn(customerTest);
        Response productResponse=new Response("Ok", 200, false,false);
        when(productServiceMock.getProductById(anyInt())).thenReturn(productResponse);
        when(rateRepositoryMock.addRate(any())).thenReturn(new Response("error while processing your request",500,true,true));
        UserInputReview userRate=new UserInputReview(1,1,1,"nice");
        //act
        Response result=rateService.AssignRateToProduct(userRate);
        //assert
        assertNotNull(result);
        assertEquals(result.getStatusCode(),500);
    }

    @Test(expected = IllegalArgumentException.class)
    public void  calculateRateOfProduct_sendNegativeProductId(){
        //arrange
        int productId=-1;
        //act
        rateService.calculateRateOfProduct_(productId);
    }

    @Test
    public void  calculateRateOfProduct_sendValidProductId(){
        //arrange
        int productId=1;
        when(rateRepositoryMock.calculateRateOfProduct(
                anyInt())).thenReturn(new Response("Ok", 200, false,false,5.0));
        //act
        double rate=rateService.calculateRateOfProduct_(productId);
        //assert
        assertEquals(rate,5.0,0.0);
        //delta:describes the amount of difference you can tolerate in the values for them to be still considered equal
    }
    @Test
    public void setProductRate_sendProductWithRates_returnValidResponse(){
        //arrange
        Category kidsDresses=new Category();
        kidsDresses.setName("men's dresses");
        kidsDresses.setImagePath("dummy pic");
        Product product1=new Product("tshirt","dummy pic",120.0,kidsDresses,12);
        product1.setId(1);
        product1.setRates(Arrays.asList(new Rate()));
        when(rateRepositoryMock.calculateRateOfProduct(anyInt())
        ).thenReturn(new Response<Double>("OK",200,false,false,5.0));
        //act
        Response result=rateService.setProductRate(product1);
        //assert
         assertEquals(result.getStatusCode(),200);
         }

}
