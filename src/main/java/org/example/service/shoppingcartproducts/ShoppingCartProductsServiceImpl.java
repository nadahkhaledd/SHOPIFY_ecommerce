package org.example.service.shoppingcartproducts;

import org.example.entity.Customer;
import org.example.repository.shoppingcartproducts.ShoppingCartProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ShoppingCartProductsServiceImpl implements ShoppingCartProductsService {

    private final ShoppingCartProductsRepository shoppingCartProductsRepository;

    @Autowired
    public ShoppingCartProductsServiceImpl(ShoppingCartProductsRepository shoppingCartProductsRepository) {
        this.shoppingCartProductsRepository = shoppingCartProductsRepository;
    }

    @Override
    public double calculateTotal(Customer customer) {
        return shoppingCartProductsRepository.calculateTotal(customer);
    }
}
