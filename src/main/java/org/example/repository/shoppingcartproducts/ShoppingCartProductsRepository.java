package org.example.repository.shoppingcartproducts;

import org.example.entity.Customer;

public interface ShoppingCartProductsRepository {
    double calculateTotal(Customer customer);
}
