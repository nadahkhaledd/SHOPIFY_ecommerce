//package org.example.controller;
//import org.example.service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
////Omar Abu ElKeir, Vodafone
////Omar: create order, get orders, view order details, cancel order, CheckOut, update status.
//
//@Controller
//@RequestMapping("/orders")
//public class OrderController {
//    @Autowired
//    OrderService orderService;
//
//    @GetMapping(name = "/getOrders")
//    public List getOrders(int userId){
//        return orderService.getOrders(userId);
//    }
//
//    @GetMapping(name="/getOrderDetails")
//    public List getOrderDetails(Long orderId){
//        return orderService.getOrderDetails(orderId);
//    }
//
//    @PutMapping(name = "/cancelOrder")
//    public boolean cancelOrder(Long orderId){
//       return orderService.cancelOrder(orderId);
//    }
//
//    @PostMapping(name = "/checkout")
//    public void checkOut(int userId){
//        orderService.checkOut(userId);
//    }
//}