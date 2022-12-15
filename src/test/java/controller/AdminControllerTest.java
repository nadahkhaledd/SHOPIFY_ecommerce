package controller;

import org.example.controller.AdminController;
import org.example.entity.Admin;
import org.example.entity.Category;
import org.example.entity.User;
import org.example.model.Response;
import org.example.repository.admin.AdminRepository;
import org.example.repository.user.UserRepository;
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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

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
    private Response responseMock;
    private Model modelMock;
    private ModelMap modelMapMock;
    private HttpSession httpSessionMock;
    private BindingResult bindingResultMock;

    public AdminControllerTest() {
        this.adminServiceMock = Mockito.mock(AdminService.class);
        userServiceMock = Mockito.mock(UserService.class);
        userRepositoryMock = Mockito.mock(UserRepository.class);
        categoryServiceMock = Mockito.mock(CategoryService.class);
        productServiceMock = Mockito.mock(ProductService.class);
        responseMock = Mockito.mock(Response.class);
        httpSessionMock = Mockito.mock(HttpSession.class);
        modelMock = Mockito.mock(Model.class);
        bindingResultMock = Mockito.mock(BindingResult.class);
        modelMapMock = Mockito.mock(ModelMap.class);
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
        verify(modelMock, times(0)).addAttribute(anyString(), any());
    }

    @Test
    public void testGetAdminHome_sendValidSessionAttribute_expectReturnAdminHomePage(){
        //Arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(1);
        when(adminController.checkSession(modelMock, httpSessionMock)).thenReturn(true);
        when(userRepositoryMock.getUsernameByID(anyInt()))
                .thenReturn(new Response("Done", 200, false, false, "admin"));
        when(modelMock.addAttribute(anyString(), any())).thenReturn(modelMock);

        //ACT
        String response = adminController.adminHome(modelMock, httpSessionMock);

        //Assert
        assertEquals("adminHome", response);
        verify(modelMock, times(1)).addAttribute(anyString(), any());

    }

    @Test
    public void testGetAdmins_sendValidSessionAttribute_returnAdminsPage(){
        //Arrange
        when(adminController.checkSession(modelMock, httpSessionMock)).thenReturn(true);
        when(adminServiceMock.getAllAdmins()).thenReturn(new Response("Done", 200, false, false, new ArrayList<>()));
        when(modelMock.addAttribute(anyString(), any())).thenReturn(modelMock);

        //ACT
        String response = adminController.getAdmins(modelMock, httpSessionMock);

        //Assert
        assertEquals("showAdmins", response);
        verify(adminServiceMock, times(1)).getAllAdmins();
    }

    @Test
    public void testShowCategories_sendValidSessionAttribute_returnCategoriesPage(){
        //Arrange
        when(adminController.checkSession(modelMock, httpSessionMock)).thenReturn(true);
        when(categoryServiceMock.getAllCategories()).thenReturn(new Response("Done", 200, false, false, new ArrayList<>()));
        when(modelMock.addAttribute(anyString(), any())).thenReturn(modelMock);

        //ACT
        String response = adminController.showCategories(modelMock, httpSessionMock);

        //Assert
        assertEquals("showCategories", response);
        verify(categoryServiceMock, times(1)).getAllCategories();
    }

    @Test
    public void testShowProducts_sendValidSessionAttribute_returnProductsPage(){
        //Arrange
        when(adminController.checkSession(modelMock, httpSessionMock)).thenReturn(true);
        when(productServiceMock.getProducts()).thenReturn(new Response("Done", 200, false, false, new ArrayList<>()));
        when(modelMock.addAttribute(anyString(), any())).thenReturn(modelMock);

        //ACT
        String response = adminController.showProducts(modelMock, httpSessionMock);

        //Assert
        assertEquals("showAllProducts", response);
        verify(productServiceMock, times(1)).getProducts();
    }

    @Test
    public void testNewAdmin_sendValidData_returnAddAdminFormPage(){
        //Arrange
        when(adminController.checkSession(modelMock, httpSessionMock)).thenReturn(true);
        when(modelMock.addAttribute(anyString(), any())).thenReturn(modelMock);

        //ACT
        String response = adminController.newAdmin(modelMock, httpSessionMock);

        //Assert
        assertEquals("addAdmin", response);
        verify(modelMock, times(2)).addAttribute(anyString(), any());

    }

    // addUser() tests
    @Test
    public void testAddUser_sendBindingResultError_expectGoToSamePage(){
        //Arrange
        when(bindingResultMock.hasErrors()).thenReturn(true);

        //ACT
        String response = adminController.addUser(new Admin(), bindingResultMock, modelMapMock);

        //Assert
        assertEquals("addAdmin", response);
        verify(adminServiceMock, times(0)).addAdmin(any(User.class));
    }

    @Test
    public void testAddUser_sendExceptionError_returnErrorPage(){
        //Arrange
        when(bindingResultMock.hasErrors()).thenReturn(false);
        when(modelMapMock.put(anyString(), any())).thenReturn("");
        when(adminServiceMock.addAdmin(any(User.class))).thenReturn(new Response("Done", 200, true));
        when(responseMock.isErrorOccurred()).thenReturn(true);
        when(responseMock.isFieldErrorOccurred()).thenReturn(false);

        //ACT
        String response = adminController.addUser(new Admin(), bindingResultMock, modelMapMock);

        //Assert
        assertEquals("error", response);
        verify(modelMapMock, times(3)).put(anyString(), any());
    }

    @Test
    public void testAddUser_sendFieldError_returnSamePage(){
        //Arrange
        when(bindingResultMock.hasErrors()).thenReturn(false);
        when(modelMapMock.put(anyString(), any())).thenReturn("");
        when(adminServiceMock.addAdmin(any(User.class))).thenReturn(new Response("Done", 200, true, true));
        when(responseMock.isErrorOccurred()).thenReturn(true);
        when(responseMock.isFieldErrorOccurred()).thenReturn(true);

        //ACT
        String response = adminController.addUser(new Admin(), bindingResultMock, modelMapMock);

        //Assert
        assertEquals("addAdmin", response);
        verify(modelMapMock, times(2)).put(anyString(), any());
    }

    @Test
    public void testAddUser_sendValidAdminData_returnAdminsPage(){
        //Arrange
        when(bindingResultMock.hasErrors()).thenReturn(false);
        when(modelMapMock.put(anyString(), any())).thenReturn("");
        when(adminServiceMock.addAdmin(any(User.class))).thenReturn(new Response("Done", 200, false));
        when(responseMock.isErrorOccurred()).thenReturn(false);

        //ACT
        String response = adminController.addUser(new Admin(), bindingResultMock, modelMapMock);

        //Assert
        assertEquals("redirect:/admin/admins", response);
        verify(modelMapMock, times(1)).put(anyString(), any());
        verify(responseMock, times(0)).isFieldErrorOccurred();
    }

    @Test
    public void testNewCategory_sendValidData_returnAddCategoryFormPage(){
        //Arrange
        when(adminController.checkSession(modelMock, httpSessionMock)).thenReturn(true);
        when(modelMock.addAttribute(anyString(), any())).thenReturn(modelMock);

        //ACT
        String response = adminController.newCategory(modelMock, httpSessionMock);

        //Assert
        assertEquals("addCategory", response);
        verify(modelMock, times(1)).addAttribute(anyString(), any());
    }

    @Test
    public void testAddCategory_sendValidCategory_returnCategoriesPage(){
        //Arrange
        when(bindingResultMock.hasErrors()).thenReturn(false);
        when(modelMapMock.put(anyString(), any())).thenReturn("");
        when(categoryServiceMock.addCategory(any(Category.class))).thenReturn(new Response("Done", 200, false));
        when(responseMock.isErrorOccurred()).thenReturn(false);

        //ACT
        String response = adminController.addCategory(new Category(), bindingResultMock, modelMapMock);

        //Assert
        assertEquals("redirect:/admin/showCategories", response);
        verify(modelMapMock, times(1)).put(anyString(), any());
        verify(responseMock, times(0)).isFieldErrorOccurred();
    }

    @Test
    public void testDeleteCategory_sendError_expectReturnErrorPage(){
        //Arrange
        when(categoryServiceMock.getCategoryByID(anyInt())).thenReturn(new Response("Done", 200, false, new Category()));
        when(categoryServiceMock.removeCategory(any(Category.class))).thenReturn(new Response("error occurred while processing your request", 500, true));
        when(modelMapMock.put(anyString(), any())).thenReturn("");
        when(responseMock.isErrorOccurred()).thenReturn(true);

        //ACT
        String response = adminController.deleteCategory(1, modelMapMock);

        //Assert
        assertEquals("error", response);
        verify(modelMapMock, times(2)).put(anyString(), any());
    }







}
