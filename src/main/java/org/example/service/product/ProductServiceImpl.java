package org.example.service.product;

import org.example.entity.Product;
import org.example.repository.product.ProductRepo;
import org.example.repository.product.ProductRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepo productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepoImpl productRepoImpl) {
        this.productRepository = productRepoImpl;
    }

    @Override
    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.updateProduct(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.deleteProduct(product);
    }

    @Override
    public boolean updateProductRate(int productId, float rate) {
        return productRepository.updateProductRate(productId, rate);
    }

    @Override
    public boolean updateProductQuantity(int productId, int quantity) {
        return productRepository.updateProductQuantity(productId, quantity);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    /**
     * @return
     */
    @Override
    public List<Product> getProductsByCategory(int categoryId) {
        return productRepository.getProductsByCategory(categoryId);
    }


}