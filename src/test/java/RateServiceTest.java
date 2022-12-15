import helpers.HelperMethods;
import lombok.experimental.Helper;
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
import org.example.service.customer.CustomerServiceImpl;
import org.example.service.product.ProductService;
import org.example.service.product.ProductServiceImpl;
import org.example.service.rate.RateService;
import org.example.service.rate.RateServiceImpl;
import org.junit.Before;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RateServiceTest {
        private RateRepo rateRepositoryMock;
        private RateServiceImpl rateService;
        private CustomerService customerServiceMock;
        private ProductService productServiceMock;
        private HelperMethods helperMethods;
        public RateServiceTest(){
            rateRepositoryMock= Mockito.mock(RateRepoImpl.class);
            productServiceMock=Mockito.mock(ProductService.class);
            customerServiceMock=Mockito.mock(CustomerService.class);
            rateService=new RateServiceImpl(rateRepositoryMock,customerServiceMock,productServiceMock);
            helperMethods=new HelperMethods();
        }
        @Before
        public void init() {
            MockitoAnnotations.openMocks(this);
        }


        @Test
        public void assignRateToProductTest_sendValidProductResponse_sendValidRateResponse_returnProductResponse(){
            //arrange
            Customer customerTest=helperMethods.initCustomer();
            when(customerServiceMock.getCustomerById(anyInt())).thenReturn(customerTest);
            Product product=helperMethods.initProduct();
            Response<Product> productResponse=new Response<Product>("Ok",200,false,false,product);
            when(productServiceMock.getProductsById(anyInt())).thenReturn(productResponse);
            when(rateRepositoryMock.addRate(any())).thenReturn(new Response("Done", 200, false));
            UserInputReview userRate=new UserInputReview(1,1,1,"nice");
            //act
            Response result=rateService.AssignRateToProduct(userRate);
            //assert
            assertNotNull(result);
            assertEquals(result.getStatusCode(),200);
        }

    @Test
    public void assignRateToProductTest_sendInvalidProductResponse_sendValidRateResponse_returnProductResponse(){
        //arrange
        Customer customerTest=helperMethods.initCustomer();
        when(customerServiceMock.getCustomerById(anyInt())).thenReturn(customerTest);
        Response<Product> productResponse=new Response<Product>("error while processing your request",500,true,true);
        when(productServiceMock.getProductsById(anyInt())).thenReturn(productResponse);
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
        Customer customerTest=helperMethods.initCustomer();
        when(customerServiceMock.getCustomerById(anyInt())).thenReturn(customerTest);
        Response<Product> productResponse=new Response<Product>("error while processing your request",500,true,true);
        when(productServiceMock.getProductsById(anyInt())).thenReturn(productResponse);
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
        Customer customerTest=helperMethods.initCustomer();
        when(customerServiceMock.getCustomerById(anyInt())).thenReturn(customerTest);
        Response productResponse=new Response("Ok", 200, false,false);
        when(productServiceMock.getProductsById(anyInt())).thenReturn(productResponse);
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
        Product product  =helperMethods.initProduct();
        when(rateRepositoryMock.calculateRateOfProduct(anyInt())
        ).thenReturn(new Response<Double>("OK",200,false,false,5.0));
        //act
        Response result=rateService.setProductRate(product);
        //assert
         assertEquals(result.getStatusCode(),200);
         }

}
