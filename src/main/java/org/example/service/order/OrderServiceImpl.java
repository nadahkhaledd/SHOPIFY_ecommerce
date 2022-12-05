package org.example.service.order;

import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.entity.OrderDetails;
import org.example.enums.OrderStatus;
import org.example.repository.order.OrderRepository;
import org.example.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final UserService userService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }


    public List<Order> getOrders(int userId) {
        Customer customer = (Customer) userService.getUserById(userId);
        return orderRepository.getOrders(customer);
    }

    public Order getOrderById(int orderId) {
        return orderRepository.getOrderById(orderId);
    }

    public List<OrderDetails> getOrderDetails(int orderId) {
        Order order = getOrderById(orderId);
        return orderRepository.getOrderDetails(order);
    }

    public String checkOrderStatus(int orderId){
        return orderRepository.checkOrderStatus(orderId);
    }

    public boolean cancelOrder(int orderId) {
        //get order status and deny the process if it's delivered
        if(checkOrderStatus(orderId)=="completed" || checkOrderStatus(orderId)=="returned"){
            return false;
        }else{
            return orderRepository.updateStatus(orderId,OrderStatus.cancelled);
        }
    }

    public boolean updateStatus(int orderId){
        switch(orderRepository.checkOrderStatus(orderId)) {
            case "placed":
                return orderRepository.updateStatus(orderId, OrderStatus.shipped);
            case "shipped":
                return orderRepository.updateStatus(orderId, OrderStatus.delivered);
            default: //returned
               return orderRepository.updateStatus(orderId, OrderStatus.returned);
        }
    }

    public void checkOut(Customer customer) {
        orderRepository.checkOut(customer);
    }
}
