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

    /**
     * This method is used to update an admin.
     * @param admin This is the id of the customer needs to be removed.
     * @return int number of rows affected
     */
    int updateAdmin(Admin admin);

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

}
