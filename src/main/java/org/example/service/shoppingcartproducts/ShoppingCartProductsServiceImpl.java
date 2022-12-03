package org.example.service.shoppingcartproducts;

import org.example.entity.Product;
import org.example.entity.ShoppingCartProducts;
import org.example.entity.User;
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
    public double calculateTotal(int userId) {
        return repository.calculateTotal(userId);
    }

    @Override
    public List<ShoppingCartProducts> viewCart(int userId) {
        return repository.viewCart(userId);
    }

    @Override
    public ShoppingCartProducts getCartItem(Product product, User user) {
        return repository.getCartItem(product, user);
    }

    @Override
    public ShoppingCartProducts getCartItem(int cartItemId) {
        return repository.getCartItem(cartItemId);
    }

    @Override
    public void addToCart(ShoppingCartProducts shoppingCartProduct) {
        ShoppingCartProducts returnedProduct = getCartItem(shoppingCartProduct.getProduct(),
                shoppingCartProduct.getUser());
        if(returnedProduct == null) {
            repository.addToCart(shoppingCartProduct);
        }
        else
            repository.updateProductQuantityInCart(returnedProduct.getId(),
                    returnedProduct.getProductQuantity()+1);

    }

    @Override
    public boolean updateProductQuantityInCart(int shoppingCartProductId, int newQuantity) {
        int affectedRows;
        ShoppingCartProducts cartProduct = getCartItem(shoppingCartProductId);
        if(cartProduct.getProduct().getAvailableQuantity() < newQuantity)
            return false;
        if(newQuantity > 0) {
            affectedRows = repository.updateProductQuantityInCart(shoppingCartProductId, newQuantity);
        }
        else {
            affectedRows = 0;
            repository.removeFromCart(shoppingCartProductId);
        }
        return affectedRows == 1;
    }

    @Override
    public boolean removeFromCart(int shoppingCartProductId) {
        int affectedRows = repository.removeFromCart(shoppingCartProductId);
        return affectedRows == 1;
    }
}
