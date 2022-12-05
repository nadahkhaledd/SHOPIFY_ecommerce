package org.example.repository.order;

import org.example.entity.*;
import org.example.enums.OrderStatus;
import org.example.service.shoppingcartproducts.ShoppingCartProductsService;
import org.example.service.user.UserService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final SessionFactory sessionFactory;
    private final ShoppingCartProductsService cartProductsService;

    @Autowired
    public OrderRepositoryImpl(SessionFactory sessionFactory, ShoppingCartProductsService cartProductsService, UserService userService) {
        this.sessionFactory = sessionFactory;
        this.cartProductsService = cartProductsService;
    }


    public List<Order> getOrders(Customer customer) {
        List<Order> orders;
        try (Session session = sessionFactory.openSession()){
            orders = session.createQuery("from Order where customer=:customer",Order.class)
                    .setParameter("customer", customer).list();
        }
        return orders;
    }

    public Order getOrderById(int orderId) {
        try(Session session = sessionFactory.openSession()) {
            return session.createQuery("from Order where id=:orderId", Order.class)
                    .setParameter("orderId", orderId)
                    .getSingleResult();
        }
    }

    public List<OrderDetails> getOrderDetails(Order order) {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("from OrderDetails where order=:order",OrderDetails.class).setParameter("order", order).list();
        }
    }
    //ok
    public String checkOrderStatus(int orderId) {
        Session session = this.sessionFactory.getCurrentSession();
        Order order = session.get(Order.class, orderId);
        return order.getStatus().toString();
    }

    public boolean updateStatus(int orderId, OrderStatus status) {
        Session session = this.sessionFactory.getCurrentSession();
        Order order = session.get(Order.class, orderId);
        if (order != null) {
            order.setStatus(status);
            return true;
        }
        return false;
    }

//    public void checkOut(int userId) {
//        //***summary***
//        //create an order
//        //loop
//        //create an order details products
//        //delete shopping cart products
//        //endLoop
//        //add order total price
//
//        try (Session session = this.sessionFactory.getCurrentSession()){
//            User user = session.get(User.class, userId);
//            Order order = new Order((Customer) user,LocalDate.now(),OrderStatus.placed,0.0);
//            Set<OrderDetails> orderDetails;
//            Double totalOrderPrice = 0.0;
//            for(ShoppingCartProducts s: user.getShoppingCartProducts()){
//                OrderDetails od = new OrderDetails(order,s.getProduct().getName(),s.getProduct().getPrice(),s.getProduct().getImagePath());
//                totalOrderPrice+=s.getProduct().getPrice()*s.getProductQuantity();
//                ShoppingCartProducts shoppingCartProducts= session.get(ShoppingCartProducts.class,s.getId());
//                session.delete(shoppingCartProducts);
//            }
//            Order order1= session.get(Order.class,order.getId());
//            order1.setTotal(totalOrderPrice);
//
//        }catch (HibernateException e){
//            e.printStackTrace();
//        }
//    }

    public void checkOut(Customer customer) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Order order = new Order(customer, LocalDate.now(), OrderStatus.placed, 0.0);
            Double totalOrderPrice = 0.0;
            for(ShoppingCartProducts cartItems : customer.getShoppingCartProducts()) {
                OrderDetails od = new OrderDetails(order, cartItems.getProduct().getName(), cartItems.getProduct().getPrice(),cartItems.getProduct().getImagePath());
                session.persist(od);
                totalOrderPrice += cartItems.getProduct().getPrice() * cartItems.getProductQuantity();
                cartProductsService.removeFromCart(cartItems.getId());
            }
            order.setTotal(totalOrderPrice);
            session.persist(order);
            tx.commit();
        }
    }

    public Double calculateTotal(int orderId) {
        Double result;
        try(Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(
                    "select sum(od.productPrice*od.productQuantity) from order_details od inner join orders o where o.id=:id"
            ).setParameter("id", orderId);
            result = (Double) query.getSingleResult();
            if(result == null)
                return 0.0;
        }
        return result;
    }
}