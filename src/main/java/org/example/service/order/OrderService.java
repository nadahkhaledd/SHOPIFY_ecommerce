package org.example.service.order;

import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.entity.OrderDetails;
import org.example.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    List<Order> getOrders(int userId);
    Order getOrderById(int orderId);
    List<OrderDetails> getOrderDetails(int orderId);
    String checkOrderStatus(int orderId);
    boolean cancelOrder(int orderId);
    boolean updateStatus(int orderId);
    void checkOut(Customer customer);

}
