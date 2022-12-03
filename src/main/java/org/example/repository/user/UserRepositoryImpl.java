package org.example.repository.user;

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


    @Override
    public User getUser(int userId) {
        List<User> users;
        try(Session session = factory.openSession()) {
            users = session.createQuery("from User where id=:userId", User.class)
                    .setParameter("userId", userId)
                    .getResultList();
        }
        return users.get(0);
    }
}
