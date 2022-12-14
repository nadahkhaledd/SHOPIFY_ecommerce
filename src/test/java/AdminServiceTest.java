import org.example.entity.Admin;
import org.example.entity.Category;
import org.example.entity.User;
import org.example.model.Response;
import org.example.repository.admin.AdminRepository;
import org.example.repository.category.CategoryRepository;
import org.example.service.ValidationService;
import org.example.service.admin.AdminService;
import org.example.service.admin.AdminServiceImplementation;
import org.example.service.category.CategoryService;
import org.example.service.category.CategoryServiceImplementation;
import org.example.service.security.AuthService;
import org.example.service.security.EncryptionService;
import org.example.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AdminServiceTest {

    private AdminService adminService;
    private AdminRepository adminRepositoryMock;
    private UserService userServiceMock;
    private AuthService authServiceMock;
    private ValidationService validationServiceMock;

    public AdminServiceTest() {
        adminRepositoryMock = Mockito.mock(AdminRepository.class);
        userServiceMock = Mockito.mock(UserService.class);
        authServiceMock = Mockito.mock(AuthService.class);
        validationServiceMock = Mockito.mock(ValidationService.class);

        adminService = new AdminServiceImplementation(adminRepositoryMock, userServiceMock, authServiceMock);
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    // addAdmin() tests
    @Test(expected = NullPointerException.class)
    public void testAddAdmin_sendNullAdminEntity_returnNullPointerException() {
        /*ACT*/
        adminService.addAdmin(null);
    }

    @Test
    public void testAddAdmin_sendWrongDomainEmail_expectWrongDomainResponseMessage(){
        //Arrange
        Admin admin = new Admin();
        admin.setEmail("newadmin@gmail.com");
        Response response = new Response("Wrong domain",400,true,true);

        when(validationServiceMock.validateAdminEmail(any(String.class)))
                .thenReturn(response);

        //ACT
        Response returnedResponse = adminService.addAdmin(admin);

        //Assert
        assertEquals(response.getMessage(), returnedResponse.getMessage());
        verify(validationServiceMock, times(0)).validateAdminEmail(anyString());
    }

    @Test
    public void testAddAdmin_sendDuplicatedEmail_expectEmailAlreadyExistsResponseMessage(){
        //Arrange
        Admin admin = new Admin();
        admin.setEmail("newadmin@shopify.com");
        Response validEmailresponse = new Response("Done",200,false,false);
        when(validationServiceMock.validateAdminEmail(any(String.class)))
                .thenReturn(validEmailresponse);

        Response userExistsResponse = new Response<>("you already have an account please login directly", 400, true, true, true);
        when(authServiceMock.checkIfUserAlreadyExists(anyString()))
                .thenReturn(userExistsResponse);

        //ACT
        Response returnedResponse = adminService.addAdmin(admin);

        //Assert
        assertEquals(userExistsResponse.getMessage(), returnedResponse.getMessage());
        verify(authServiceMock, times(1)).checkIfUserAlreadyExists(anyString());
    }

    @Test
    public void testAddAdmin_sendAdminEntity_thenSaveToDB(){
        //Arrange
        Admin admin = new Admin();
        admin.setEmail("newadmin@shopify.com");
        admin.setPassword("adm1n");
        Response validEmailresponse = new Response("Done",200,false,false);
        when(validationServiceMock.validateAdminEmail(any(String.class)))
                .thenReturn(validEmailresponse);

        Response userExistsResponse = new Response<>("Ok", 200, false, false, false);
        when(authServiceMock.checkIfUserAlreadyExists(anyString()))
                .thenReturn(userExistsResponse);

        Response addAdminResponse = new Response("Done", 200, false);
        when(adminRepositoryMock.addAdmin(any(User.class)))
                .thenReturn(addAdminResponse);

        //ACT
        Response returnedResponse = adminService.addAdmin(admin);

        //Assert
        assertEquals(addAdminResponse.getMessage(), returnedResponse.getMessage());
        verify(adminRepositoryMock, times(1)).addAdmin(any(User.class));
    }

    @Test
    public void testGetAllAdmins_returnAllSavedAdmins(){
        /*Arrange*/
        List<Admin> admins = new ArrayList<>();
        admins.add(new Admin("adminjohn@shopify.com"));
        admins.add(new Admin("adminalex@shopify.com"));
        Response response = new Response("Done", 200, false, admins);
        when(adminRepositoryMock.getAllAdmins())
                .thenReturn(response);

        /*ACT*/
        List<Admin> returnedAdmins = adminService.getAllAdmins().getObjectToBeReturned();

        /*Assert*/
        assertNotNull(returnedAdmins);
        assertEquals(2, returnedAdmins.size());
        verify(adminRepositoryMock, times(1)).getAllAdmins();
    }

    // updateAdmin() tests
    @Test(expected = NullPointerException.class)
    public void testAUpdateAdmin_sendNullAdminEntity_returnNullPointerException() {
        /*ACT*/
        adminService.updateAdmin(null);
    }

    @Test
    public void testUpdateAdmin_sendUpdatedAdminEntity_thenSaveToDB(){
        //Arrange
        Admin admin = new Admin("adminsam@shopify.com");
        admin.setLastName("corden");
        Response response = new Response<Boolean>("Done", 200, false, false, true);
        when(adminRepositoryMock.updateAdmin(any(Admin.class))).thenReturn(response);

        //ACT
        Response returnedResponse = adminService.updateAdmin(admin);

        //Assert
        assertEquals(response.getStatusCode(), returnedResponse.getStatusCode());
        verify(adminRepositoryMock, times(1)).updateAdmin(any(Admin.class));
    }

    //removeAdminByEmail() tests
    @Test
    public void testRemoveAdminByEmail_sendInvalidEmail_expectInvalidAdminDataResponse(){
        //Arrange
        Response getUserResponse = new Response<>("no content", 204, false, true, null);
        when(userServiceMock.getUserByEmail(anyString())).thenReturn(getUserResponse);

        Response removeAdminResponse = new Response<>("admin data incorrect", 404, true, true, false);
        when(adminRepositoryMock.removeAdminByEmail(anyString())).thenReturn(removeAdminResponse);

        //ACT
        Response returnedResponse = adminService.removeAdminByEmail("admin1@shopify.com");

        //Assert
        assertNotNull(returnedResponse);
        assertEquals(removeAdminResponse.getMessage(), returnedResponse.getMessage());
        verify(userServiceMock, times(1)).getUserByEmail(anyString());
        verify(adminRepositoryMock, times(0)).removeAdminByEmail(anyString());
    }

    @Test
    public void testRemoveAdminByEmail_sendValidEmail_expectRemoveFromDB(){
        //Arrange
        User user = new User("admin1@shopify.com");
        Response getUserResponse = new Response("Done", 200, false, user);
        when(userServiceMock.getUserByEmail(anyString())).thenReturn(getUserResponse);

        Response removeAdminResponse = new Response<Boolean>("Done", 200, false, false, true);
        when(adminRepositoryMock.removeAdminByEmail(anyString())).thenReturn(removeAdminResponse);

        //ACT
        Response returnedResponse = adminService.removeAdminByEmail(user.getEmail());

        //Assert
        assertNotNull(returnedResponse);
        assertEquals(removeAdminResponse.getMessage(), returnedResponse.getMessage());
        verify(userServiceMock, times(1)).getUserByEmail(anyString());
        verify(adminRepositoryMock, times(1)).removeAdminByEmail(anyString());
    }

    //removeAdminByID() tests
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAdminByID_sendNegativeID_returnIllegalArgumentException() {
        /*ACT*/
        adminService.removeAdminByID(-1);
    }

    @Test
    public void testRemoveAdminByID_sendInvalidID_expectInvalidAdminDataResponse(){
        //Arrange
        Response getUserResponse = new Response<>("no content", 204, false, true, null);
        when(userServiceMock.getUserById(anyInt())).thenReturn(getUserResponse);

        Response removeAdminResponse = new Response<>("admin data incorrect", 404, true, true, false);
        when(adminRepositoryMock.removeAdminByID(anyInt())).thenReturn(removeAdminResponse);

        //ACT
        Response returnedResponse = adminService.removeAdminByID(16);

        //Assert
        assertNotNull(returnedResponse);
        assertEquals(removeAdminResponse.getMessage(), returnedResponse.getMessage());
        verify(userServiceMock, times(1)).getUserById(anyInt());
        verify(adminRepositoryMock, times(0)).removeAdminByID(anyInt());
    }

    @Test
    public void testRemoveAdminByID_sendValidID_expectRemoveFromDB(){
        //Arrange
        User user = new User("admin1@shopify.com");
        user.setId(3);
        Response getUserResponse = new Response("Done", 200, false, user);
        when(userServiceMock.getUserById(anyInt())).thenReturn(getUserResponse);

        Response removeAdminResponse = new Response<Boolean>("Done", 200, false, false, true);
        when(adminRepositoryMock.removeAdminByID(anyInt())).thenReturn(removeAdminResponse);

        //ACT
        Response returnedResponse = adminService.removeAdminByID(3);

        //Assert
        assertNotNull(returnedResponse);
        assertEquals(removeAdminResponse.getMessage(), returnedResponse.getMessage());
        verify(userServiceMock, times(1)).getUserById(anyInt());
        verify(adminRepositoryMock, times(1)).removeAdminByID(anyInt());
    }

    // deactivateCustomer() tests
    @Test
    public void testDeactivateCustomer_sendInvalidEmail_expectInvalidCustomerDataResponse(){
        //Arrange
        Response getUserResponse = new Response<>("no content", 204, false, true, null);
        when(userServiceMock.getUserByEmail(anyString())).thenReturn(getUserResponse);

        Response deactivateCustomerResponse = new Response<>("customer data incorrect", 404, true, true, false);
        when(adminRepositoryMock.removeAdminByEmail(anyString())).thenReturn(deactivateCustomerResponse);

        //ACT
        Response returnedResponse = adminService.deactivateCustomer("hagar@gmail.com");

        //Assert
        assertNotNull(returnedResponse);
        assertEquals(deactivateCustomerResponse.getMessage(), returnedResponse.getMessage());
        verify(userServiceMock, times(1)).getUserByEmail(anyString());
        verify(adminRepositoryMock, times(0)).deactivateCustomer(anyString());
    }

    @Test
    public void testRemoveAdminByEmail_sendValidEmail_expectCustomerStatusDeactivated(){
        //Arrange
        User user = new User("hagarehab@gmail.com");
        Response getUserResponse = new Response("Done", 200, false, user);
        when(userServiceMock.getUserByEmail(anyString())).thenReturn(getUserResponse);

        Response deactivateCustomerResponse = new Response<Boolean>("Done", 200, false, false, true);
        when(adminRepositoryMock.deactivateCustomer(anyString())).thenReturn(deactivateCustomerResponse);

        //ACT
        Response returnedResponse = adminService.deactivateCustomer(user.getEmail());

        //Assert
        assertNotNull(returnedResponse);
        assertEquals(deactivateCustomerResponse.getMessage(), returnedResponse.getMessage());
        verify(userServiceMock, times(1)).getUserByEmail(anyString());
        verify(adminRepositoryMock, times(1)).deactivateCustomer(anyString());
    }





}
