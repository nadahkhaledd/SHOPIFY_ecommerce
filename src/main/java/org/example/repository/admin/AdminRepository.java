package org.example.repository.admin;

import org.example.entity.Admin;
import org.example.entity.Category;
import org.example.entity.User;
import org.example.model.Response;

import java.util.List;

public interface AdminRepository {

    /**
     * This method is used to add new admin to database.
     * @param admin This is the admin object to be added.
     * @return nothing
     */
    Response addAdmin(User admin);


    /**
     * get all admins
     * retrieves all admins from database
     * @return list of admins
     */
    Response<List<Admin>> getAllAdmins();


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
    Response<Boolean> updateAdmin(User admin);

    /**
     * This method is used to remove an admin from database.
     * @param adminID This is the id of the admin needs to be deleted.
     * @param adminEmail This is the email of the admin.
     * @return int number of rows affected
     */
    Response<Boolean> removeAdmin(int adminID, String adminEmail);

    /**
     * This method is used by admin to remove a customer account from the system.
     * It works by deactivating the customer from database as to keep their history stored.
     * @param customerID This is the id of the customer needs to be removed.
     * @param customerEmail This is the email of the customer.
     * @return int number of rows affected or exception if happens.
     */
    Response<Boolean> deactivateCustomer(int customerID, String customerEmail);

}
