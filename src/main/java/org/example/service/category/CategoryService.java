package org.example.service.category;

import org.example.entity.Category;

import java.util.List;

public interface CategoryService {

    /**
     * This method is used by admin to add a new category to database.
     * @param category This is the admin object to be added.
     * @return nothing.
     */
    void addCategory(Category category);

    /**
     * This method is used by admin to update a category's image.
     * @param categoryID This is the id of the category needs to be updated.
     * @param imgPath This is the path of the new image to be added.
     * @return boolean if category updated.
     */
    boolean updateCategory(int categoryID, String imgPath);

    /**
     * This method is used by admin to remove a category from database.
     * @param categoryID This is the id of the category needs to be deleted.
     * @return boolean if category removed.
     */
    boolean removeCategory(int categoryID);

    List<Category> getAllCategories();

    /**
     * This method is used to get the names of all categories available in database.
     * @return List a list with categories available names.
     */
    List<String> getCategoriesNames();
}
