package org.example.controller;
import org.example.entity.Address;
import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.entity.OrderDetails;
import org.example.enums.OrderStatus;
import org.example.model.Response;
import org.example.service.address.AddressService;
import org.example.service.order.OrderService;
import org.example.service.user.UserService;
import org.example.typeEditor.AddressEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

//Omar Abu ElKeir, Vodafone
//Omar: create order, get orders, view order details, cancel order, CheckOut, update status.

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final AddressService addressService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService, AddressService addressService) {
        this.orderService = orderService;
        this.userService = userService;
        this.addressService = addressService;
    }

    @InitBinder
    public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
    {
        binder.registerCustomEditor(Address.class,
                new AddressEditor(addressService));
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
    public String getOrders(Model model, HttpSession session) {
        int userId =  (int) session.getAttribute("user-Id");
        Response<List<Order>> orders = orderService.getOrders(userId, OrderStatus.placed);
        if(orders.isErrorOccurred()) {
            model.addAttribute("statusCode", orders.getStatusCode());
            model.addAttribute("errorMessage", orders.getMessage());
            return"error";
        }
        model.addAttribute("orders", orders.getObjectToBeReturned());
        return "viewOrder";
    }


    @PostMapping("/placeOrder")
    public String placeOrder(@ModelAttribute("newOrder") Order order,
                              Model model,HttpSession session) {
        int userId = (int) session.getAttribute("user-Id");
        Customer customer = (Customer) userService.getUserById(userId).getObjectToBeReturned();
        Response response = orderService.checkOut(customer, order);
        if(response.isErrorOccurred()) {
            model.addAttribute("statusCode", response.getStatusCode());
            model.addAttribute("errorMessage", response.getMessage());
            return"error";
        }
        return "redirect:/orders/view";
    }

    @GetMapping("/cancel/{id}")
    public String cancelOrder(@PathVariable("id") int id, Model model) {
        Response response = orderService.updateStatus(id, OrderStatus.cancelled);
        if(response.isErrorOccurred()) {
            model.addAttribute("statusCode", response.getStatusCode());
            model.addAttribute("errorMessage", response.getMessage());
            return"error";
        }
        return "redirect:/orders/view";
    }
}