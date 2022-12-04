package org.example.controller;

import org.example.service.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orderdetails")
public class OrderDetailsController {
    private final OrderServiceImpl orderServiceImpl;

    @Autowired
    public OrderDetailsController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }
}
