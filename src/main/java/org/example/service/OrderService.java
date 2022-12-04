package org.example.service;

import org.example.entity.Order;
import org.example.enums.OrderStatus;
import org.example.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepo orderRepo;

    public List getOrders(int userId) {

        return orderRepo.getOrders(userId);
    }

    public List getOrderDetails(int orderId) {
        return orderRepo.getOrderDetails(orderId);
    }

    public String checkOrderStatus(int orderId){
        return orderRepo.checkOrderStatus(orderId);
    }

    public boolean cancelOrder(int orderId) {
        //get order status and deny the process if it's delivered
        if(checkOrderStatus(orderId)=="completed" || checkOrderStatus(orderId)=="returned"){
            return false;
        }else{
            return orderRepo.updateStatus(orderId,OrderStatus.cancelled);
        }
    }

    public boolean updateStatus(int orderId){
        switch(orderRepo.checkOrderStatus(orderId)) {
            case "placed":
                return orderRepo.updateStatus(orderId, OrderStatus.shipped);
            case "shipped":
                return orderRepo.updateStatus(orderId, OrderStatus.delivered);
            default: //returned
               return orderRepo.updateStatus(orderId, OrderStatus.returned);
        }
    }

    public void checkOut(int userId) {
        orderRepo.checkOut(userId);
    }
}
