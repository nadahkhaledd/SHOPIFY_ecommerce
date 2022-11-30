package org.example.repository.admin;

import org.example.entity.Admin;
import org.example.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepositoryImplementation implements AdminRepository{

    @Override
    public void addAdmin(Admin admin) {

    }

    @Override
    public void updateAdmin(Admin admin) {

    }

    @Override
    public void removeAdmin(int adminID) {

    }

    @Override
    public void suspendCustomer(int customerID) {

    }

    @Override
    public void addCategory(Category category) {

    }

    @Override
    public void updateCategory(int categoryID, String imgPath) {

    }

    @Override
    public void removeCategory(int categoryID) {

    }
}
