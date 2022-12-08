package org.example.service.category;

import org.example.entity.Category;
import org.example.model.Response;

import java.util.List;

public interface CategoryService {

    /**
     * This method is used by admin to add a new category to database.
     * @param category This is the admin object to be added.
     * @return nothing.
     */
    Response addCategory(Category category);

    /**
     * This method is used by admin to update a category's image.
     * @param category updated category object.
     * @return boolean if category updated.
     */
    Response<Boolean> updateCategory(Category category);

    /**
     * This method is used by admin to remove a category from database.
     * @param categoryID This is the id of the category needs to be deleted.
     * @return boolean if category removed.
     */
    Response<Boolean> removeCategory(int categoryID);

    /**
     * get all categories
     * retrieves all categories from database
     * @return list of categories
     */
    Response<List<Category>> getAllCategories();

    /**
     * get category by its name.
     * @param name the name of the category to get form database.
     * @return the found category.
     */
    Response<Category> getCategoryByName(String name);

    /**
     * get category by its id.
     * @param id the id of the category to get form database.
     * @return the found category.
     */
    Response<Category> getCategoryByID(int id);

    /**
     * search by category name
     * takes category name and retrieves all categories matching this name
     * @param categoryName categoryName
     * @return list of matched categories
     */
    Response<List<Category>> searchByCategoryName(String categoryName);


    /**
     * This method is used to get the names of all categories available in database.
     * @return List a list with categories available names.
     */
    Response<List<String>> getCategoriesNames();
}
