package org.example.repository.customer;

import org.example.entity.Customer;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    SessionFactory sessionFactory;
    @Autowired
    public CustomerRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    /**

    /**
     * @return 
     */

    /**
     * @param userId 
     * @return
     */
    @Override
    public Customer getCustomerById(int userId) {
        Customer user;
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            user= (Customer) session.createQuery("from User where id= :userId")
                    .setParameter("userId",userId).getSingleResult();
            //     session.getTransaction().commit();
        }
        System.out.println(user.toString());
        return user;
    }
}
