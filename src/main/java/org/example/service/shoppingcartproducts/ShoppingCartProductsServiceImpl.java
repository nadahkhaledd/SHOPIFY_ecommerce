package org.example.service.shoppingcartproducts;

import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.entity.ShoppingCartProducts;
import org.example.entity.User;
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
    public double calculateTotal(int userId) {
        return repository.calculateTotal(userId);
    }

    @Override
    public List<ShoppingCartProducts> viewCart(int userId) {
        return repository.viewCart(userId);
    }

    @Override
    public void addToCart(ShoppingCartProducts shoppingCartProduct) {
        repository.addToCart(shoppingCartProduct);
    }

    @Override
    public boolean updateProductQuantityInCart(int shoppingCartProductId, int newQuantity) {
        int affectedRows = repository.updateProductQuantityInCart(shoppingCartProductId, newQuantity);
        return affectedRows == 1;
    }

    @Override
    public boolean removeFromCart(int shoppingCartProductId) {
        int affectedRows = repository.removeFromCart(shoppingCartProductId);
        return affectedRows == 1;
    }
}
