package org.example.service.admin;

import org.example.entity.Admin;
import org.example.repository.admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceImplementation implements AdminService{

    private final AdminRepository repository;

    @Autowired
    public AdminServiceImplementation(AdminRepository repository) {
        this.repository = repository;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void addAdmin(Admin admin) {
        repository.addAdmin(admin);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean updateAdmin(Admin admin) {
        int rowsAffected = repository.updateAdmin(admin);
        return rowsAffected==1;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean removeAdmin(int adminID) {
        int rowsAffected = repository.removeAdmin(adminID);
        return rowsAffected==1;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean deactivateCustomer(int customerID) {
        int rowsAffected = repository.deactivateCustomer(customerID);
        return rowsAffected==1;
    }

    @Override
    public void updateOrderStatus(int orderID) {
        /// call orderservice.updateStatus()
    }
}
