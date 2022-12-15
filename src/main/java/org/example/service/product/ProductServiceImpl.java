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
    public ProductServiceImpl(ProductRepo productRepoImpl) {
        this.productRepository = productRepoImpl;

    }

  /*  public Response<Product> getProduct(int productId) {
        if(productId<0){
            throw new IllegalArgumentException();
        }
        return productRepository.getProductsById(productId);
    }
*/
    /**
     * @InheritedDoc
     */
    @Override
    public Response addProduct(Product product) {
        if(product==null){
            throw new NullPointerException();
        }
        product.setName(product.getName().toLowerCase());
        return productRepository.addProduct(product);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response updateProduct(Product product) {

        if(product==null){
            throw new NullPointerException();
        }
        product.setName(product.getName().toLowerCase());
        return productRepository.updateProduct(product);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response deleteProduct(Product product) {
        if(product==null){
            throw new NullPointerException();
        }

        return productRepository.deleteProduct(product);
    }

    /**
     * @inheritDoc
     */
    //to be removed
    @Override
    public Response<Boolean> removeProduct(int productId) {
        if(productId<0){
            throw new IllegalArgumentException();
        }
        return productRepository.deleteProduct(productId);
    }

    /**
     * @InheritedDoc
     */
   /* @Override
    public Response<Boolean> updateProductRate(int productId, float rate) {
        return productRepository.updateProductRate(productId, rate);
    }
*/
    /**
     * @InheritedDoc
     */
    @Override
    public Response<Boolean> updateProductQuantity(int productId, int quantity) {
        if(productId<0 || quantity<0){
            throw new IllegalArgumentException();
        }
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
        if(categoryId<0){
            throw new IllegalArgumentException();
        }
        return productRepository.getProductsByCategory(categoryId);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Response<Product> getProductById(int productId) {
        if(productId<0){
            throw new IllegalArgumentException();
        }
        return productRepository.getProductsById(productId);
    }


    /**
     * @InheritedDoc
     */
    @Override
    public Response<List<Product>> searchByProductName(String productName) {
        if(productName==null){
            throw new NullPointerException();
        }
        else if(productName.isBlank()){
            throw new IllegalArgumentException();
        }
        return productRepository.searchByProductName(productName);
    }


}