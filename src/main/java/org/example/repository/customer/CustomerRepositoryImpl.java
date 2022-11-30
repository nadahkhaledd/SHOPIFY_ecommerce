package org.example.repository.customer;

import org.example.entity.Address;
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
public class CustomerRepositoryImpl implements CustomerRepository {

    private final SessionFactory factory;

    @Autowired
    public CustomerRepositoryImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void addAddress(Customer customer, Address address) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            address.setCustomer(customer);
            session.save(address);
            tx.commit();
        }
    }

    @Override
    public List<Address> getUserAddresses(Customer customer) {
        List<Address> addresses;
        try (Session session = factory.openSession()) {
            addresses = session
                    .createQuery("from Address where customer=:id")
                    .setParameter("id", customer)
                    .getResultList();
        }
        return addresses;
    }

    @Override
    public int updateAddress(Address address) {
        int result;
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query updateQuery = session
                    .createQuery("update Address a set a.street=:street, a.city=:city, " +
                    "a.buildingNumber=:buildingNumber where a.id=:addressId");
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
    public int deleteAddress(Address address) {
        int result;
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query deleteQuery = session.createQuery("delete from Address a where a.id=:addressId");
            deleteQuery.setParameter("addressId", address.getId());
            result = deleteQuery.executeUpdate();
            tx.commit();
        }
        return result;
    }

    @Override
    public void addToCart(Product product, Customer customer, ShoppingCartProducts shoppingCartProduct) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            shoppingCartProduct.setCustomer(customer);
            shoppingCartProduct.setProduct(product);
            session.save(shoppingCartProduct);
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
}
