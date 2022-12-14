package org.example.service.shoppingcartproducts;

import org.example.entity.Product;
import org.example.entity.ShoppingCartProducts;
import org.example.entity.User;
import org.example.model.Response;
import org.example.repository.shoppingcartproducts.ShoppingCartProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartProductsServiceImpl implements ShoppingCartProductsService {

    private final ShoppingCartProductsRepository repository;

    @Autowired
    public ShoppingCartProductsServiceImpl(ShoppingCartProductsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response<Double> calculateTotal(int userId) {
        if(userId < 1)
            throw new IllegalArgumentException();
        return repository.calculateTotal(userId);
    }

    @Override
    public Response<List<ShoppingCartProducts>> viewCart(int userId) {
        if(userId < 1)
            throw new IllegalArgumentException();
        return repository.viewCart(userId);
    }

    @Override
    public Response<ShoppingCartProducts> getCartItem(Product product, User user) {
        if(product == null || user == null)
            throw new NullPointerException();
        return repository.getCartItem(product, user);
    }

    @Override
    public Response<ShoppingCartProducts> getCartItem(int cartItemId) {
        if(cartItemId < 1)
            throw new IllegalArgumentException();
        return repository.getCartItem(cartItemId);
    }

    @Override
    public Response addToCart(ShoppingCartProducts shoppingCartProduct) {
        if(shoppingCartProduct == null)
            throw new NullPointerException();

        Response<ShoppingCartProducts> returnedProduct = getCartItem(shoppingCartProduct.getProduct(),
                shoppingCartProduct.getUser());
        if(returnedProduct == null) {
            return repository.addToCart(shoppingCartProduct);
        }
        else
            return repository.updateProductQuantityInCart(returnedProduct.getObjectToBeReturned().getId(),
                    returnedProduct.getObjectToBeReturned().getProductQuantity()+1);
    }

    @Override
    public Response<Boolean> updateProductQuantityInCart(int shoppingCartProductId, int newQuantity) {
        if(shoppingCartProductId < 1)
            throw new IllegalArgumentException();

        int affectedRows;
        Response<ShoppingCartProducts> cartProduct = getCartItem(shoppingCartProductId);

        if(cartProduct.getObjectToBeReturned().getProduct().getAvailableQuantity() < newQuantity) {
            return new Response<>("The amount isn't in stock.", 402, false, true);
        }
        if(newQuantity > 0) {
            affectedRows = repository.updateProductQuantityInCart(shoppingCartProductId, newQuantity).getObjectToBeReturned();
        }
        else {
            affectedRows = repository.removeFromCart(shoppingCartProductId).getObjectToBeReturned();
        }
        return new Response<Boolean>("Quantity updated.", 200, false, affectedRows == 1);
    }

    @Override
    public Response<Boolean> removeFromCart(int shoppingCartProductId) {
        if(shoppingCartProductId < 1)
            throw new IllegalArgumentException();
        int result = repository.removeFromCart(shoppingCartProductId).getObjectToBeReturned();
        return new Response<Boolean>("Done", 200, false, false,  result == 1);
    }
}
