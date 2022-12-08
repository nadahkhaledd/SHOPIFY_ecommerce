package org.example.repository.order;

import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.entity.OrderDetails;
import org.example.enums.OrderStatus;
import org.example.model.Response;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public interface OrderRepository {
    Response<List<Order>> getOrders(Customer customer, OrderStatus status);
    Response<List<OrderDetails>> getOrderDetails(Order order);
    Response<Order> getOrderById(int orderId);
    Response checkOrderStatus(int orderId);
    Response<Boolean> updateStatus(int orderId, OrderStatus status);
    Response<Double> calculateTotal(int orderId);
    Response checkOut(Customer customer, Order order);
}
