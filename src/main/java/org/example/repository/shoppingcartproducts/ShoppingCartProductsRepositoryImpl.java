package org.example.repository.shoppingcartproducts;

import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.entity.ShoppingCartProducts;
import org.example.entity.User;
import org.example.model.Response;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShoppingCartProductsRepositoryImpl implements ShoppingCartProductsRepository {

    private final SessionFactory factory;

    @Autowired
    public ShoppingCartProductsRepositoryImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Response<ShoppingCartProducts> getCartItem(Product product, User user) {
        List<ShoppingCartProducts> cartProducts;
        try(Session session = factory.openSession()) {
            cartProducts = session.createQuery("from ShoppingCartProducts where user=:userId and product=:productId",
                            ShoppingCartProducts.class)
                    .setParameter("userId", user)
                    .setParameter("productId", product)
                    .getResultList();
        }
        catch (Exception e) {
            System.out.println("in ShoppingCartProductsRepositoryImpl.getCartItem  e.getStackTrace() = " + e.getStackTrace());
            return new Response<>("error occurred while processing your request", 500, true);

        }
        return new Response<ShoppingCartProducts>("Done", 200,
                false,(cartProducts.isEmpty()?null: cartProducts.get(0)));
    }

    @Override
    public Response<ShoppingCartProducts> getCartItem(int cartItemId) {
        List<ShoppingCartProducts> cartProducts;
        try(Session session = factory.openSession()) {
            cartProducts = session.createQuery("from ShoppingCartProducts where id=:cartItemId",
                            ShoppingCartProducts.class)
                    .setParameter("cartItemId", cartItemId)
                    .getResultList();
        }
        catch (Exception e) {
            System.out.println("in ShoppingCartProductsRepositoryImpl.getCartItem  e.getStackTrace() = " + e.getStackTrace());
            return new Response<>("error occurred while processing your request", 500, true);

        }
        return new Response<ShoppingCartProducts>("Done", 200,
                false,(cartProducts.isEmpty()?null: cartProducts.get(0)));
    }

    @Override
    public Response<List<ShoppingCartProducts>> viewCart(int userId) {
        List<ShoppingCartProducts> shoppingCartProducts;
        try(Session session = factory.openSession()) {
            shoppingCartProducts = session.
                    createQuery("select scp from ShoppingCartProducts as scp inner join scp.user as u " +
                                    "where u.id=:id",
                            ShoppingCartProducts.class)
                    .setParameter("id", userId)
                    .getResultList();
        }
        catch (Exception e) {
            System.out.println("in ShoppingCartProductsRepositoryImpl.viewCart  e.getStackTrace() = " + e.getStackTrace());
            return new Response<>("error occurred while processing your request", 500, true);

        }
        return new Response<List<ShoppingCartProducts>>("Done", 200,
                false,shoppingCartProducts);
    }

    @Override
    public Response addToCart(ShoppingCartProducts shoppingCartProduct) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(shoppingCartProduct);
            tx.commit();
        }
        catch (Exception e) {
            System.out.println("in ShoppingCartProductsRepositoryImpl.addToCart  e.getStackTrace() = " + e.getStackTrace());
            return new Response<>("error occurred while processing your request", 500, true);

        }
        return new Response<>("Done", 200, false);
    }

    @Override
    public Response<Integer> updateProductQuantityInCart(int shoppingCartProductId, int newQuantity) {
        int result;
        try(Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query updateQuery = session
                    .createQuery("update ShoppingCartProducts set product_quantity=:newQuantity" +
                            " where id=:shoppingCartProductId");
            updateQuery.setParameter("newQuantity", newQuantity);
            updateQuery.setParameter("shoppingCartProductId", shoppingCartProductId);
            result = updateQuery.executeUpdate();
            tx.commit();
        }
        catch (Exception e) {
            System.out.println("in ShoppingCartProductsRepositoryImpl.updateProductQuantityInCart  e.getStackTrace() = " + e.getStackTrace());
            return new Response<>("error occurred while processing your request", 500, true);

        }
        return new Response<Integer>("Done", 200,
                false,result);
    }

    @Override
    public Response<Boolean> removeFromCart(int shoppingCartProductId) {
        int result;
        try(Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query deleteQuery = session.createQuery("delete from ShoppingCartProducts scp " +
                    "where scp.id=:shoppingCartProductId");
            deleteQuery.setParameter("shoppingCartProductId",shoppingCartProductId);
            result = deleteQuery.executeUpdate();
            tx.commit();
        }
        catch (Exception e) {
            System.out.println("in ShoppingCartProductsRepositoryImpl.removeFromCart  e.getStackTrace() = " + e.getStackTrace());
            return new Response<>("error occurred while processing your request", 500, true);

        }
        return new Response<Boolean>("Done", 200,
                false,result==1);
    }
    @Override
    public Response<Double> calculateTotal(int userId) {
        Double result;
        try(Session session = factory.openSession()) {
            Query query = session.createQuery(
                    "select sum(scp.product.price*scp.productQuantity) " +
                            "from ShoppingCartProducts as scp inner join scp.user as u where u.id=:id"
            ).setParameter("id", userId);
            result = (Double) query.getSingleResult();
        }
        catch (Exception e) {
            System.out.println("in ShoppingCartProductsRepositoryImpl.updateProductQuantityInCart  e.getStackTrace() = " + e.getStackTrace());
            return new Response<>("error occurred while processing your request", 500, true);

        }
        return new Response<Double>("Done", 200,
                false,result==null?0.0:result);
    }

}
