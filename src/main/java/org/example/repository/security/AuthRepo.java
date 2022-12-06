package org.example.repository.security;

import org.example.entity.Customer;
import org.example.entity.User;
import org.example.enums.CustomerStatus;
import org.example.model.Response;
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

    public Response<Boolean> register(User user) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
        } catch (Exception e) {
            System.out.println("error in register auth repo " + e.toString());
            return new Response<Boolean>("error occurred while processing your request", 500, true, false, false);

        }
        return new Response<Boolean>("OK", 200, false, false, true);

    }

    public Response<User> checkLoginCredential(String email, String password) {
        int userId = -1;
        try (Session session = factory.openSession()) {
            List<User> users = session.createQuery("FROM User", User.class).list();
            if (!users.isEmpty()) {
                for (User user : users) {
                    if (user.getEmail().equals(email) ) {
                        userId = user.getId();
                    }
                }
                if (userId == -1)
                    return new Response<User>("email or password is wrong", 404, true, true, null);
                //int userId = (session.createQuery("FROM User u where u.email=:email", User.class).setParameter("email", email).getSingleResult()).getId();
                User customer = session.get(User.class, userId);
                Transaction tx = session.beginTransaction();
                if (customer.getPassword().equals(password)) {
                        customer.setPasswordAttempts(0);
                        session.merge(customer);
                        tx.commit();
                    return new Response<User>("OK", 200, false, false, customer);

                }
                customer.setPasswordAttempts(customer.getPasswordAttempts() + 1);
                System.out.println("*****************");
                System.out.println(customer.getPasswordAttempts());
                System.out.println("*******************");
                session.merge(customer);
                if (customer.getPasswordAttempts() > 3) {
                    customer.setStatus(CustomerStatus.SUSPENDED);
                    session.merge(customer);
                }
                tx.commit();

            }
        } catch (Exception e) {
            System.out.println("error in check login credentials auth repo " + e.toString());
            return new Response<User>("error occurred while processing your request", 500, true, false, null);

        }
        return new Response<User>("email or password is wrong", 404, true, true, null);
    }

    public Response<Boolean> verifyEmail(String email) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.createQuery("update User c set c.status=:status where c.email=:email").setParameter("status", CustomerStatus.ACTIVATED).setParameter("email", email).executeUpdate();
            tx.commit();

            //return true;
        } catch (Exception e) {
            System.out.println("error in verify email auth repo " + e.getStackTrace().toString());
            return new Response<Boolean>("error occurred while processing your request", 500, true, false, false);
        }
        return new Response<Boolean>("Ok", 200, false, false, true);
    }

    public Response<Boolean> checkIfActivated(int userId) {
        try (Session session = factory.openSession()) {
            User customer = session.get(User.class, userId);
            if (customer != null) {
                if (customer.getStatus() == CustomerStatus.ACTIVATED) {
                    return new Response<>("Ok", 200, false, false, true);
                } else
                    return new Response<>("customer not activated", 200, false, false, false);

            }
            return new Response<>("customer not found", 404, true, false, false);
        } catch (Exception e) {
            System.out.println("error in check if activated auth repo " + e.getStackTrace().toString());
            return new Response<Boolean>("error occurred while processing your request", 500, true, false, false);

        }
    }

    public Response<Boolean> checkIfUserAlreadyExists(String email) {
        System.out.println("email " + email);
        try (Session session = factory.openSession()) {
            List<User> user = session.createQuery("FROM User u where u.email=:email", User.class).setParameter("email", email).list();
            if (user.isEmpty()) {
                return new Response<>("Ok", 200, false, false, false);
            } else {
                return new Response<>("you already have an account please login directly", 400, true, true, true);
            }

        } catch (Exception e) {
            System.out.println("error occured in check if user already exists " + e.toString());
            return new Response<Boolean>("error occurred while processing your request", 500, true, false, false);
        }
    }

    public boolean checkIfSuspended(String email) {

        try (Session session = factory.openSession()) {
            int userId = (session.createQuery("FROM User u where u.email=:email", User.class).setParameter("email", email).getSingleResult()).getId();
            User customer = session.get(User.class, userId);
            if (customer.getStatus().equals(CustomerStatus.SUSPENDED)) {
                return true;
            }
            System.out.println("(((((((((((((((((((())))))))))))))))))))");
            System.out.println(customer.getStatus());
            System.out.println("(((((((((((((((((((())))))))))))))))))))");

        }catch (Exception e){
            return false;
        }
        return false;
    }
}
