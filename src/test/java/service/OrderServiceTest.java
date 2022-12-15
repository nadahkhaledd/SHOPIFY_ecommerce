package service;

import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.entity.OrderDetails;
import org.example.entity.User;
import org.example.enums.OrderStatus;
import org.example.model.Response;
import org.example.repository.order.OrderRepository;
import org.example.repository.order.OrderRepositoryImpl;
import org.example.repository.product.ProductRepo;
import org.example.repository.product.ProductRepoImpl;
import org.example.repository.user.UserRepository;
import org.example.repository.user.UserRepositoryImpl;
import org.example.service.order.OrderService;
import org.example.service.order.OrderServiceImpl;
import org.example.service.product.ProductService;
import org.example.service.product.ProductServiceImpl;
import org.example.service.user.UserService;
import org.example.service.user.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    private OrderService orderService;
    private OrderRepository orderRepositoryMock;
    private UserRepository userRepository;
    private UserService userService;
    private ProductRepo productRepo;
    private ProductService productService;

    @Before
    public void setup() {
        orderRepositoryMock = Mockito.mock(OrderRepositoryImpl.class);
        userRepository = Mockito.mock(UserRepositoryImpl.class);
        productRepo = Mockito.mock(ProductRepoImpl.class);
        userService = new UserServiceImpl(userRepository);
        productService = new ProductServiceImpl(productRepo);
        orderService = new OrderServiceImpl(orderRepositoryMock, userService, productService);
    }
//_thenReturnTrue
    @Test
    public void getOrderByIdTest_sendOrderId_returnOneOrderInOrderEntity() {
        Response<Order> orderResponse = new Response<Order>("Done", 200, false, new Order());
        when(orderRepositoryMock.getOrderById(any(Integer.class))).thenReturn(orderResponse);
        Response<Order> result = orderService.getOrderById(1);
        assertNotNull(result);
        assertEquals("Done", result.getMessage());
        assertEquals(200, result.getStatusCode());
    }

    @Test
    public void updateStatusTest_sendOrderStatus_returnOrderDetailsBelongsToOrderEntity() {
        Response<Boolean> orderResponse = new Response<Boolean>("Done", 200, false);
        when(orderRepositoryMock.updateStatus(1, OrderStatus.cancelled)).thenReturn(orderResponse);
        //Response<Boolean> result = new Response<Boolean>("Done", 200, false);
        Response<Boolean> result = this.orderService.updateStatus(1, OrderStatus.cancelled);
        assertEquals(200, result.getStatusCode());
    }

    @Test
    public void getOrdersTest_sendCustomer_returnOrderDetailsBelongsToOrderEntity() {
        Order order = new Order(new Customer(), LocalDate.now(), OrderStatus.cancelled, 1.00);
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        Customer user =new Customer();

        Response userResponse = new Response("Done", 200, false, user);
        when(userRepository.getUserById(any(Integer.class))).thenReturn(userResponse);

        Response<List<Order>> orderResponse = new Response<>("Done", 200, false, orders);
        when(orderRepositoryMock.getOrders(any(Customer.class), any(OrderStatus.class))).thenReturn(orderResponse);

        Response<List<Order>> result = orderService.getOrders(1, OrderStatus.cancelled);
        assertEquals(200, result.getStatusCode());
    }

    @Test
    public void getOrderDetailsTest_sendOrder_returnListOfProduct(){
        Order order = new Order(new Customer(), LocalDate.now(), OrderStatus.cancelled, 1.00);
        List<OrderDetails> orderDetails = new ArrayList<>();
        OrderDetails orderItem = new OrderDetails();
        orderDetails.add(orderItem);
        order.setOrderDetails(orderDetails);

        Response<Order> orderResponse1 = new Response("Done", 200, false,order);
        when(orderRepositoryMock.getOrderById(any(Integer.class))).thenReturn(orderResponse1);

        Response<List<OrderDetails>> orderResponse = new Response<List<OrderDetails>>("Done", 200, false,orderDetails);
        when(orderRepositoryMock.getOrderDetails(any(Order.class))).thenReturn(orderResponse);

        Response result = orderService.getOrderDetails(order.getId());
        assertEquals(200, result.getStatusCode());
    }

    @Test
    public void checkOutTest_sendUserAndOrder_returnedSuccessCode(){
        Order order = new Order(new Customer(), LocalDate.now(), OrderStatus.cancelled, 1.00);
        List<OrderDetails> orderDetails = new ArrayList<>();
        OrderDetails orderItem = new OrderDetails();
        orderDetails.add(orderItem);
        order.setOrderDetails(orderDetails);
        Customer user =new Customer();

        Response orderResponse =  new Response("Done",200,false,order);
        when(orderRepositoryMock.checkOut(any(Customer.class),any(Order.class))).thenReturn(orderResponse);

        Response result = orderService.checkOut(user,order);
        assertEquals(200, result.getStatusCode());
    }

    @Test
    public void checkOrderStatusTest_sendOrderId_toReturnStatus(){
        Order order = new Order(new Customer(), LocalDate.now(), OrderStatus.cancelled, 1.00);
        List<OrderDetails> orderDetails = new ArrayList<>();
        OrderDetails orderItem = new OrderDetails();
        orderDetails.add(orderItem);
        order.setOrderDetails(orderDetails);

        Response response = new Response("Done", 200, false, order.getStatus());
        when(orderRepositoryMock.checkOrderStatus(any(Integer.class))).thenReturn(response);

        Response result = orderService.checkOrderStatus(order.getId());
        assertEquals(200, result.getStatusCode());
    }


}
