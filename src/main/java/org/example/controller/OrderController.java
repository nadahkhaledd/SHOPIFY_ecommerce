/*package org.example.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @GetMapping(name = "/getOrders")
    public String getOrders(int userId){
        return "";
    }

    @GetMapping(name="/getOrderDetails")
    public String getOrderDetails(int userId, int orderId){
        return "";
    }

    @GetMapping(name = "/cancelOrder")
    public String cancelOrder(int orderId,int userId){
        return "";
    }

    @DeleteMapping(name = "/deleteOrderItem")
    //before order approval
    public String deleteOrderItem(Long productId, Long userId){
        return "";
    }

    @PutMapping(name = "/updateOrderItemQty")
    public String updateOrderDetailQty(int productId, int userId, int Qty){
        return "";
    }

}*/