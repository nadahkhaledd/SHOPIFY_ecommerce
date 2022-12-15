package controller;

import org.bouncycastle.math.raw.Mod;
import org.example.controller.AdminController;
import org.example.controller.AuthController;
import org.example.entity.Admin;
import org.example.entity.Customer;
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
public class AuthControllerTest {

    private AuthController authcontroller;
    private AuthService authService;
    private UserRepository userRepositoryMock;
    private CategoryService categoryServiceMock;
    private ProductService productServiceMock;
    private Model modelMock;
    private HttpSession httpSessionMock;

    public AuthControllerTest() {
        authService = Mockito.mock(AuthService.class);
        userRepositoryMock = Mockito.mock(UserRepository.class);
        categoryServiceMock = Mockito.mock(CategoryService.class);
        productServiceMock = Mockito.mock(ProductService.class);
        httpSessionMock = Mockito.mock(HttpSession.class);
        modelMock = Mockito.mock(Model.class);
        AuthController authcontroller = new AuthController();
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void registerTest_sendUserObject_returnTrue(){
        //arrange
        Response response= new Response("OK", 200, false, false, true);
        when(authService.register(any(User.class))).thenReturn(response);
        //act
        String result=authcontroller.register(new Customer(),modelMock);
        //Assert
        assertEquals("register", result);
    }

    @Test
    public void loginTest_sendEmailAndPassword_returnTrue(){
        //arrange
        Response response= new Response("OK", 200, false, false, true);
        when(authService.login(any(String.class),anyString())).thenReturn(response);
        //act
        String result=authcontroller.login(new User(),modelMock,httpSessionMock);
        //Assert
        assertEquals("register", result);
    }

}

