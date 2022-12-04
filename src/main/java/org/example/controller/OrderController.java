package org.example.controller;
import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.enums.OrderStatus;
import org.example.service.order.OrderService;
import org.example.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

//Omar Abu ElKeir, Vodafone
//Omar: create order, get orders, view order details, cancel order, CheckOut, update status.

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

 //   @GetMapping("/getOrders")
//    public List getOrders(int userId){
//        return orderServiceImpl.getOrders(userId);
//    }

    @GetMapping("/getOrderDetails")
    public String getOrderDetails(){
        return "detail";
    }

//    @PutMapping("/cancelOrder")
//    public boolean cancelOrder(int orderId){
//       return orderServiceImpl.cancelOrder(orderId);
//    }

    @GetMapping("/view")
    public String getOrders(@RequestParam int id, Model model) {
        List<Order> orders = orderService.getOrders(id);
        model.addAttribute("orders", orders);
        return "viewOrders";
    }
    @PostMapping("/placeOrder/{userId}")
    public String placeOrder(@PathVariable("userId") int userId) {
        Customer customer = (Customer) userService.getUserById(userId);
        orderService.checkOut(customer);
        return "contact";
    }

//    @PostMapping("/checkout")
//    public void checkOut(int userId){
//        orderServiceImpl.checkOut(userId);
//    }
}