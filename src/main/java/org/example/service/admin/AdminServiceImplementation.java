package org.example.service.admin;

import org.example.entity.Admin;
import org.example.entity.User;
import org.example.repository.admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImplementation implements AdminService{

    private final AdminRepository repository;

    @Autowired
    public AdminServiceImplementation(AdminRepository repository) {
        System.out.println("in admin service....");
        this.repository = repository;
        //repository.createSuperAdmin();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void addAdmin(User admin) {
        repository.addAdmin(admin);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void createSuperAdmin() {
        repository.createSuperAdmin();
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
    public boolean removeAdmin(int adminID, String adminEmail) {
        int rowsAffected = repository.removeAdmin(adminID, adminEmail);
        return rowsAffected==1;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean deactivateCustomer(int customerID, String customerEmail) {
        int rowsAffected = repository.deactivateCustomer(customerID, customerEmail);
        return rowsAffected==1;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void updateOrderStatus(int orderID) {
        /// call orderservice.updateStatus()
    }
}
