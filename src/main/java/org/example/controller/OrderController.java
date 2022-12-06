package org.example.controller;
import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.entity.OrderDetails;
import org.example.model.Response;
import org.example.service.order.OrderService;
import org.example.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Omar Abu ElKeir, Vodafone
//Omar: create order, get orders, view order details, cancel order, CheckOut, update status.

@Controller
@RequestMapping("/orders")
@SessionAttributes("userId")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/details/{id}")
    public String getOrderDetails(@PathVariable("id") int orderId, Model model) {
        Response<List<OrderDetails>> orderDetails = orderService.getOrderDetails(orderId);
        if(orderDetails.isErrorOccurred()) {
            model.addAttribute("statusCode", orderDetails.getStatusCode());
            model.addAttribute("errorMessage", orderDetails.getMessage());
            return"error";
        }
        model.addAttribute("orderDetails", orderDetails.getObjectToBeReturned());
        return "viewOrderDetails";
    }

    @GetMapping("/view")
    public String getOrders(Model model) {
        int userId = (int) model.getAttribute("userId");
        Response<List<Order>> orders = orderService.getOrders(userId);
        if(orders.isErrorOccurred()) {
            model.addAttribute("statusCode", orders.getStatusCode());
            model.addAttribute("errorMessage", orders.getMessage());
            return"error";
        }
        model.addAttribute("orders", orders.getObjectToBeReturned());
        return "viewOrder";
    }

    @PostMapping("/placeOrder")
    public String placeOrder(Model model) {
        int userId = (int) model.getAttribute("userId");
        Customer customer = (Customer) userService.getUserById(userId).getObjectToBeReturned();
        orderService.checkOut(customer);
        return "redirect:/orders/view";
    }
}