package org.example.service.order;

import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.entity.OrderDetails;
import org.example.enums.OrderStatus;
import org.example.model.Response;

import java.util.List;

public interface OrderService {

    Response<List<Order>> getOrders(int userId, OrderStatus status);
    Response<Order> getOrderById(int orderId);
    Response<List<OrderDetails>> getOrderDetails(int orderId);
    Response checkOrderStatus(int orderId);
   // Response<Boolean> cancelOrder(int orderId);
   Response<Boolean> updateStatus(int orderId, OrderStatus status);
    Response checkOut(Customer customer, Order order);

}
