package org.example.repository.security;

import org.example.entity.Customer;
import org.example.entity.User;
import org.example.enums.CustomerStatus;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

@Repository
public class AuthRepo {

    private final SessionFactory factory;

    @Autowired
    public AuthRepo(SessionFactory factory) {
        this.factory = factory;
    }

    public boolean register(User user) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public User checkLoginCredential(String email, String password) {
        try (Session session = factory.openSession()) {
            List<User> users = session.createQuery("FROM User", User.class).list();
            if (users.size() > 0) {
                int userId = (session.createQuery("FROM User u where u.email=:email", User.class).setParameter("email", email).getSingleResult()).getId();
                User customer = session.get(User.class, userId);
                if (customer != null) {
                    if (customer.getPassword().equals(password)) {
                        customer.setPasswordAttempts(0);
                        return customer;
                    }
                    customer.setPasswordAttempts(customer.getPasswordAttempts() + 1);
                    if (customer.getPasswordAttempts() >= 3) {
                        customer.setStatus(CustomerStatus.SUSPENDED);
                    }
                }
            }
        } catch (Exception ex) {
            return null;
        }
        return null;
    }

    public boolean verifyEmail(final String email) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery(
                    "update User c set c.status=:status" +
                            " where c.email:=email"
            );
            query.setParameter("status", CustomerStatus.ACTIVATED);
            query.setParameter("email", email);
            query.executeUpdate();
            tx.commit();
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    public boolean checkIfActivated(int userId) {
        try (Session session = factory.openSession()) {
            User customer = session.get(User.class, userId);
            if (customer != null) {
                if (customer.getStatus().toString().equals("ACTIVATED")) {
                    return true;
                }
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }

    public boolean checkIfUserAlreadyExists(int userId) {
        try (Session session = factory.openSession()) {
            User customer = session.get(User.class, userId);
            if (customer != null) {
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }




}
