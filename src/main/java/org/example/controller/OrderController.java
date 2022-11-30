package org.example.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @GetMapping(name = "/getOrders")
    public String getOrders(Long userId){
        return "";
    }

    @GetMapping(name="/getOrderDetails")
    public String getOrderDetails(Long userId, Long orderId){
        return "";
    }

    @GetMapping(name = "/cancelOrder")
    public String cancelOrder(Long orderId,Long userId){
        return "";
    }

    @DeleteMapping(name = "/deleteOrderItem")
    //before order approval
    public String deleteOrderItem(Long productId, Long userId){
        return "";
    }

    @PutMapping(name = "/updateOrderItemQty")
    public String updateOrderDetailQty(Long productId, Long userId, int Qty){
        return "";
    }

}