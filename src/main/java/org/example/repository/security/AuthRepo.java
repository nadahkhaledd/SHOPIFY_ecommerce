
package org.example.repository.security;

import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import java.util.List;
import org.hibernate.Session;
import org.example.enums.CustomerStatus;
import java.io.Serializable;
import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepo
{
    private final SessionFactory factory;

    @Autowired
    public AuthRepo(final SessionFactory factory) {
        this.factory = factory;
    }

    public User checkLoginCredential(final String email, final String password) {
        final Session session = this.factory.openSession();
        try {
            final List<User> users = (List<User>)session.createQuery("FROM User", (Class)User.class).list();
            if (users.size() > 0) {
                try {
                     int userId = ((User)session.createQuery("FROM User u where u.email=:email", (Class)User.class).setParameter("email", (Object)email).getSingleResult()).getId();
                     User customer = (User)session.get((Class)User.class, (Serializable)userId);
                    if (customer != null) {
                        if (customer.getPassword().equals(password)) {
                            customer.setPasswordAttempts(0);
                            final User user = customer;
                            if (session != null) {
                                session.close();
                            }
                            return user;
                        }
                        customer.setPasswordAttempts(customer.getPasswordAttempts() + 1);
                        if (customer.getPasswordAttempts() >= 3) {
                            customer.setStatus(CustomerStatus.SUSPENDED);
                        }
                    }
                }
                catch (Exception e) {
                    final User user2 = null;
                    if (session != null) {
                        session.close();
                    }
                    return user2;
                }
            }
            if (session != null) {
                session.close();
            }
        }
        catch (Throwable t) {
            if (session != null) {
                try {
                    session.close();
                }
                catch (Throwable exception) {
                    t.addSuppressed(exception);
                }
            }
            throw t;
        }
        return null;
    }

    public boolean register(final User user) {
        try {
            final Session session = this.factory.openSession();
            try {
                final Transaction tx = session.beginTransaction();
                session.persist((Object)user);
                tx.commit();
                if (session != null) {
                    session.close();
                }
            }
            catch (Throwable t) {
                if (session != null) {
                    try {
                        session.close();
                    }
                    catch (Throwable exception) {
                        t.addSuppressed(exception);
                    }
                }
                throw t;
            }
        }
        catch (HibernateException e) {
            return false;
        }
        return true;
    }

    public boolean verifyEmail(final String email) {
        final Session session = this.factory.openSession();
        try {
            final List<User> users = (List<User>)session.createQuery("FROM User", (Class)User.class).list();
            if (users.size() > 0) {
                int userId = users.stream().filter(user -> user.getEmail().equals(email)).findFirst().get().getId();
                User customer = session.get(User.class, userId);
                if (customer != null) {
                    customer.setStatus(CustomerStatus.ACTIVATED);
                    final boolean b = true;
                    if (session != null) {
                        session.close();
                    }
                    return b;
                }
            }
            if (session != null) {
                session.close();
            }
        }
        catch (Throwable t) {
            if (session != null) {
                try {
                    session.close();
                }
                catch (Throwable exception) {
                    t.addSuppressed(exception);
                }
            }
            throw t;
        }
        return false;
    }
}