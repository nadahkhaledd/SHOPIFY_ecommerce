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
        return repository.calculateTotal(userId);
    }

    @Override
    public Response<List<ShoppingCartProducts>> viewCart(int userId) {
        return repository.viewCart(userId);
    }

    @Override
    public Response<ShoppingCartProducts> getCartItem(Product product, User user) {
        return repository.getCartItem(product, user);
    }

    @Override
    public Response<ShoppingCartProducts> getCartItem(int cartItemId) {
        return repository.getCartItem(cartItemId);
    }

    @Override
    public Response addToCart(ShoppingCartProducts shoppingCartProduct) {
        Response<ShoppingCartProducts> returnedProduct = getCartItem(shoppingCartProduct.getProduct(),
                shoppingCartProduct.getUser());
        if(returnedProduct == null) {
            repository.addToCart(shoppingCartProduct);
        }
        else
            repository.updateProductQuantityInCart(returnedProduct.getObjectToBeReturned().getId(),
                    returnedProduct.getObjectToBeReturned().getProductQuantity()+1);

        return new Response<>("Done", 200, false);
    }

    @Override
    public Response updateProductQuantityInCart(int shoppingCartProductId, int newQuantity) {
        int affectedRows;
        Response<ShoppingCartProducts> cartProduct = getCartItem(shoppingCartProductId);

        if(cartProduct.getObjectToBeReturned().getProduct().getAvailableQuantity() < newQuantity) {
            return new Response<>("The amount isn't in stock.", 402, false, true);
        }
        if(newQuantity > 0) {
            affectedRows = repository.updateProductQuantityInCart(shoppingCartProductId, newQuantity).getObjectToBeReturned();
        }
        else {
            affectedRows = 0;
            repository.removeFromCart(shoppingCartProductId);
        }
        return new Response<>("Quantity updated.", 200, false, affectedRows);
    }

    @Override
    public Response<Boolean> removeFromCart(int shoppingCartProductId) {
        return repository.removeFromCart(shoppingCartProductId);
    }
}
