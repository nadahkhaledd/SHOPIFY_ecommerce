package org.example.service.admin;

import org.example.entity.Admin;
import org.example.entity.User;
import org.example.model.Response;

import java.util.List;

public interface AdminService {


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
     * This method is used to update an admin.
     * @param admin This is the id of the customer needs to be removed.
     * @return boolean if admin updated.
     */
    Response<Boolean> updateAdmin(User admin);

    /**
     * This method is used to remove an admin from database with their id  and email.
     * @param adminEmail This is the email of the admin needs to be deleted.
     * @return boolean if admin removed.
     */
    Response<Boolean> removeAdminByEmail(String adminEmail);

    /**
     * This method is used to remove an admin from database with their id.
     * @param adminID This is the id of the admin needs to be deleted.
     * @return boolean if admin removed.
     */
    Response<Boolean> removeAdminByID(int adminID);

    /**
     * This method is used by admin to remove a customer account from the system.
     * It works by deactivating the customer from database as to keep their history stored.
     * @return boolean if customer deactivated.
     */
    Response<Boolean> deactivateCustomer(String customerEmail);


}
