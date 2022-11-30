package org.example.repository.admin;

import org.example.entity.Admin;
import org.example.entity.Category;

public interface AdminRepository {


    /**
     * This method is used to add new admin to database.
     * @param admin This is the admin object to be added.
     * @return nothing
     */
    void addAdmin(Admin admin);


    /**
     * This method is used to create the super admin of the system.
     * It creates a new admin object and then add it to database.
     * This method will be called by the start of the project.
     * @return nothing
     */
     void createSuperAdmin();
    void updateAdmin(Admin admin);

    /**
     * This method is used to remove an admin from database.
     * @param adminID This is the id of the admin needs to be deleted.
     * @return int number of rows affected
     */
    int removeAdmin(int adminID);

    /**
     * This method is used by admin to remove a customer account from the system.
     * It works by deactivating the customer from database as to keep their history stored.
     * @param customerID This is the id of the customer needs to be removed.
     * @return int number of rows affected
     */
    int deactivateCustomer(int customerID);

    /**
     * This method is used by admin to add a new category to database.
     * @param category This is the admin object to be added.
     * @return nothing
     */
    void addCategory(Category category);

    /**
     * This method is used by admin to update a category's image.
     * @param categoryID This is the id of the category needs to be updated.
     * @param imgPath This is the path of the new image to be added.
     * @return int number of rows affected
     */
    int updateCategory(int categoryID, String imgPath);

    /**
     * This method is used to remove a category from database.
     * @param categoryID This is the id of the category needs to be deleted.
     * @return int number of rows affected
     */
    int removeCategory(int categoryID);

}
