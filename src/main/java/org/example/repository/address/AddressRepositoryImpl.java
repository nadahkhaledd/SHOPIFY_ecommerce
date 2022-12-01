package org.example.repository.address;

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
public class AddressRepositoryImpl implements AddressRepository {

    private final SessionFactory factory;

    @Autowired
    public AddressRepositoryImpl(SessionFactory factory) {
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
}
