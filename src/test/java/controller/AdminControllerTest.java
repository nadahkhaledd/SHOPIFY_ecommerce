package controller;

import org.example.controller.AdminController;
import org.example.entity.Admin;
import org.example.entity.Category;
import org.example.entity.Product;
import org.example.entity.User;
import org.example.model.RemoveUserFields;
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
public class AdminControllerTest {

    private AdminController adminController;
    private AdminService adminServiceMock;
    private UserService userServiceMock;
    private UserRepository userRepositoryMock;
    private CategoryService categoryServiceMock;
    private ProductService productServiceMock;
    private Response responseMock;
    private RemoveUserFields removeUserFieldsMock;
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
        removeUserFieldsMock = Mockito.mock(RemoveUserFields.class);
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
        assertEquals(true, response);
    }

    @Test
    public void testCheckSession_sendNullModelAttribute_expectReturnFalse(){
        //Arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(null);
        when(modelMock.getAttribute(anyString())).thenReturn(false);

        //ACT
        Boolean response = adminController.checkSession(modelMock, httpSessionMock);

        //Assert
        assertEquals(false, response);
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

    // deleteCategory() tests
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

    @Test
    public void testDeleteCategory_sendValidCategoryID_returnCategoriesPage(){
        //Arrange
        when(categoryServiceMock.getCategoryByID(anyInt())).thenReturn(new Response("Done", 200, false, new Category()));
        when(categoryServiceMock.removeCategory(any(Category.class))).thenReturn(new Response("Done", 200, false, false, true));
        when(responseMock.isErrorOccurred()).thenReturn(false);

        //ACT
        String response = adminController.deleteCategory(1, modelMapMock);

        //Assert
        assertEquals("redirect:/admin/showCategories", response);
        verify(modelMapMock, times(0)).put(anyString(), any());
    }

    // deleteProduct() tests
    @Test
    public void testDeleteProduct_sendError_expectReturnErrorPage(){
        //Arrange
        when(productServiceMock.removeProduct(anyInt())).thenReturn(new Response("error occurred while processing your request", 500, true));
        when(modelMapMock.put(anyString(), any())).thenReturn("");
        when(responseMock.isErrorOccurred()).thenReturn(true);

        //ACT
        String response = adminController.deleteProduct(1, modelMapMock);

        //Assert
        assertEquals("error", response);
        verify(modelMapMock, times(2)).put(anyString(), any());
    }

    @Test
    public void testDeleteProduct_sendValidProductID_returnProductsPage(){
        //Arrange
        when(productServiceMock.removeProduct(anyInt())).thenReturn(new Response("Done", 200, false, false, true));
        when(responseMock.isErrorOccurred()).thenReturn(false);

        //ACT
        String response = adminController.deleteProduct(1, modelMapMock);

        //Assert
        assertEquals("redirect:/admin/products", response);
        verify(modelMapMock, times(0)).put(anyString(), any());
    }

    // deleteAdmin() tests
    @Test
    public void testDeleteAdmin_sendError_expectReturnErrorPage(){
        //Arrange
        when(adminServiceMock.removeAdminByID(anyInt())).thenReturn(new Response("error occurred while processing your request", 500, true));
        when(modelMapMock.put(anyString(), any())).thenReturn("");
        when(responseMock.isErrorOccurred()).thenReturn(true);

        //ACT
        String response = adminController.deleteAdmin(1, modelMapMock);

        //Assert
        assertEquals("error", response);
        verify(modelMapMock, times(2)).put(anyString(), any());
    }

    @Test
    public void testDeleteAdmin_sendValidAdminID_returnProductsPage(){
        //Arrange
        when(adminServiceMock.removeAdminByID(anyInt())).thenReturn(new Response("Done", 200, false, false, true));
        when(responseMock.isErrorOccurred()).thenReturn(false);

        //ACT
        String response = adminController.deleteAdmin(1, modelMapMock);

        //Assert
        assertEquals("redirect:/admin/admins", response);
        verify(modelMapMock, times(0)).put(anyString(), any());
    }

    // updateAdmin() tests
    @Test
    public void testGETUpdateAdmin_sendNullSessionAttribute_returnLoginPage(){
        //Arrange
        when(adminController.checkSession(modelMock, httpSessionMock)).thenReturn(false);

        //ACT
        String response = adminController.updateAdmin(modelMock, 1, httpSessionMock);

        //Assert
        assertEquals("redirect:/login", response);
        verify(userServiceMock, times(0)).getUserById(anyInt());
    }

    @Test
    public void testGETUpdateAdmin_sendValidAdminID_returnUpdateAdminFormPage(){
        //Arrange
        when(adminController.checkSession(modelMock, httpSessionMock)).thenReturn(true);
        when(userServiceMock.getUserById(anyInt())).thenReturn(new Response<User>("Done", 200, false, new User()));
        when(modelMock.addAttribute(anyString(), any())).thenReturn(modelMock);

        //ACT
        String response = adminController.updateAdmin(modelMock, 1, httpSessionMock);

        //Assert
        assertEquals("updateAdmin", response);
        verify(userServiceMock, times(1)).getUserById(anyInt());
    }

    ///rest with errors to be implemented
    @Test
    public void testPOSTUpdateAdmin_sendValidAdminData_returnAdminsPage(){
        //Arrange
        when(bindingResultMock.hasErrors()).thenReturn(false);
        when(modelMapMock.put(anyString(), any())).thenReturn("");
        when(adminServiceMock.updateAdmin(any(User.class))).thenReturn(new Response("Done", 200, false, false, true));
        when(responseMock.isErrorOccurred()).thenReturn(false);

        //ACT
        String response = adminController.updateAdmin(new Admin(), bindingResultMock, modelMapMock);

        //Assert
        assertEquals("redirect:/admin/admins", response);
        verify(modelMapMock, times(1)).put(anyString(), any());
        verify(responseMock, times(0)).isFieldErrorOccurred();
    }

    // updateCategory() tests
    @Test
    public void testGETUpdateCategory_sendNullSessionAttribute_returnLoginPage(){
        //Arrange
        when(adminController.checkSession(modelMock, httpSessionMock)).thenReturn(false);

        //ACT
        String response = adminController.updateCategory(modelMock, 1, httpSessionMock);

        //Assert
        assertEquals("redirect:/login", response);
        verify(categoryServiceMock, times(0)).getCategoryByID(anyInt());
    }

    @Test
    public void testGETUpdateCategory_sendValidCategoryID_returnUpdateCategoryFormPage(){
        //Arrange
        when(adminController.checkSession(modelMock, httpSessionMock)).thenReturn(true);
        when(categoryServiceMock.getCategoryByID(anyInt())).thenReturn(new Response<Category>("Done", 200, false,false,  new Category()));
        when(modelMock.addAttribute(anyString(), any())).thenReturn(modelMock);

        //ACT
        String response = adminController.updateCategory(modelMock, 1, httpSessionMock);

        //Assert
        assertEquals("updateCategory", response);
        verify(categoryServiceMock, times(1)).getCategoryByID(anyInt());
    }

    ///rest with errors to be implemented
    @Test
    public void testPOSTUpdateCategory_sendValidCategoryData_returnCategoriesPage(){
        //Arrange
        when(bindingResultMock.hasErrors()).thenReturn(false);
        when(modelMapMock.put(anyString(), any())).thenReturn("");
        when(categoryServiceMock.updateCategory(any(Category.class))).thenReturn(new Response("Done", 200, false, false, true));
        when(responseMock.isErrorOccurred()).thenReturn(false);

        //ACT
        String response = adminController.updateCategory(new Category(), bindingResultMock, modelMapMock);

        //Assert
        assertEquals("redirect:/admin/showCategories", response);
        verify(modelMapMock, times(1)).put(anyString(), any());
        verify(responseMock, times(0)).isFieldErrorOccurred();
    }

    @Test
    public void testNewProduct_sendValidData_returnAddProductFormPage(){
        //Arrange
        when(adminController.checkSession(modelMock, httpSessionMock)).thenReturn(true);
        when(categoryServiceMock.getCategoriesNames()).thenReturn(new Response("Done", 200, false, new ArrayList<>()));
        when(modelMock.addAttribute(anyString(), any())).thenReturn(modelMock);

        //ACT
        String response = adminController.newProduct(modelMock, httpSessionMock);

        //Assert
        assertEquals("addProduct", response);
        verify(modelMock, times(2)).addAttribute(anyString(), any());
    }

    @Test
    public void testAddProduct_sendValidProduct_returnProductsPage(){
        //Arrange
        when(bindingResultMock.hasErrors()).thenReturn(false);
        when(modelMapMock.put(anyString(), any())).thenReturn("");
        when(categoryServiceMock.getCategoriesNames()).thenReturn(new Response("Done", 200, false, new ArrayList<>()));
        when(productServiceMock.addProduct(any(Product.class))).thenReturn(new Response("Done", 200, false));
        when(responseMock.isErrorOccurred()).thenReturn(false);

        //ACT
        String response = adminController.addProduct(new Product(), bindingResultMock, modelMapMock);

        //Assert
        assertEquals("redirect:/admin/products", response);
        verify(modelMapMock, times(1)).addAttribute(anyString(), any());
        verify(modelMapMock, times(1)).put(anyString(), any());
        verify(responseMock, times(0)).isFieldErrorOccurred();
    }

    // updateProduct() tests
    @Test
    public void testGETUpdateProducts_sendNullSessionAttribute_returnLoginPage(){
        //Arrange
        when(adminController.checkSession(modelMock, httpSessionMock)).thenReturn(false);

        //ACT
        String response = adminController.updateCategory(modelMock, 1, httpSessionMock);

        //Assert
        assertEquals("redirect:/login", response);
        verify(productServiceMock, times(0)).getProductById(anyInt());
    }

    @Test
    public void testGETUpdateProduct_sendValidProductID_returnUpdateProductFormPage(){
        //Arrange
        when(adminController.checkSession(modelMock, httpSessionMock)).thenReturn(true);
        when(productServiceMock.getProductById(anyInt())).thenReturn(new Response<Product>("Done", 200, false,false,  new Product()));
        when(categoryServiceMock.getCategoriesNames()).thenReturn(new Response("Done", 200, false, new ArrayList<>()));
        when(responseMock.getObjectToBeReturned()).thenReturn(new ArrayList<>());
        when(modelMock.addAttribute(anyString(), any())).thenReturn(modelMock);

        //ACT
        String response = adminController.updateProduct(modelMock, 1, httpSessionMock);

        //Assert
        assertEquals("updateProduct", response);
        verify(productServiceMock, times(1)).getProductById(anyInt());
        verify(modelMock, times(2)).addAttribute(anyString(), any());
    }

    ///rest with errors to be implemented
    @Test
    public void testPOSTUpdateProduct_sendValidProductData_returnHomePage(){
        //Arrange
        when(bindingResultMock.hasErrors()).thenReturn(false);
        when(categoryServiceMock.getCategoriesNames()).thenReturn(new Response("Done", 200, false, new ArrayList<>()));
        when(modelMapMock.put(anyString(), any())).thenReturn("");
        when(productServiceMock.updateProduct(any(Product.class))).thenReturn(new Response("Done", 200, false, false, true));
        when(responseMock.isErrorOccurred()).thenReturn(false);

        //ACT
        String response = adminController.updateProduct(new Product(), bindingResultMock, modelMapMock);

        //Assert
        assertEquals("redirect:/admin/products", response);
        verify(modelMapMock, times(1)).put(anyString(), any());
        verify(responseMock, times(0)).isFieldErrorOccurred();
    }

    // removeUser() tests
    @Test
    public void testRemoveUser_sendNullSessionAttribute_expectReturnLoginPage(){
        //Arrange
        when(adminController.checkSession(modelMock, httpSessionMock)).thenReturn(false);

        //ACT
        String response = adminController.removeUser(modelMock, httpSessionMock);

        //Assert
        assertEquals("redirect:/login", response);
        verify(modelMock, times(0)).addAttribute(anyString(), any());
    }

    @Test
    public void testRemove_sendValidSessionAttribute_returnRemoveUserFormPage(){
        //Arrange
        when(adminController.checkSession(modelMock, httpSessionMock)).thenReturn(true);
        when(modelMock.addAttribute(anyString(), any())).thenReturn(modelMock);

        //ACT
        String response = adminController.removeUser(modelMock, httpSessionMock);

        //Assert
        assertEquals("removeUser", response);
        verify(modelMock, times(1)).addAttribute(anyString(), any());
    }

    //deleteUser() tests

//    @Test
//    public void testDeleteUser_sendValidFieldsForAdmin_returnAdminsPage(){
//        //Arrange
//        RemoveUserFields removeUserFields = new RemoveUserFields();
//        removeUserFields.setUserType("admin");
//        when(bindingResultMock.hasErrors()).thenReturn(false);
//        when(removeUserFieldsMock.getUserType()).thenReturn("admin");
//        when(adminServiceMock.removeAdminByEmail(anyString()))
//                .thenReturn(new Response<Boolean>("Done", 200, false, false, true));
//        when(responseMock.isErrorOccurred()).thenReturn(false);
//
//
//        //ACT
//        String response = adminController.deleteUser(removeUserFields, bindingResultMock, modelMapMock);
//
//        //Assert
//        assertEquals("redirect:/admin/admins", response);
//        verify(removeUserFieldsMock, times(2)).getUserType();
//
//    }




}
