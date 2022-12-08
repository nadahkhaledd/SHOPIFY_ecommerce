package org.example.service.product;

import org.example.entity.Product;
import org.example.model.Response;
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

    public Response<Product> getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response addProduct(Product product) {
        product.setName(product.getName().toLowerCase());
        return productRepository.addProduct(product);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response updateProduct(Product product) {
        product.setName(product.getName().toLowerCase());
        return productRepository.updateProduct(product);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response deleteProduct(Product product) {

        return productRepository.deleteProduct(product);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Response<Boolean> removeProduct(int productID) {
        return productRepository.deleteProduct(productID);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response<Boolean> updateProductRate(int productId, float rate) {
        return productRepository.updateProductRate(productId, rate);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response<Boolean> updateProductQuantity(int productId, int quantity) {
        return productRepository.updateProductQuantity(productId, quantity);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response<List<Product>> getProducts() {
        return productRepository.getProducts();
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response<List<Product>> getProductsByCategory(int categoryId) {
        return productRepository.getProductsByCategory(categoryId);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response<Integer> getNumberOfCategoryProducts(int categoryID) {
        return productRepository.getNumberOfCategoryProducts(categoryID);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response<Product> getProductsById(int productId) {
        return productRepository.getProductsById(productId);
    }


    /**
     * @InheritedDoc
     */
    @Override
    public Response<List<Product>> searchByProductName(String productName) {
        return productRepository.searchByProductName(productName);
    }


}