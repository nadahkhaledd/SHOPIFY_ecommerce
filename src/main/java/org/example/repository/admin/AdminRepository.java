package org.example.repository.admin;

import org.example.entity.Admin;
import org.example.entity.Category;

public interface AdminRepository {


    void addAdmin(Admin admin);
     void createSuperAdmin();
    void updateAdmin(Admin admin);
    void removeAdmin(int adminID);
    void suspendCustomer(int customerID);
    void addCategory(Category category);
    void updateCategory(int categoryID, String imgPath);
    void removeCategory(int categoryID);

}
