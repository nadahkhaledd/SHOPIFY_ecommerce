package org.example.repository.product;

import org.example.entity.Category;
import org.example.entity.Product;
import org.example.model.Response;

import java.util.List;

public interface ProductRepo {
    Response<Product> getProduct(int productId);

    /**
     *  add product this function used by admin to add product to database
     *
     * @param product product object to be added
     */
    Response<Product> addProduct(Product product);
    /**
     * update product this function is used by admin to update product from database
     * @param product product object to be updated
     * @Return nothing
     */
    Response<Product> updateProduct(Product product);

    /**
     * delete product function invoked by admin to delete product from database
     * @param product product object to be deleted
     * @Return nothing
     */
    Response<Product> deleteProduct(Product product);

    /**
     * delete product function invoked by admin to delete product from database
     * @param id product id to be deleted
     * @Return boolean
     */
    Response<Boolean> deleteProduct(int id);

    /**
     * update product rate used by customers to update rate to database
     * @param productId productId  id of the product to be updated
     * @param rate rate new rate of the product
     * @return  true if a row is updated in the database
     */
    Response<Boolean> updateProductRate(int productId, float rate);

    /**
     * update product quantity
     * function takes id of the product and new quantity and apply this changes to database
     * @param productId productId id of the product to be updated
     * @param quantity quantity new quantity of the product to be updated
     * @return  true if a row is updated in the database
     */
    Response<Boolean> updateProductQuantity(int productId, int quantity);

    /**
     * get products
     * function used to return all products from the database
     * @return products
     */
   Response<List<Product>> getProducts();

    /**
     * get number of products for a category.
     * @param categoryID id of category.
     * @return number of products
     */
   // Response<Integer> getNumberOfCategoryProducts(int categoryID);

    /**
     * get products by category
     *
     * @param categoryId categoryId id of category we want to retrieve it's products
     * @return {@link List} list of products
     */
    Response<List<Product>> getProductsByCategory(int categoryId);

    /**
     * search by product name
     * takes a product name and retrieve all products matching this name
     * @param productName productName
     * @return list of products
     */
    Response<List<Product>> searchByProductName(String productName);

    /**
     * get products by id
     * retrieves product with the given id
     * @param productId id of product
     * @return  product
     */
     Response<Product> getProductsById(int productId);
}
