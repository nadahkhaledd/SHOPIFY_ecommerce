import org.example.controller.ProductController;
import org.example.entity.Category;
import org.example.entity.Product;
import org.example.entity.ShoppingCartProducts;
import org.example.entity.User;
import org.example.model.Response;
import org.example.service.product.ProductService;
import org.example.service.rate.RateService;
import org.example.service.shoppingcartproducts.ShoppingCartProductsService;
import org.example.service.user.UserService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class ProductControllerTest {
   private ProductService productServiceMock;
   private RateService rateServiceMock;
   private UserService userServiceMock;
   private ShoppingCartProductsService cartServiceMock;
   private ProductController productController;
    private Model modelMock;
    private HttpSession httpSessionMock;
    private BindingResult bindingResultMock;

    public ProductControllerTest() {
        productServiceMock= Mockito.mock(ProductService.class);
        rateServiceMock= Mockito.mock(RateService.class);
        userServiceMock= Mockito.mock(UserService.class);
        cartServiceMock= Mockito.mock(ShoppingCartProductsService.class);
        productController=new ProductController(productServiceMock,rateServiceMock,userServiceMock,cartServiceMock);
        httpSessionMock = Mockito.mock(HttpSession.class);
        modelMock = Mockito.mock(Model.class);
        bindingResultMock = Mockito.mock(BindingResult.class);
    }
    //productService.getProductsById(productId);
    //rateService.setProductRate(productResponse.getObjectToBeReturned()
    @Test
    public void getProductDetails_sendValidProductResponseAndValidRateResponse_expectedProductDetailsView(){
        //arrange
        Category womenDresses=new Category();
        womenDresses.setName("women's dresses");
        womenDresses.setImagePath("dummy pic");
        Product product1=new Product("tshirt","dummy pic",120.0,womenDresses,12);
        product1.setRate(5.0);
        Response<Product> productResponse=new Response("Ok",200,false,false,product1);
        when(productServiceMock.getProductsById(anyInt())).thenReturn(productResponse);
        when(rateServiceMock.setProductRate(any())).thenReturn(new Response("Ok",200,false));
       //act
       ModelAndView result=productController.getProductDetails(1);
       //assert
        assertEquals(result.getViewName(),"productDetails");

    }
    @Test
    public void getProductDetails_sendInvalidProductResponseAndValidRateResponse_expectedErrorView(){
        //arrange
        Response<Product> productResponse=new Response("error",500,true);
        when(productServiceMock.getProductsById(anyInt())).thenReturn(productResponse);
        when(rateServiceMock.setProductRate(any())).thenReturn(new Response("Ok",200,false));
        //act
        ModelAndView result=productController.getProductDetails(1);
        //assert
        assertEquals(result.getViewName(),"error");
        assertEquals(result.getModel().get("errorMessage"),productResponse.getMessage());
        assertEquals(result.getModel().get("statusCode"),productResponse.getStatusCode());
    }
    @Test
    public void getProductDetails_sendValidProductResponseAndInvalidRateResponse_expectedErrorView(){
        //arrange
        Category womenDresses=new Category();
        womenDresses.setName("women's dresses");
        womenDresses.setImagePath("dummy pic");
        Product product1=new Product("tshirt","dummy pic",120.0,womenDresses,12);
        product1.setRate(5.0);
        Response<Product> productResponse=new Response("Ok",200,false,false,product1);
        Response<Product> rateResponse=new Response("error",500,true);
        when(productServiceMock.getProductsById(anyInt())).thenReturn(productResponse);
        when(rateServiceMock.setProductRate(any())).thenReturn(rateResponse);
        //act
        ModelAndView result=productController.getProductDetails(1);
        //assert
        assertEquals(result.getViewName(),"error");
        assertEquals(result.getModel().get("errorMessage"),rateResponse.getMessage());
        assertEquals(result.getModel().get("statusCode"),rateResponse.getStatusCode());
    }
    @Test
    public void addToCart_SendNullUserId_expectedLoginPage(){
        //arrange
        when(httpSessionMock.getAttribute("user-Id")).thenReturn(null);
        //act
        String result=productController.addToCart(new ShoppingCartProducts(),bindingResultMock,1,httpSessionMock);
        //assert
        assertEquals(result,"redirect:/login");
    }
    //bindingResult.hasErrors()
    @Test
    public void addToCart_sendTrueBindingResultError_expectedProductDetailsPage(){
        //arrange
        when(httpSessionMock.getAttribute("user-Id")).thenReturn(1);
        when(bindingResultMock.hasErrors()).thenReturn(true);
        //act
        String result=productController.addToCart(new ShoppingCartProducts(),bindingResultMock,1,httpSessionMock);
        //assert
        System.out.println(result);
        assertEquals(result,"productDetails");
    }
    @Test//productService.getProductsById(productId)
    public void addToCart_sendFalseBindingResultError_expectedViewAllProductsPage(){
        //arrange
        when(httpSessionMock.getAttribute("user-Id")).thenReturn(1);
        when(bindingResultMock.hasErrors()).thenReturn(false);
        Category womenDresses=new Category();
        womenDresses.setName("women's dresses");
        womenDresses.setImagePath("dummy pic");
        Product product1=new Product("tshirt","dummy pic",120.0,womenDresses,12);
        product1.setRate(5.0);
        Response<Product> productResponse=new Response("Ok",200,false,false,product1);
        when(productServiceMock.getProductsById(anyInt())).thenReturn(productResponse);
        //userService.getUserById(userId);
        User user=new User();
        Response<User> userResponse=new Response("Ok",200,false,false,user);
        when(userServiceMock.getUserById(anyInt())).thenReturn(userResponse);
        //act
        String result=productController.addToCart(new ShoppingCartProducts(),bindingResultMock,1,httpSessionMock);
        //assert
        System.out.println(result);
        assertEquals(result,"redirect:/products/getAllProducts");
    }

    @Test
    public void getAllProducts_SendValidProductResponse_ExpectedViewProductsPage(){
        //arrange
        Category womenDresses=new Category();
        womenDresses.setName("women's dresses");
        womenDresses.setImagePath("dummy pic");
        List<Product> productsList = new ArrayList<>();
        Product product1=new Product("tshirt","dummy pic",120.0,womenDresses,12);
        Product product2=new Product("red tshirt","dummy pic",120.0,womenDresses,1);
        productsList.add(product1);
        productsList.add(product2);
        Response productsResponse=new Response("Ok",200,false,false,productsList);
        when(productServiceMock.getProducts()).thenReturn(productsResponse);
        //act
        String result=productController.getAllProducts(new ModelMap());
        //assert
        assertEquals(result,"viewProducts");
    }
    @Test
    public void getAllProducts_SendInvalidProductResponse_ExpectedErrorPage(){
        //arrange
        Response productsResponse=new Response("error",500,true);
        when(productServiceMock.getProducts()).thenReturn(productsResponse);
        //act
        String result=productController.getAllProducts(new ModelMap());
        //assert
        assertEquals(result,"error");
    }
    @Test
    public void getCategoryProducts_SendValidProductResponse_ExpectedViewProductsPage(){
        //arrange
        Category womenDresses=new Category();
        womenDresses.setName("women's dresses");
        womenDresses.setImagePath("dummy pic");
        List<Product> productsList = new ArrayList<>();
        Product product1=new Product("tshirt","dummy pic",120.0,womenDresses,12);
        Product product2=new Product("red tshirt","dummy pic",120.0,womenDresses,1);
        productsList.add(product1);
        productsList.add(product2);
        Response productsResponse=new Response("Ok",200,false,false,productsList);
        when(productServiceMock.getProductsByCategory(anyInt())).thenReturn(productsResponse);
        //act
        String result=productController.getCategoryProducts(new ModelMap(),1);
        //assert
        assertEquals(result,"viewProducts");
    }
    @Test
    public void getCategoryProducts_SendInvalidProductResponse_ExpectedErrorPage(){
        //arrange
        Response productsResponse=new Response("error",500,true);
        when(productServiceMock.getProductsByCategory(anyInt())).thenReturn(productsResponse);
        //act
        String result=productController.getCategoryProducts(new ModelMap(),1);
        //assert
        assertEquals(result,"error");
    }
}
