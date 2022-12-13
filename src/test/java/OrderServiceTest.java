import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.entity.OrderDetails;
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
    private ProductRepo productRepo;
    private UserService userService;
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

    @Test
    public void getOrderByIdTest_returnOneOrderInOrderEntity() {
        Response<Order> orderResponse = new Response<Order>("Done", 200, false, new Order());
        when(orderRepositoryMock.getOrderById(any(Integer.class))).thenReturn(orderResponse);
        Response<Order> result = orderService.getOrderById(1);
        assertNotNull(result);
        assertEquals("Done", result.getMessage());
        assertEquals(200, result.getStatusCode());
//      verify(orderRepositoryMock.getOrderById(any(Integer.class)), times(1));
    }

    @Test
    public void updateStatusTest_returnOrderDetailsBelongsToOrderEntity() {
        Response<Boolean> orderResponse = new Response<Boolean>("Done", 200, false);
        when(orderRepositoryMock.updateStatus(1, OrderStatus.cancelled)).thenReturn(orderResponse);
        //Response<Boolean> result = new Response<Boolean>("Done", 200, false);
        Response<Boolean> result = orderService.updateStatus(1, OrderStatus.cancelled);
        assertEquals(200, result.getStatusCode());
    }

    @Test
    public void getOrdersTest_returnOrderDetailsBelongsToOrderEntity() {
        Order order = new Order(new Customer(), LocalDate.now(),OrderStatus.cancelled,1.00);
        List<Order> orders= new ArrayList<>();
        orders.add(order);

        Response<List<Order>> orderResponse =new Response<>("Done", 200, false, orders);

        when(orderRepositoryMock.getOrders(any(Customer.class),any(OrderStatus.class))).thenReturn(orderResponse);

        Response<List<Order>> result = orderService.getOrders(1,OrderStatus.returned);
        assertEquals(200, result.getStatusCode());

    }





}
