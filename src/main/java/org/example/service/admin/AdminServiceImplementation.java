package org.example.service.admin;

import org.example.entity.Admin;
import org.example.entity.User;
import org.example.model.Response;
import org.example.repository.admin.AdminRepository;
import org.example.repository.user.UserRepository;
import org.example.service.ValidationService;
import org.example.service.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImplementation implements AdminService{

    private final AdminRepository repository;
    private final UserRepository userRepository;

    private ValidationService validationService;
    private AuthService authService;

    @Autowired
    public AdminServiceImplementation(AdminRepository repository, UserRepository userRepository,AuthService authService) {
        validationService=new ValidationService();
        this.repository = repository;
        this.userRepository = userRepository;
        this.authService=authService;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Response addAdmin(Admin admin) {
        Response response=validationService.validateAdminEmail(admin.getEmail());
        //update admin email response to be of type response
        boolean adminEmailResponse=authService.checkIfUserAlreadyExists(admin.getEmail());
        if (response.isErrorOccurred() || adminEmailResponse){
            response.setFieldErrorOccurred(true);
            if(adminEmailResponse) {
                response.setMessage("email already exists ");
                response.setStatusCode(400);
            }
            return response;
        }
      else{
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
    public Response<Boolean> updateAdmin(Admin admin) {
        return repository.updateAdmin(admin);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Response<Boolean> removeAdmin(int adminID, String adminEmail) {
        Response isAdminDataCorrect = userRepository.getUser(adminID, adminEmail);

        if(isAdminDataCorrect.getObjectToBeReturned()==null)
            return new Response<>("admin data incorrect", 404, true, true, false);
        return repository.removeAdmin(adminID, adminEmail);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Response<Boolean> deactivateCustomer(int customerID, String customerEmail) {
        Response isAdminDataCorrect = userRepository.getUser(customerID, customerEmail);

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
