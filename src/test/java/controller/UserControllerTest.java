package controller;
import org.example.controller.UserController;
import org.example.entity.User;
import org.example.model.Response;
import org.example.repository.user.UserRepository;
import org.example.service.admin.AdminService;
import org.example.service.category.CategoryService;
import org.example.service.product.ProductService;
import org.example.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class UserControllerTest {

    private UserController userController;
    private UserService userServiceMock;
    private Response responseMock;
    private Model modelMock;
    private HttpSession httpSessionMock;

    public UserControllerTest() {
        userServiceMock = Mockito.mock(UserService.class);
        responseMock = Mockito.mock(Response.class);
        httpSessionMock = Mockito.mock(HttpSession.class);
        modelMock = Mockito.mock(Model.class);
        userController = new UserController(userServiceMock);
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserInfo_givenNoLogin_returnLoginPage(){
        //Arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(null);

        //ACT
        String response = userController.getUserInfo(modelMock, httpSessionMock);

        //Assert
        assertEquals("redirect:/login", response);
    }

    @Test
    public void testGetUserInfo_givenValidUserID_returnUserProfilePage(){
        //Arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(1);
        when(userServiceMock.getUserById(anyInt())).thenReturn(new Response<User>("Done", 200, false, new User()));
        when(modelMock.addAttribute(anyString(), any())).thenReturn(modelMock);
        when(responseMock.getObjectToBeReturned()).thenReturn(new User("a"));
        //ACT
        String response = userController.getUserInfo(modelMock, httpSessionMock);

        //Assert
        assertEquals("userProfile", response);
        verify(modelMock, times(1)).addAttribute(anyString(), any());
    }


}
