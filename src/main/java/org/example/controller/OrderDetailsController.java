package org.example.controller;

import org.example.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orderdetails")
public class OrderDetailsController {
    private final OrderService orderService;

    @Autowired
    public OrderDetailsController(OrderService orderService) {
        this.orderService = orderService;
    }
}
