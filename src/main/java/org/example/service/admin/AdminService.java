package org.example.service.admin;

import org.example.entity.Admin;
import org.example.entity.User;

public interface AdminService {


    /**
     * This method is used to add new admin to database.
     * @param admin This is the admin object to be added.
     * @return nothing
     */
    void addAdmin(User admin);

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
     * @return boolean if admin updated.
     */
    boolean updateAdmin(Admin admin);

    /**
     * This method is used to remove an admin from database.
     * @param adminID This is the id of the admin needs to be deleted.
     * @return boolean if admin removed.
     */
    boolean removeAdmin(int adminID);

    /**
     * This method is used by admin to remove a customer account from the system.
     * It works by deactivating the customer from database as to keep their history stored.
     * @param customerID This is the id of the customer needs to be removed.
     * @return boolean if customer deactivated.
     */
    boolean deactivateCustomer(int customerID);

    /**
     * This method is used by admin to update status of an order after being created/placed.
     * It works by calling the updateStatus() service placed in order service.
     * @param orderID This is the id of the customer needs to be removed.
     * @return nothing.
     */
    void updateOrderStatus(int orderID);

}
