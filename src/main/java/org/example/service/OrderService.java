package org.example.service;

import org.example.entity.Order;
import org.example.enums.OrderStatus;
import org.example.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderService {
    @Autowired
    OrderRepo orderRepo;

    public List getOrders(Long userId) {

        return orderRepo.getOrders(userId);
    }

    public List getOrderDetails(Long orderId) {
        return orderRepo.getOrderDetails(orderId);
    }

    public String checkOrderStatus(Long orderId){
        return orderRepo.checkOrderStatus(orderId);
    }

    public boolean cancelOrder(Long orderId) {
        //get order status and deny the process if it's delivered
        if(checkOrderStatus(orderId)=="completed" || checkOrderStatus(orderId)=="returned"){
            return false;
        }else{
            return orderRepo.updateStatus(orderId,OrderStatus.cancelled);
        }
    }

    public boolean updateStatus(Long orderId){
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
