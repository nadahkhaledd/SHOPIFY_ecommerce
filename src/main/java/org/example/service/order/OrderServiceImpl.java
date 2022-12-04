package org.example.service.order;

import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.enums.OrderStatus;
import org.example.repository.order.OrderRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepositoryImpl orderRepositoryImpl;

    public List<Order> getOrders(int userId) {

        return orderRepositoryImpl.getOrders(userId);
    }

    public List getOrderDetails(int orderId) {
        return orderRepositoryImpl.getOrderDetails(orderId);
    }

    public String checkOrderStatus(int orderId){
        return orderRepositoryImpl.checkOrderStatus(orderId);
    }

    public boolean cancelOrder(int orderId) {
        //get order status and deny the process if it's delivered
        if(checkOrderStatus(orderId)=="completed" || checkOrderStatus(orderId)=="returned"){
            return false;
        }else{
            return orderRepositoryImpl.updateStatus(orderId,OrderStatus.cancelled);
        }
    }

    public boolean updateStatus(int orderId){
        switch(orderRepositoryImpl.checkOrderStatus(orderId)) {
            case "placed":
                return orderRepositoryImpl.updateStatus(orderId, OrderStatus.shipped);
            case "shipped":
                return orderRepositoryImpl.updateStatus(orderId, OrderStatus.delivered);
            default: //returned
               return orderRepositoryImpl.updateStatus(orderId, OrderStatus.returned);
        }
    }

    public void checkOut(Customer customer) {
        orderRepositoryImpl.checkOut(customer);
    }
}
