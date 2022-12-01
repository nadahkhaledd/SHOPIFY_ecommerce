package org.example.repository.product;

import org.example.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepoImpl implements ProductRepo {
    SessionFactory sessionFactory;
    @Autowired
    public ProductRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addProduct(Product product){
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            session.persist(product);//return type of persist is void
            session.getTransaction().commit();
        }
    }
    @Override
    public void updateProduct(Product product){
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            session.merge(product);
            session.getTransaction().commit();
        }
    }
    @Override
    public void deleteProduct(Product product){
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            session.remove(product);//(delete vs remove)If you have the index of the item to be eliminated, del is probably best. But if you have the value that you want to eliminate, .remove() is likely to be best.
            session.getTransaction().commit();
        }
    }

    @Override
    public boolean updateProductRate(int productId, float rate){
        int rowsAffected=0;
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();

             rowsAffected= session.createQuery("UPDATE Product set rate = :rate " + "WHERE id = :productId").
                    setParameter("rate",rate).setParameter("productId",productId)
                    .executeUpdate();
            System.out.println(rowsAffected+" rowsAffected ");
            session.getTransaction().commit();
        }
        return rowsAffected!=0;
    }

    @Override
    public boolean updateProductQuantity(int productId, int quantity){
        int rowsAffected=0;
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            rowsAffected= session.createQuery("UPDATE Product set availableQuantity= :quantity " + "WHERE id = :productId").
                    setParameter("quantity",quantity).setParameter("productId",productId)
                    .executeUpdate();
            System.out.println(rowsAffected+" rowsAffected ");
            session.getTransaction().commit();
        }
        return rowsAffected!=0;
    }
   @Override
   public List<Product> getProducts(){
        List<Product> products;
       try(Session session=sessionFactory.openSession()){
           session.beginTransaction();
           products=session.createQuery("from Product").list();
           session.getTransaction().commit();
       }
       return products;
   }

}
