package controller;

import org.example.controller.HomeController;
import org.example.entity.Category;
import org.example.entity.Product;
import org.example.model.Response;
import org.example.service.category.CategoryService;
import org.example.service.product.ProductService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class HomeControllerTest {
   private CategoryService categoryServiceMock;
    private ProductService productServiceMock;
    private Model modelMock;
    private HttpSession httpSessionMock;
    private HomeController homeController;
    public HomeControllerTest(){
        httpSessionMock = Mockito.mock(HttpSession.class);
        modelMock = Mockito.mock(Model.class);
        categoryServiceMock = Mockito.mock(CategoryService.class);
        productServiceMock=Mockito.mock(ProductService.class);
        homeController=new HomeController(categoryServiceMock,productServiceMock);
    }
    @Test
    public void getAllCategoriesTest_ValidCategoriesAndProductsResponses_expectedHomePage(){
        //arrange
        when(httpSessionMock.getAttribute("user-Id")).thenReturn(1);
        List<Category> categoriesList = new ArrayList<>();
        Category kidsDresses=new Category();
        kidsDresses.setName("kid's dresses");
        kidsDresses.setImagePath("dummy pic");
        Category womenDresses=new Category();
        womenDresses.setName("women's dresses");
        womenDresses.setImagePath("dummy pic");
        categoriesList.add(kidsDresses);
        categoriesList.add(womenDresses);
        Response categoriesResponse=new Response("Ok",200,false,false,categoriesList);
        when(categoryServiceMock.getAllCategories()).thenReturn(categoriesResponse);
        List<Product> productsList = new ArrayList<>();
        Product product1=new Product("tshirt","dummy pic",120.0,womenDresses,12);
        Product product2=new Product("red tshirt","dummy pic",120.0,kidsDresses,1);
        productsList.add(product1);
        productsList.add(product2);
        Response productsResponse=new Response("Ok",200,false,false,productsList);
        when(productServiceMock.getProducts()).thenReturn(productsResponse);
        ModelAndView modelAndViewExpectedResult = new ModelAndView("home");
        modelAndViewExpectedResult.addObject("categories", categoriesResponse.getObjectToBeReturned());
        modelAndViewExpectedResult.addObject("products", productsResponse.getObjectToBeReturned());
        //act
        ModelAndView result= homeController.getAllItems(httpSessionMock,modelMock);
        assertEquals(result.getViewName(),modelAndViewExpectedResult.getViewName());
        assertEquals(result.getModel().get("products"),modelAndViewExpectedResult.getModel().get("products"));
       assertEquals(result.getModel().get("categories"),modelAndViewExpectedResult.getModel().get("categories"));
    }

    @Test
    public void getAllCategoriesTest_InvalidCategoriesAndValidProductsResponses_expectedErrorPage(){
        //arrange
        when(httpSessionMock.getAttribute("user-Id")).thenReturn(1);
        Category kidsDresses=new Category();
        kidsDresses.setName("kid's dresses");
        kidsDresses.setImagePath("dummy pic");
        Category womenDresses=new Category();
        womenDresses.setName("women's dresses");
        womenDresses.setImagePath("dummy pic");
        Response categoriesResponse=new Response("error",500,true);
        when(categoryServiceMock.getAllCategories()).thenReturn(categoriesResponse);
        List<Product> productsList = new ArrayList<>();
        Product product1=new Product("tshirt","dummy pic",120.0,womenDresses,12);
        Product product2=new Product("red tshirt","dummy pic",120.0,kidsDresses,1);
        productsList.add(product1);
        productsList.add(product2);
        Response productsResponse=new Response("Ok",200,false,false,productsList);
        when(productServiceMock.getProducts()).thenReturn(productsResponse);
        ModelAndView modelAndViewExpectedResult = new ModelAndView("error");
        modelAndViewExpectedResult.addObject("errorMessage", categoriesResponse.getMessage());
        modelAndViewExpectedResult.addObject("statusCode", categoriesResponse.getStatusCode()
        );

        //act
        ModelAndView result= homeController.getAllItems(httpSessionMock,modelMock);
        //System.out.println(result);
        //assert
        assertEquals(result.getViewName(),modelAndViewExpectedResult.getViewName());
        assertEquals(result.getModel().get("errorMessage"),modelAndViewExpectedResult.getModel().get("errorMessage"));
        assertEquals(result.getModel().get("statusCode"),modelAndViewExpectedResult.getModel().get("statusCode"));
    }
    @Test
    public void getAllCategoriesTest_InvalidCategoriesAndInvalidProductsResponses_expectedErrorPage(){
        //arrange
        when(httpSessionMock.getAttribute("user-Id")).thenReturn(1);

        Response categoriesResponse=new Response("error",500,true);
        when(categoryServiceMock.getAllCategories()).thenReturn(categoriesResponse);
        Response productsResponse=new Response("error",500,true);
        when(productServiceMock.getProducts()).thenReturn(productsResponse);
        ModelAndView modelAndViewExpectedResult = new ModelAndView("error");
        modelAndViewExpectedResult.addObject("errorMessage", productsResponse.getMessage());
        modelAndViewExpectedResult.addObject("statusCode", productsResponse.getStatusCode()
        );

        //act
        ModelAndView result= homeController.getAllItems(httpSessionMock,modelMock);
        //System.out.println(result);
        //assert
        assertEquals(result.getViewName(),modelAndViewExpectedResult.getViewName());
        assertEquals(result.getModel().get("errorMessage"),modelAndViewExpectedResult.getModel().get("errorMessage"));
        assertEquals(result.getModel().get("statusCode"),modelAndViewExpectedResult.getModel().get("statusCode"));
    }


    @Test
    public void getAllCategoriesTest_validCategoriesAndInvalidProductsResponses_expectedErrorPage(){
        //arrange
        when(httpSessionMock.getAttribute("user-Id")).thenReturn(1);
        List<Category> categoriesList = new ArrayList<>();
        Category kidsDresses=new Category();
        kidsDresses.setName("kid's dresses");
        kidsDresses.setImagePath("dummy pic");
        Category womenDresses=new Category();
        womenDresses.setName("women's dresses");
        womenDresses.setImagePath("dummy pic");
        categoriesList.add(kidsDresses);
        categoriesList.add(womenDresses);
        Response categoriesResponse=new Response("Ok",200,false,false,categoriesList);
        when(categoryServiceMock.getAllCategories()).thenReturn(categoriesResponse);
        Response productsResponse=new Response("error",500,true);
        when(productServiceMock.getProducts()).thenReturn(productsResponse);
        ModelAndView modelAndViewExpectedResult = new ModelAndView("error");
        modelAndViewExpectedResult.addObject("errorMessage", productsResponse.getMessage());
        modelAndViewExpectedResult.addObject("statusCode", productsResponse.getStatusCode()
        );

        //act
        ModelAndView result= homeController.getAllItems(httpSessionMock,modelMock);
        //System.out.println(result);
        //assert
        assertEquals(result.getViewName(),modelAndViewExpectedResult.getViewName());
        assertEquals(result.getModel().get("errorMessage"),modelAndViewExpectedResult.getModel().get("errorMessage"));
        assertEquals(result.getModel().get("statusCode"),modelAndViewExpectedResult.getModel().get("statusCode"));
    }
}
