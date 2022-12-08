package org.example.service.shoppingcartproducts;

import org.example.entity.Product;
import org.example.entity.ShoppingCartProducts;
import org.example.entity.User;
import org.example.model.Response;
import org.example.repository.shoppingcartproducts.ShoppingCartProductsRepository;
import org.example.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartProductsServiceImpl implements ShoppingCartProductsService {

    private final ShoppingCartProductsRepository repository;
    private final ProductService productService;

    @Autowired
    public ShoppingCartProductsServiceImpl(ShoppingCartProductsRepository repository, ProductService productService) {
        this.repository = repository;
        this.productService = productService;
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
        ShoppingCartProducts returnedProduct = getCartItem(shoppingCartProduct.getProduct(),
                shoppingCartProduct.getUser()).getObjectToBeReturned();
        if(returnedProduct == null) {
            repository.addToCart(shoppingCartProduct);
        }
        else
            repository.updateProductQuantityInCart(returnedProduct.getId(),
                    returnedProduct.getProductQuantity()+1);

        return new Response<>("Done", 200, false);
    }

    @Override
    public Response updateProductQuantityInCart(int shoppingCartProductId, int newQuantity) {
        int affectedRows;
        ShoppingCartProducts cartProduct = getCartItem(shoppingCartProductId).getObjectToBeReturned();
        if(cartProduct.getProduct().getAvailableQuantity() < newQuantity) {
            return new Response<>("The amount isn't in stock.", 402, false, true);
        }
        if(newQuantity > 0) {
            affectedRows = repository.updateProductQuantityInCart(shoppingCartProductId, newQuantity).getObjectToBeReturned();
        }
        else {
            affectedRows = 0;
            repository.removeFromCart(shoppingCartProductId);
        }
        return new Response<>("Quantity updated.", 200, false);
    }

    @Override
    public Response<Boolean> removeFromCart(int shoppingCartProductId) {
        return repository.removeFromCart(shoppingCartProductId);
    }
}
