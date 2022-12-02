package org.example.service.product;

import org.example.entity.Category;
import org.example.entity.Product;
import org.example.repository.product.ProductRepo;
import org.example.repository.product.ProductRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepo productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepoImpl productRepoImpl) {
        this.productRepository = productRepoImpl;
    }

    public Product getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    /**
     * @Inherited
     */
    @Override
    public void addProduct(Product product) {
        product.setName(product.getName().toLowerCase());
        productRepository.addProduct(product);
    }
    /**
     * @Inherited
     */

    @Override
    public void updateProduct(Product product) {
        product.setName(product.getName().toLowerCase());
        productRepository.updateProduct(product);
    }
    /**
     * @Inherited
     */
    @Override
    public void deleteProduct(Product product) {
        productRepository.deleteProduct(product);
    }
    /**
     * @Inherited
     */
    @Override
    public boolean updateProductRate(int productId, float rate) {
        return productRepository.updateProductRate(productId, rate);
    }
    /**
     * @Inherited
     */
    @Override
    public boolean updateProductQuantity(int productId, int quantity) {
        return productRepository.updateProductQuantity(productId, quantity);
    }
    /**
     * @Inherited
     */
    @Override
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    /**
     * @Inherited
     */
    @Override
    public List<Product> getProductsByCategory(int categoryId) {
        return productRepository.getProductsByCategory(categoryId);
    }


    /**
     * @Inherited
     */
    @Override
    public List<Product> searchByProductName(String productName) {
        return productRepository.searchByProductName(productName);
    }


}