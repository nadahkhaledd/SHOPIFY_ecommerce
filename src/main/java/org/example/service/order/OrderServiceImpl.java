package org.example.service.order;

import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.entity.OrderDetails;
import org.example.enums.OrderStatus;
import org.example.model.Response;
import org.example.repository.order.OrderRepository;
import org.example.service.product.ProductService;
import org.example.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productService = productService;
    }


    public Response<List<Order>> getOrders(int userId, OrderStatus status) {
        Customer customer = (Customer) userService.getUserById(userId).getObjectToBeReturned();
        return orderRepository.getOrders(customer, status);
    }

    public Response<Order> getOrderById(int orderId) {
        return orderRepository.getOrderById(orderId);
    }

    public Response<List<OrderDetails>> getOrderDetails(int orderId) {
        Order order = getOrderById(orderId).getObjectToBeReturned();
        return orderRepository.getOrderDetails(order);
    }

    public Response checkOrderStatus(int orderId){
        return orderRepository.checkOrderStatus(orderId);
    }

//    public Response<Boolean> cancelOrder(int orderId) {
//        //get order status and deny the process if it's delivered
//        if(checkOrderStatus(orderId)=="completed" || checkOrderStatus(orderId)=="returned"){
//            return false;
//        }else{
//            return orderRepository.updateStatus(orderId,OrderStatus.cancelled);
//        }
//    }
//

    public Response<Boolean> updateStatus(int orderId, OrderStatus status) {
        return  orderRepository.updateStatus(orderId, status);
    }

    public Response checkOut(Customer customer, Order order) {
        return orderRepository.checkOut(customer, order);
    }
}
