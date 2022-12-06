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
        int userId=0;
        try (Session session = factory.openSession()) {
            List<User> users = session.createQuery("FROM User", User.class).list();
            if (users.size() > 0) {
                for (User user : users) {
                    if (user.getEmail().equals(email)) {
                        userId=user.getId();
                    }
                }
                if(userId==0)
                    return null;
                //int userId = (session.createQuery("FROM User u where u.email=:email", User.class).setParameter("email", email).getSingleResult()).getId();
                User customer = session.get(User.class, userId);
                if (customer.getPassword().equals(password)) {
                    customer.setPasswordAttempts(0);
                    return customer;
                }
                Transaction tx = session.beginTransaction();
                customer.setPasswordAttempts(customer.getPasswordAttempts() + 1);
                if (customer.getPasswordAttempts() >= 3) {
                    customer.setStatus(CustomerStatus.SUSPENDED);
                }
                session.merge(customer);
                tx.commit();
            }
        } catch (Exception ex) {
            return null;
        }
        return null;
    }

    public boolean verifyEmail(String email) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.createQuery("update User c set c.status=:status where c.email=:email").setParameter("status", CustomerStatus.ACTIVATED).setParameter("email", email).executeUpdate();
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
                System.out.println("**********************");
                System.out.println("checkIf :"+customer);
                System.out.println("**********************");

                if (customer.getStatus()==CustomerStatus.ACTIVATED) {
                    return true;
                }
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }

    public boolean checkIfUserAlreadyExists(String email) {
        try (Session session = factory.openSession()) {
            int userId = (session.createQuery("FROM User u where u.email=:email", User.class).setParameter("email", email).getSingleResult()).getId();
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
