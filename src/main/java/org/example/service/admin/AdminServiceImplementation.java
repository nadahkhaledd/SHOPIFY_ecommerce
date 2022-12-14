package org.example.service.admin;

import org.example.entity.Admin;
import org.example.entity.User;
import org.example.enums.CustomerStatus;
import org.example.model.Response;
import org.example.repository.admin.AdminRepository;
import org.example.service.ValidationService;
import org.example.service.security.EncryptionService;
import org.example.service.user.UserService;
import org.example.service.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImplementation implements AdminService{

    private final AdminRepository repository;
    private final UserService userService;
    private ValidationService validationService;
    private final AuthService authService;

    @Autowired
    public AdminServiceImplementation(AdminRepository repository, UserService userService, AuthService authService) {
        this.authService = authService;
        validationService=new ValidationService();
        this.repository = repository;
        this.userService = userService;
        repository.createSuperAdmin();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Response addAdmin(Admin admin) {
        if(admin == null)
            throw new NullPointerException();
        Response response=validationService.validateAdminEmail(admin.getEmail());
        Response<Boolean> adminEmailResponse=authService.checkIfUserAlreadyExists(admin.getEmail());
        if(adminEmailResponse.isErrorOccurred()){
            return adminEmailResponse;
        }
        if (response.isErrorOccurred() ){
            return response;
        }
      else{
            admin.setPassword(EncryptionService.hashPassword(admin.getPassword()));
            admin.setStatus(CustomerStatus.ACTIVATED);
            admin.setPasswordAttempts(0);
            Response adminResponse=repository.addAdmin(admin);
            if(adminResponse.isErrorOccurred()){
                return adminResponse;
            }
        }
      return new Response("Done",200,false);

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
    public Response<Boolean> removeAdminByEmail(String adminEmail) {
        Response isAdminDataCorrect = userService.getUserByEmail(adminEmail);
        if(isAdminDataCorrect.getObjectToBeReturned()==null)
            return new Response<>("admin data incorrect", 404, true, true, false);

        return repository.removeAdminByEmail(adminEmail);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Response<Boolean> removeAdminByID(int adminID) {
        Response isAdminDataCorrect = userService.getUserById(adminID);
        if(isAdminDataCorrect.getObjectToBeReturned()==null)
            return new Response<>("admin data incorrect", 404, true, true, false);

        return repository.removeAdminByID(adminID);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Response<Boolean> deactivateCustomer(String customerEmail) {
        Response isAdminDataCorrect = userService.getUserByEmail(customerEmail);
        if(isAdminDataCorrect.getObjectToBeReturned()==null)
            return new Response<>("customer data incorrect", 404, true, true, false);

        return repository.deactivateCustomer(customerEmail);
    }
}
