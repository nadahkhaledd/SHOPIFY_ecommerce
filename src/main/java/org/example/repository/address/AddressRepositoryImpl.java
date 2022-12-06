package org.example.repository.address;

import org.example.entity.*;
import org.example.model.Response;
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
    public Response addAddress(Address address) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(address);
            tx.commit();
        } catch (Exception e) {
            System.out.println("in AddressRepositoryImpl.addAddress stacktrace = " + e.getStackTrace());
            return new Response("error occurred while processing your request", 500, true);
        }
        return new Response("Done", 200, false, address);
    }

    @Override
    public Response<List<Address>> getUserAddresses(int userId) {
        List<Address> addresses;
        try (Session session = factory.openSession()) {
            addresses = session
                    .createQuery("select a from Address a inner join a.customer c where c.id=:userId",
                            Address.class)
                    .setParameter("userId", userId)
                    .getResultList();
        } catch (Exception e) {
            System.out.println("in AddressRepositoryImpl.getUserAddresses stacktrace = " + e.getStackTrace());
            return new Response("error occurred while processing your request", 500, true);
        }
        return new Response<List<Address>>("Done",200,false,addresses) ;
    }

    @Override
    public Response<Address> getAddress(int addressId) {
        Address address;
        try(Session session = factory.openSession()) {
            address = session
                    .createQuery("from Address where id=:id", Address.class)
                    .setParameter("id", addressId)
                    .getSingleResult();
        } catch(Exception e) {
            System.out.println("in AddressRepositoryImpl.getAddress stacktrace = " + e.getStackTrace());
            return new Response("error occurred while processing your request", 500, true);
        }
        return new Response<Address>("Done", 200, false, address);
    }

    @Override
    public Response<Address> updateAddress(Address address) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
//            Query updateQuery = session
//                    .createQuery("update Address a set a.street=:street, a.city=:city, " +
//                    "a.buildingNumber=:buildingNumber where a.id=:addressId");
//            updateQuery.setParameter("city", address.getCity());
//            updateQuery.setParameter("buildingNumber", address.getBuildingNumber());
//            updateQuery.setParameter("street", address.getStreet());
//            updateQuery.setParameter("addressId", address.getId());
            session.merge(address);
            //result = updateQuery.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            System.out.println("in AddressRepositoryImpl.updateAddress stacktrace = " + e.getStackTrace());
            return new Response("error occurred while processing your request", 500, true);
        }
        return new Response<Address>("Done", 200, false, address);
    }

    @Override
    public Response<Address> deleteAddress(int addressId) {
        int result;
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query deleteQuery = session.createQuery("delete from Address a where a.id=:addressId");
            deleteQuery.setParameter("addressId", addressId);
            result = deleteQuery.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            System.out.println("in AddressRepositoryImpl.deleteAddress stacktrace = " + e.getStackTrace());
            return new Response("error occurred while processing your request", 500, true);
        }
        return new Response<Address>("Done", 200, false, result == 1);
    }
}
