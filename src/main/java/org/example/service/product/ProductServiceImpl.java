package org.example.service.product;

import org.example.entity.Category;
import org.example.entity.Product;
import org.example.repository.product.ProductRepo;
import org.example.repository.product.ProductRepoImpl;
import org.example.service.rate.RateService;
import org.example.service.rate.RateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepo productRepository;
    private RateService rateService;
    @Autowired
    public ProductServiceImpl(ProductRepoImpl productRepoImpl,RateService rateService) {
        this.productRepository = productRepoImpl;
        this.rateService=rateService;
    }

    public Product getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    /**
     * @Inherited Doc
     */
    @Override
    public void addProduct(Product product) {
        product.setName(product.getName().toLowerCase());
        productRepository.addProduct(product);
    }
    /**
     * @Inherited Doc
     */

    @Override
    public void updateProduct(Product product) {
        product.setName(product.getName().toLowerCase());
        productRepository.updateProduct(product);
    }
    /**
     * @Inherited Doc
     */
    @Override
    public void deleteProduct(Product product) {
        productRepository.deleteProduct(product);
    }
    /**
     * @Inherited Doc
     */
    @Override
    public boolean updateProductRate(int productId, float rate) {
        return productRepository.updateProductRate(productId, rate);
    }
    /**
     * @Inherited Doc
     */
    @Override
    public boolean updateProductQuantity(int productId, int quantity) {
        return productRepository.updateProductQuantity(productId, quantity);
    }
    /**
     * @Inherited Doc
     */
    @Override
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    /**
     * @Inherited Doc
     */
    @Override
    public List<Product> getProductsByCategory(int categoryId) {
        return productRepository.getProductsByCategory(categoryId);
    }

    /**
     * @Inherited Doc
     */
    @Override
    public Product getProductsById(int productId) {
        return productRepository.getProductsById(productId);
    }


    /**
     * @Inherited Doc
     */
    @Override
    public List<Product> searchByProductName(String productName) {
        return productRepository.searchByProductName(productName);
    }


    /**
     * @Inherited Doc
     */

    @Override
    public void calculateProductRate(Product product) {
        if(!product.getRates().isEmpty())
            product.setRate(rateService.calculateRateOfProduct(product.getId()));
    }

}