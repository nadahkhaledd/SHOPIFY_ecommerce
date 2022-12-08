package org.example.utility;

import org.example.entity.Category;
import org.example.entity.Product;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductsUtils {
    /**
     * used in search controller
     * merging Products And Category Products
     * a method that extracts products from categories and add them to products list
     * takes a list of categories and adds all the category products in 1 list
     * then calls merge lists of products function to add products and category products
     * @param categories categories list of categories
     * @return list of products returned from a search method
     */
    public Set<Product> mergingProductsAndCategoryProducts(List<Category> categories, List<Product> products) {
        List<Product> categoryProducts= categories.stream().flatMap(category -> category.getProducts()
                        .stream())
                .collect(Collectors.toList());
        return mergingListsOfProducts(categoryProducts,products);
    }
    private Set<Product> mergingListsOfProducts(List<Product> firstListOfProducts,List<Product> secondListOfProducts){
        Set<Product>allProducts = new HashSet<>();
        allProducts.addAll(firstListOfProducts);
        allProducts.addAll(secondListOfProducts);
        return allProducts;
    }
}
