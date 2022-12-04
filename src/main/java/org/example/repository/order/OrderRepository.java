package org.example.repository.order;

import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.entity.OrderDetails;
import org.example.enums.OrderStatus;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public interface OrderRepository {
    List<Order> getOrders(int userId);
    List getOrderDetails(int orderId);
    String checkOrderStatus(int orderId);
    boolean updateStatus(int orderId, OrderStatus status);
    Double calculateTotal(int orderId);
    void checkOut(Customer customer);
}
