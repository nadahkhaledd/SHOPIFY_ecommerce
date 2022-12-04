package org.example.repository.user;

import jdk.jfr.Registered;
import org.example.entity.Product;
import org.example.entity.User;
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

     /**
     * @return
     */
    @Override
    public User getUserById(int userId) {
        User user;
        try(Session session=factory.openSession()){
            session.beginTransaction();
            user= (User) session.createQuery("from User where id= :userId")
                    .setParameter("userId",userId).getSingleResult();
            //     session.getTransaction().commit();
        }
        System.out.println(user.toString());
        return user;
    }
}
