package org.example.repository.shoppingcartproducts;

import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.entity.ShoppingCartProducts;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShoppingCartProductsRepositoryImpl implements ShoppingCartProductsRepository {

    private final SessionFactory factory;

    @Autowired
    public ShoppingCartProductsRepositoryImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<ShoppingCartProducts> viewCart(Customer customer) {
        List<ShoppingCartProducts> shoppingCartProducts;
        try(Session session = factory.openSession()) {
            shoppingCartProducts = session.
                    createQuery("from ShoppingCartProducts where customer=:id")
                    .setParameter("id", customer)
                    .getResultList();
        }
        return shoppingCartProducts;
    }

    @Override
    public void addToCart(ShoppingCartProducts shoppingCartProduct) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(shoppingCartProduct);
            tx.commit();
        }
    }

    @Override
    public int updateProductQuantityInCart(int shoppingCartProductId, int newQuantity) {
        int result;
        try(Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query updateQuery = session
                    .createQuery("update ShoppingCartProducts set product_quantity=:newQuantity" +
                            " where id=:shoppingCartProductId");
            updateQuery.setParameter("newQuantity", newQuantity);
            updateQuery.setParameter("shoppingCartProductId", shoppingCartProductId);
            result = updateQuery.executeUpdate();
            tx.commit();
        }
        return result;
    }

    @Override
    public int removeFromCart(int shoppingCartProductId) {
        int result;
        try(Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query deleteQuery = session.createQuery("delete from ShoppingCartProducts scp " +
                    "where scp.id=:shoppingCartProductId");
            deleteQuery.setParameter("shoppingCartProductId",shoppingCartProductId);
            result = deleteQuery.executeUpdate();
            tx.commit();
        }
        return result;
    }
    @Override
    public double calculateTotal(Customer customer) {
        double result;
        try(Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("select sum(p.price*scp.productQuantity) from ShoppingCartProducts scp " +
                    "LEFT JOIN Product p ON scp.product=p.id where scp.customer=:customerId");
            query.setParameter("customerId", customer);
            result = (Double) query.getSingleResult();
            tx.commit();
        }
        return result;
    }

}
