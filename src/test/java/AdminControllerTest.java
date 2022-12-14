import org.bouncycastle.math.raw.Mod;
import org.example.controller.AdminController;
import org.example.entity.Admin;
import org.example.entity.User;
import org.example.model.Response;
import org.example.repository.admin.AdminRepository;
import org.example.repository.user.UserRepository;
import org.example.service.ValidationService;
import org.example.service.admin.AdminService;
import org.example.service.admin.AdminServiceImplementation;
import org.example.service.category.CategoryService;
import org.example.service.product.ProductService;
import org.example.service.security.AuthService;
import org.example.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class AdminControllerTest {

    private AdminController adminController;
    private AdminService adminServiceMock;
    private UserService userServiceMock;
    private UserRepository userRepositoryMock;
    private CategoryService categoryServiceMock;
    private ProductService productServiceMock;
    private Model modelMock;
    private HttpSession httpSessionMock;

    public AdminControllerTest() {
        this.adminServiceMock = Mockito.mock(AdminService.class);
        userServiceMock = Mockito.mock(UserService.class);
        userRepositoryMock = Mockito.mock(UserRepository.class);
        categoryServiceMock = Mockito.mock(CategoryService.class);
        productServiceMock = Mockito.mock(ProductService.class);
        httpSessionMock = Mockito.mock(HttpSession.class);
        modelMock = Mockito.mock(Model.class);
        adminController = new AdminController(adminServiceMock, userServiceMock, userRepositoryMock, categoryServiceMock, productServiceMock);

    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCheckSession_sendValidAdminIDAndModelAttribute_expectReturnTrue(){
        //Arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(1);
        when(modelMock.getAttribute(anyString())).thenReturn(true);

        //ACT
        Boolean response = adminController.checkSession(modelMock, httpSessionMock);

        //Assert
        assertEquals(response, true);
    }

    @Test
    public void testCheckSession_sendNullModelAttribute_expectReturnFalse(){
        //Arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(null);
        when(modelMock.getAttribute(anyString())).thenReturn(false);

        //ACT
        Boolean response = adminController.checkSession(modelMock, httpSessionMock);

        //Assert
        assertEquals(response, false);
    }


    @Test
    public void testGetAdminHome_sendNullSessionAttribute_expectReturnLoginPage(){
        //Arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(null);
        when(adminController.checkSession(modelMock, httpSessionMock)).thenReturn(false);

        //ACT
        String response = adminController.adminHome(modelMock, httpSessionMock);

        //Assert
        assertEquals("redirect:/login", response);
    }


}
