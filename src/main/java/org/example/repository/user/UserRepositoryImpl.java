package org.example.repository.user;

import jdk.jfr.Registered;
import org.example.entity.Admin;
import org.example.entity.Product;
import org.example.entity.User;
import org.example.model.Response;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final SessionFactory factory;

    @Autowired
    public UserRepositoryImpl(SessionFactory factory) {
        this.factory = factory;
    }

     /**
     * @return
     */
    @Override
    public Response<User> getUserById(int userId) {
        User user;
        try(Session session=factory.openSession()){
            session.beginTransaction();
            user= (User) session.createQuery("from User where id= :userId")
                    .setParameter("userId",userId).getSingleResult();
            //     session.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println("in get user by id user repo impl e.getStackTrace() = " + e.getStackTrace().toString());
            return new Response("error occurred while processing your request", 500, true);
        }

        return new Response<User>("Done", 200, false, user);
    }

    @Override
    public Response<String> getUsernameByID(int userId) {
        String result;
        try (Session session = factory.openSession()) {
            result = session.createQuery("SELECT u.firstName from User u WHERE u.id=:id", String.class)
                    .setParameter("id", userId)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("in UserRepositoryImpl.getUsernameByID e.getMessage() = " + e.getMessage());
            return new Response("error occurred while processing your request", 500, true);

        }
        return new Response("Done", 200, false, result);
    }

    @Override
    public Response<User> getUser(int id, String email) {
        User user;
        try (Session session = factory.openSession()) {

            user = session.createQuery("from User u WHERE u.id=:id AND u.email=:email", User.class)
                    .setParameter("id", id)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("in UserRepositoryImpl.getUser e.getStackTrace() = " + e.getStackTrace().toString());
            return new Response("error occurred while processing your request", 500, true);

        }
        return new Response("Done", 200, false, user);
    }
}
