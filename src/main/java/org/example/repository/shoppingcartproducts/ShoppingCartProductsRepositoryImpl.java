package org.example.repository.shoppingcartproducts;

import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.entity.ShoppingCartProducts;
import org.example.entity.User;
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
    public ShoppingCartProducts getCartItem(Product product, User user) {
        List<ShoppingCartProducts> cartProducts;
        try(Session session = factory.openSession()) {
            cartProducts = session.createQuery("from ShoppingCartProducts where user=:userId and product=:productId",
                            ShoppingCartProducts.class)
                    .setParameter("userId", user)
                    .setParameter("productId", product)
                    .getResultList();
            if(cartProducts.isEmpty())
                return null;
            else
                return cartProducts.get(0);
        }
    }

    @Override
    public ShoppingCartProducts getCartItem(int cartItemId) {
        List<ShoppingCartProducts> cartProducts;
        try(Session session = factory.openSession()) {
            cartProducts = session.createQuery("from ShoppingCartProducts where id=:cartItemId",
                            ShoppingCartProducts.class)
                    .setParameter("cartItemId", cartItemId)
                    .getResultList();
            if(cartProducts.isEmpty())
                return null;
            else
                return cartProducts.get(0);
        }
    }

    @Override
    public List<ShoppingCartProducts> viewCart(int userId) {
        List<ShoppingCartProducts> shoppingCartProducts;
        try(Session session = factory.openSession()) {
            shoppingCartProducts = session.
                    createQuery("select scp from ShoppingCartProducts as scp inner join scp.user as u " +
                                    "where u.id=:id",
                            ShoppingCartProducts.class)
                    .setParameter("id", userId)
                    .getResultList();
        }
        return shoppingCartProducts;
    }

    @Override
    public void addToCart(ShoppingCartProducts shoppingCartProduct) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(shoppingCartProduct);
            tx.commit();
        }
    }

    @Override
    public int updateProductQuantityInCart(int shoppingCartProductId, int newQuantity) {
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
        return result;
    }

    @Override
    public int removeFromCart(int shoppingCartProductId) {
        int result;
        try(Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query deleteQuery = session.createQuery("delete from ShoppingCartProducts scp " +
                    "where scp.id=:shoppingCartProductId");
            deleteQuery.setParameter("shoppingCartProductId",shoppingCartProductId);
            result = deleteQuery.executeUpdate();
            tx.commit();
        }
        return result;
    }
    @Override
    public double calculateTotal(int userId) {
        Double result;
        try(Session session = factory.openSession()) {
            Query query = session.createQuery(
                    "select sum(scp.product.price*scp.productQuantity) " +
                            "from ShoppingCartProducts as scp inner join scp.user as u where u.id=:id"
            ).setParameter("id", userId);
            result = (Double) query.getSingleResult();
            if(result == null)
                return 0.0;
        }
        return result;
    }

}
