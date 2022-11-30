package org.example.repository.customer;

import org.example.entity.Address;
import org.example.entity.ShoppingCartProducts;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final SessionFactory factory;

    @Autowired
    public CustomerRepositoryImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void addAddress(Address address) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(address);
            tx.commit();
        }
    }

    @Override
    public List<Address> getAllAddresses(int customerId) {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Address a where a.customer_id=:customerId" , Address.class)
                    .setParameter("customerId", customerId)
                    .list();
        }
    }

    @Override
    public int updateAddress(int addressId, Address address) {
        int result;
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query updateQuery = session.createQuery("update Address a set a.street=:street and a.city=:city" +
                    "and a.buildingNumber=:buildingNumber where a.id=:addressId",
                    Address.class);
            updateQuery.setParameter("city", address.getCity());
            updateQuery.setParameter("buildingNumber", address.getBuildingNumber());
            updateQuery.setParameter("street", address.getStreet());
            updateQuery.setParameter("addressId", address.getId());
            result = updateQuery.executeUpdate();
            tx.commit();
        }
        return result;
    }

    @Override
    public int deleteAddress(int addressId) {
        int result;
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query deleteQuery = session.createQuery("delete from Address a where a.id=:addressId");
            deleteQuery.setParameter("addressId", addressId);
            result = deleteQuery.executeUpdate();
            tx.commit();
        }
        return result;
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
            Query updateQuery = session.createQuery("update ShoppingCartProducts scp" +
                    "scp.product_quantity=:newQuantity" +
                    "where scp.id=:shoppingCartProductId");
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
}
