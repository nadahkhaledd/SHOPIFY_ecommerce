package controller;

import org.example.controller.AddressController;
import org.example.controller.OrderController;
import org.example.entity.*;
import org.example.enums.OrderStatus;
import org.example.model.Response;
import org.example.service.address.AddressService;
import org.example.service.order.OrderService;
import org.example.service.user.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Or;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class OrderControllerTest {
    private final OrderService orderServiceMock;
    private final UserService userServiceMock;
    private final AddressService addressServiceMock;
    private final Model modelMock;
    private final HttpSession httpSessionMock;
    private final OrderController orderController;

    public OrderControllerTest() {
        orderServiceMock = Mockito.mock(OrderService.class);
        userServiceMock = Mockito.mock(UserService.class);
        addressServiceMock = Mockito.mock(AddressService.class);
        modelMock = Mockito.mock(Model.class);
        httpSessionMock = Mockito.mock(HttpSession.class);
        orderController = new OrderController(orderServiceMock, userServiceMock, addressServiceMock);
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getOrderDetailsTest_sendOrderId_internalErrorOccurred_returnErrorPage() {
        //Arrange
        Response<List<OrderDetails>> response = new Response<>("error", 400, true, false);
        when(orderServiceMock.getOrderDetails(anyInt())).thenReturn(response);

        //Act
        String returnedPage = orderController.getOrderDetails(1, modelMock);

        //Assert
        assertEquals("error", returnedPage);
    }

    @Test
    public void getOrderDetailsTest_sendOrderId_returnViewOrderDetailsPage() {
        //Arrange
        Response<List<OrderDetails>> response = new Response<>("Done", 200, false, false, new ArrayList<>());
        when(orderServiceMock.getOrderDetails(anyInt())).thenReturn(response);

        //Act
        String returnedPage = orderController.getOrderDetails(1, modelMock);

        //Assert
        verify(modelMock, times(1)).addAttribute(anyString(), any());
        assertEquals("viewOrderDetails", returnedPage);
    }

    @Test
    public void getOrdersTest_internalErrorOccurred_returnErrorPage() {
        //Arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(1);
        Response<List<Order>> response = new Response<>("error", 400, true, false, new ArrayList<>());
        when(orderServiceMock.getOrders(anyInt(), any(OrderStatus.class))).thenReturn(response);

        //Act
        String returnedPage = orderController.getOrders(modelMock, httpSessionMock);

        //Assert
        assertEquals("error", returnedPage);
    }

    @Test
    public void getOrdersTest_returnViewOrderPage() {
        //Arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(1);
        Response<List<Order>> response = new Response<>("Done", 200, false, false, new ArrayList<>());
        when(orderServiceMock.getOrders(anyInt(), any(OrderStatus.class))).thenReturn(response);

        //Act
        String returnedPage = orderController.getOrders(modelMock, httpSessionMock);

        //Assert
        assertEquals("viewOrder", returnedPage);
    }

//    @Test
//    public void placeOrderTest_sendOrder_internalErrorOccurred_returnErrorPage() {
//        //Arrange
//        when(httpSessionMock.getAttribute(anyString())).thenReturn(1);
//        User user = new User();
//        when(userServiceMock.getUserById(anyInt()))
//                .thenReturn(new Response<>("error", 400, true, false, user));
//
//        //Act
//        String returnedPage = orderController.placeOrder(new Order(), modelMock, httpSessionMock);
//
//        //Assert
//        assertEquals("error", returnedPage);
//    }

    @Test
    public void cancelOrderTest_sendOrderId_internalErrorOccurred_returnErrorPage() {
        //Arrange
        Response response = new Response("error", 400, true, false, false);
        when(orderServiceMock.updateStatus(anyInt(), any(OrderStatus.class)))
                .thenReturn(response);

        //Act
        String returnedPage = orderController.cancelOrder(1, modelMock);

        //Assert
        assertEquals("error", returnedPage);
    }

    @Test
    public void cancelOrderTest_sendOrderId_returnViewOrdersPage() {
        //Arrange
        Response response = new Response("Done", 200, false, false, true);
        when(orderServiceMock.updateStatus(anyInt(), any(OrderStatus.class)))
                .thenReturn(response);

        //Act
        String returnedPage = orderController.cancelOrder(1, modelMock);

        //Assert
        assertEquals("redirect:/orders/view", returnedPage);
        assertEquals(true, response.getObjectToBeReturned());
    }
}
