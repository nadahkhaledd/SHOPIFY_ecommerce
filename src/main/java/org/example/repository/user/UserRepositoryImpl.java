package org.example.repository.user;

import jdk.jfr.Registered;
import org.example.entity.Product;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements  UserRepository{
    SessionFactory sessionFactory;
    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    /**

    /**
     * @return 
     */
    @Override
    public User getUserById(int userId) {
        User user;
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            user= (User) session.createQuery("from User where id= :userId")
                    .setParameter("userId",userId).getSingleResult();
            //     session.getTransaction().commit();
        }
        System.out.println(user.toString());
        return user;
    }
}
