package org.example.service.admin;

import org.example.entity.Admin;
import org.example.entity.User;
import org.example.model.Response;
import org.example.repository.admin.AdminRepository;
import org.example.repository.user.UserRepository;
import org.example.service.ValidationService;
import org.example.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImplementation implements AdminService{

    private final AdminRepository repository;
    private final UserService userService;

    private ValidationService validationService;
    @Autowired
    public AdminServiceImplementation(AdminRepository repository, UserService userService) {
        validationService=new ValidationService();
        this.repository = repository;
        this.userService = userService;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Response addAdmin(Admin admin) {
        Response response=validationService.validateAdminEmail(admin.getEmail());
        System.out.println("**********************");
        System.out.println( response.toString());
        System.out.println("**********************");
        if (response.isErrorOccurred()){
            System.out.println("henaa");
            response.setFieldErrorOccurred(true);
            return response;
        }
      else{
            System.out.println("beforrrrrrrr");
            Response adminResponse=repository.addAdmin(admin);
            System.out.println("email is valid 444");
            if(adminResponse.isErrorOccurred()){
                return adminResponse;
            }
        }
      return new Response("Ok",200,false);

    }

    @Override
    public Response<List<Admin>> getAllAdmins() {
        return repository.getAllAdmins();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Response<Boolean> updateAdmin(User admin) {
        return repository.updateAdmin(admin);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Response<Boolean> removeAdmin(int adminID, String adminEmail) {
        Response isAdminDataCorrect = userService.getUser(adminID, adminEmail);

        if(isAdminDataCorrect.getObjectToBeReturned()==null)
            return new Response<>("admin data incorrect", 404, true, true, false);
        return repository.removeAdmin(adminID, adminEmail);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Response<Boolean> removeAdmin(int adminID) {
        Response isAdminDataCorrect = userService.getUserById(adminID);

        if(isAdminDataCorrect.getObjectToBeReturned()==null)
            return new Response<>("admin data incorrect", 404, true, true, false);
        return repository.removeAdmin(adminID);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Response<Boolean> deactivateCustomer(int customerID, String customerEmail) {
        Response isAdminDataCorrect = userService.getUser(customerID, customerEmail);

        if(isAdminDataCorrect.getObjectToBeReturned()==null)
            return new Response<>("customer data incorrect", 404, true, true, false);
        return repository.deactivateCustomer(customerID, customerEmail);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void updateOrderStatus(int orderID) {
        /// call orderservice.updateStatus()
    }
}
