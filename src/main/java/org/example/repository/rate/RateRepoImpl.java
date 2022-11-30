package org.example.repository.rate;

import org.example.entity.Rate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RateRepoImpl implements RateRepo {
    SessionFactory sessionFactory;
    @Autowired
    public RateRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void addRate(Rate rate){
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            session.persist(rate);//return type of persist is void
            session.getTransaction().commit();
        }
    }


    @Override
    public double calculateRateOfProduct(int productId) {
        double productRate=0;
            try(Session session=sessionFactory.openSession()){
                session.beginTransaction();
                //"select c.name , c.id FROM Course as c inner join c.students as s WHERE s.id=:studentId"
                productRate= (double) session.createQuery( "select avg(r.rate) FROM Rate as r "
                               +"inner join r.product as p "+"WHERE p.id = :productId").
                   setParameter("productId",productId).getSingleResult();
            }
            return productRate;

    }
  /*  public List<Float> getRates(int productId){
        List<Float> rates;
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            //"select c.name , c.id FROM Course as c inner join c.students as s WHERE s.id=:studentId"
            rates= session.createQuery( "select r.rate FROM Rate as r "
                            +"inner join r.product as p "+"WHERE p.id = :productId").
                    setParameter("productId",productId).
                    list();
        }
        return rates;
    }*/
}
