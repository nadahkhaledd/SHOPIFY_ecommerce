package org.example.repository.shoppingcartproducts;

import org.example.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

public class ShoppingCartProductsRepositoryImpl implements ShoppingCartProductsRepository {

    private final SessionFactory factory;

    @Autowired
    public ShoppingCartProductsRepositoryImpl(SessionFactory factory) {
        this.factory = factory;
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
