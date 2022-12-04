//package org.example.repository;
//
//
//
//import org.example.entity.Order;
//import org.example.entity.OrderDetails;
//import org.example.entity.ShoppingCartProducts;
//import org.example.entity.User;
//import org.example.enums.OrderStatus;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//
//
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//
//
//@Repository
//public class OrderRepo {
//
//
//
//    private final SessionFactory sessionFactory;
//
//
//
//    @Autowired
//    public OrderRepo(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//
//
//
//    public List<Order> getOrders(int userId) {
//        try (Session session = this.sessionFactory.getCurrentSession()){
//            return  session.createQuery("from Order where userId=:userId",Order.class).setParameter("userId",userId).list();
//        }catch (HibernateException e){
//            return new ArrayList<>();
//        }
//    }
//
//
//
//    public List getOrderDetails(Long orderId) {
//        try(Session session = this.sessionFactory.getCurrentSession()){
//            return session.createQuery("from OrderDetails where order_id =:orderId",OrderDetails.class).setParameter("orderId", orderId).list();
//        }catch (HibernateException e){
//            return new ArrayList();
//        }
//    }
//    //ok
//    public String checkOrderStatus(Long orderId) {
//        Session session = this.sessionFactory.getCurrentSession();
//        Order order = session.get(Order.class, orderId);
//        return order.getStatus().toString();
//    }
//
//
//
//    public boolean updateStatus(Long orderId, OrderStatus status) {
//        Session session = this.sessionFactory.getCurrentSession();
//        Order order = session.get(Order.class, orderId);
//        if (order != null) {
//            order.setStatus(status);
//            return true;
//        }
//        return false;
//    }
//
//
//
//    public void checkOut(int userId) {
//        //***summary***
//        //create an order
//        //loop
//        //create an order details products
//        //delete shopping cart products
//        //endLoop
//        //add order total price
//
//
//
//        try (Session session = this.sessionFactory.getCurrentSession()){
//            User user = session.get(User.class, userId);
//            Order order = new Order(userId,LocalDate.now(),OrderStatus.placed,null);
//            Set<OrderDetails> orderDetails;
//            int totalOrderPrice = 0;
//            for(ShoppingCartProducts s: user.getShoppingCartProducts()){
//                OrderDetails od = new OrderDetails(order.getId(),s.getProduct().getName(),s.getProduct().getPrice(),s.getProduct().getImagePath());
//                totalOrderPrice+=s.getProduct().getPrice()*s.getProductQuantity();
//                ShoppingCartProducts shoppingCartProducts= session.get(ShoppingCartProducts.class,s.getId());
//                session.delete(shoppingCartProducts);
//            }
//            Order order1= session.get(Order.class,order.getId());
//            order1.setTotal(totalOrderPrice);
//
//
//
//        }catch (HibernateException e){
//            e.printStackTrace();
//        }
//    }
//}