package org.example.service.shoppingcartproducts;

import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.entity.ShoppingCartProducts;
import org.example.repository.shoppingcartproducts.ShoppingCartProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ShoppingCartProductsServiceImpl implements ShoppingCartProductsService {

    private final ShoppingCartProductsRepository repository;

    @Autowired
    public ShoppingCartProductsServiceImpl(ShoppingCartProductsRepository repository) {
        this.repository = repository;
    }

    @Override
    public double calculateTotal(Customer customer) {
        return repository.calculateTotal(customer);
    }

    @Override
    public void addToCart(Product product, Customer customer, ShoppingCartProducts shoppingCartProduct) {
        repository.addToCart(product, customer, shoppingCartProduct);
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
