package org.example.service.product;

import org.example.entity.Category;
import org.example.entity.Product;
import org.example.model.Response;

import java.util.List;

/**
 * responsible for Products operations
 */
public interface ProductService {

    Response<Product> getProduct(int productId);

    /**
     * add product this function used by admin to add product to database
     *
     * @param product product object to be added
     */
    Response addProduct(Product product);

    /**
     * update product this function is used by admin to update product from database
     *
     * @param product product object to be updated
     * @Return nothing
     */

    Response updateProduct(Product product);


    /**
     * delete product function invoked by admin to delete product from database
     *
     * @param product product object to be deleted
     * @Return nothing
     */
    Response deleteProduct(Product product);

    /**
     * This method is used by admin to remove a product from database.
     *
     * @param productID This is the id of the product needs to be deleted.
     * @return boolean if product removed.
     */
    Response<Boolean> removeProduct(int productID);


    /**
     * update product rate used by customers to update rate to database
     *
     * @param productId productId  id of the product to be updated
     * @param rate      rate new rate of the product
     * @return true if a row is updated in the database
     */
    Response<Boolean> updateProductRate(int productId, float rate);


    /**
     * update product quantity
     * function takes id of the product and new quantity and apply this changes to database
     *
     * @param productId productId id of the product to be updated
     * @param quantity  quantity new quantity of the product to be updated
     * @return true if a row is updated in the database
     */

    Response<Boolean> updateProductQuantity(int productId, int quantity);


    /**
     * get products
     * function used to return all products from the database
     *
     * @return products
     */
    Response<List<Product>> getProducts();


    /**
     * get products by category
     * retrieves all products related to a category
     *
     * @param categoryId id of category
     * @return list of products
     */
    Response<List<Product>> getProductsByCategory(int categoryId);



    /**
     * get products by id
     * retrieves product with the given id
     *
     * @param productId id of product
     * @return product
     */
    Response<Product> getProductsById(int productId);

    /**
     * search by product name
     * takes a product name and retrieve all products matching this name
     *
     * @param productName productName
     * @return list of products
     */

    Response<List<Product>> searchByProductName(String productName);


}
