package org.example.repository.product;

import org.example.entity.Category;
import org.example.entity.Product;
import org.example.model.Response;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ResourceBundle;

@Repository
public class ProductRepoImpl implements ProductRepo {
    SessionFactory sessionFactory;

    @Autowired
    public ProductRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Response<Product> getProduct(int productId) {
        Product product;
        try (Session session = sessionFactory.openSession()) {
            try {
                product = session.createQuery("from Product where id=:productId", Product.class)
                        .setParameter("productId", productId)
                        .getSingleResult();
            } catch (Exception e) {
                System.out.println("in get products repo impl  e.getStackTrace() = " + e.getStackTrace());
                return new Response("error occurred while processing your request", 500, true);
            }
        }
        return new Response<Product>("Done", 200, false, product);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response addProduct(Product product) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.persist(product);//return type of persist is void
                session.getTransaction().commit();
            } catch (Exception e) {
                System.out.println("in add product repo impl  e.getStackTrace() = " + e.getStackTrace());
                return new Response("error occurred while processing your request", 500, true);
            }
        }
        return new Response("Done", 200, false, product);

    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response<Product> updateProduct(Product product) {

        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.merge(product);
                session.getTransaction().commit();
            } catch (Exception e) {
                System.out.println("in update product repo impl e.getStackTrace() = " + e.getStackTrace());
                return new Response("error occurred while processing your request", 500, true);
            }
        }
        return new Response<Product>("Done", 200, false, product);

    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response<Product> deleteProduct(Product product) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.remove(product);//(delete vs remove)If you have the index of the item to be eliminated, del is probably best. But if you have the value that you want to eliminate, .remove() is likely to be best.
                session.getTransaction().commit();
            } catch (Exception e) {
                System.out.println("in delete product repo impl e.getStackTrace() = " + e.getStackTrace());
                return new Response("error occurred while processing your request", 500, true);
            }
        }
        return new Response<Product>("Done", 200, false, product);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response<Boolean> updateProductRate(int productId, float rate) {
        int rowsAffected = 0;
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                rowsAffected = session.createQuery("UPDATE Product set rate = :rate " + "WHERE id = :productId").
                        setParameter("rate", rate).setParameter("productId", productId)
                        .executeUpdate();
                System.out.println(rowsAffected + " rowsAffected ");
                session.getTransaction().commit();
            } catch (Exception e) {
                System.out.println("in update product rate product repo impl e.getStackTrace() = " + e.getStackTrace());
                return new Response("error occurred while processing your request", 500, true);
            }
        }
        return new Response<Boolean>("Done", 200, false, rowsAffected != 1);

    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response<Boolean> updateProductQuantity(int productId, int quantity) {
        int rowsAffected = 0;
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                rowsAffected = session.createQuery("UPDATE Product set availableQuantity= :quantity " + "WHERE id = :productId").
                        setParameter("quantity", quantity).setParameter("productId", productId)
                        .executeUpdate();
                System.out.println(rowsAffected + " rowsAffected ");
                session.getTransaction().commit();
            } catch (Exception e) {
                System.out.println("in update product quantity product repo impl  e.getStackTrace() = " + e.getStackTrace());
                return new Response("error occurred while processing your request", 500, true);
            }
        }
        return new Response<Boolean>("Done", 200, false, rowsAffected != 1);

    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response<List<Product>> getProducts() {
        List<Product> products;
        try (Session session = sessionFactory.openSession()) {
            try{
            session.beginTransaction();
            products = session.createQuery("from Product").list();}
            catch (Exception e){
                System.out.println("in update product rate product repo impl e.getStackTrace() = " + e.getStackTrace());
                return new Response("error occurred while processing your request",500,true);
            }
        }
        return new Response<List<Product>>("Done",200,false,products) ;

    }



    /**
     * @InheritedDoc
     */
    @Override
    public Response<List<Product>> getProductsByCategory(int categoryId) {
        List<Product> products;
        try (Session session = sessionFactory.openSession()) {
           try{
            session.beginTransaction();
            products = session.createQuery("select p from Product as p inner join p.category as c where c.id=:categoryId").
                    setParameter("categoryId", categoryId).list();
        } catch (Exception e){
               System.out.println("in get products by category  product repo impl e.getStackTrace() = " + e.getStackTrace());
               return new Response("error occurred while processing your request",500,true);
           }
        }
        return new Response<List<Product>>("Done",200,false,products) ;

    }


        /**
         * @InheritedDoc
         */
    @Override
    public Response<List<Product>> searchByProductName(String productName) {

        List<Product> products;
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                products = session.createQuery("from Product where name like :searchkey or name= :productName", Product.class).
                        setString("searchkey", "% " + productName + "%")
                        .setParameter("productName", productName).list();
            }
            catch (Exception e){
                System.out.println("in search by product name  product repo impl e.getStackTrace() = " + e.getStackTrace());
                return new Response("error occurred while processing your request",500,true);
            }
        }
        return new Response<List<Product>>("Done",200,false,products) ;

    }


    /**
     * @Inherited
     */
    @Override
    public Response<Product> getProductsById(int productId) {
        Product product;
        try (Session session = sessionFactory.openSession()) {
            try{
            session.beginTransaction();
            product = (Product) session.createQuery("from Product where id= :productId")
                    .setParameter("productId", productId).getSingleResult();
        }
            catch (Exception e){
                System.out.println("in get products by id product repo impl e.getStackTrace() = " + e.getStackTrace());
                return new Response("error occurred while processing your request",500,true);
            }
        }
        return new Response<Product>("Done",200,false,product) ;

    }


}
