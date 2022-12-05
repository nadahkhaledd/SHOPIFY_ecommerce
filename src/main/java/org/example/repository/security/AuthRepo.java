package org.example.repository.security;

import org.example.entity.User;
import org.example.enums.CustomerStatus;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        }catch (Exception e){
            return false;
        }

    return true;
    }
    public User checkLoginCredential(String email, String password) {
         try (Session session = factory.openSession()){
             List<User> users = session.createQuery("FROM User", User.class).list();
             if (users.size() > 0) {
                 int userId = (session.createQuery("FROM User u where u.email=:email", User.class).setParameter("email",email).getSingleResult()).getId();
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
         }catch (HibernateException ex){
                return null;
         }
        return null;
    }


    public boolean verifyEmail(final String email) {
        try(Session session = this.factory.openSession())
         {
            int userId = (session.createQuery("FROM User u where u.email=:email", User.class).setParameter("email",email).getSingleResult()).getId();
            User customer = session.get(User.class, userId);
            if (customer != null) {
                customer.setStatus(CustomerStatus.ACTIVATED);
                return true;
            }
        }
        catch (Throwable t) {
            return false;
        }
        return false;
    }

}
