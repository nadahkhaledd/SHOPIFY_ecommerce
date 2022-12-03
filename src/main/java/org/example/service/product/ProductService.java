package org.example.service.product;

import org.example.entity.Category;
import org.example.entity.Product;

import java.util.List;

/**
 *  responsible for Products operations
 *
 */
public interface ProductService {

    Product getProduct(int productId);

    /**
     *  add product this function used by admin to add product to database
     *
     * @param product product object to be added
     */
    void addProduct(Product product);
    /**
     * update product this function is used by admin to update product from database
     * @param product product object to be updated
     * @Return nothing
     */

    void updateProduct(Product product);


    /**
     * delete product function invoked by admin to delete product from database
     * @param product product object to be deleted
     * @Return nothing
     */
    void deleteProduct(Product product);


    /**
     * update product rate used by customers to update rate to database
     * @param productId productId  id of the product to be updated
     * @param rate rate new rate of the product
     * @return  true if a row is updated in the database
     */
    boolean updateProductRate(int productId, float rate);


    /**
     * update product quantity
     * function takes id of the product and new quantity and apply this changes to database
     * @param productId productId id of the product to be updated
     * @param quantity quantity new quantity of the product to be updated
     * @return  true if a row is updated in the database
     */
    boolean updateProductQuantity(int productId, int quantity);


    /**
     * get products
     * function used to return all products from the database
     * @return products
     */
    List<Product> getProducts();


    /**
     * get products by category
     * retrieves all products related to a category
     * @param categoryId id of category
     * @return list of products
     */
    List<Product> getProductsByCategory(int categoryId);
    /**
     * get products by id
     * retrieves product with the given id
     * @param productId id of product
     * @return  product
     */
    Product getProductsById(int productId);

    /**
     * search by product name
     * takes a product name and retrieve all products matching this name
     * @param productName productName
     * @return list of products
     */

     List<Product> searchByProductName(String productName);

     /**
      *  calculate product rate
      * product having list<Rate> and calculate rate of the product
      * and put the value in the transient parameter rate to be displayed in product details view
      * @param product product
      * @return nothing
      */
      void calculateProductRate(Product product);
}
