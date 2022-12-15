package controller;

import helpers.HelperMethods;
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
    private HelperMethods helperMethods;
    public HomeControllerTest(){
        httpSessionMock = Mockito.mock(HttpSession.class);
        modelMock = Mockito.mock(Model.class);
        categoryServiceMock = Mockito.mock(CategoryService.class);
        productServiceMock=Mockito.mock(ProductService.class);
        homeController=new HomeController(categoryServiceMock,productServiceMock);
        helperMethods=new HelperMethods();
    }
    @Test
    public void getAllCategoriesTest_ValidCategoriesAndProductsResponses_expectedHomePage(){
        //arrange
        when(httpSessionMock.getAttribute("user-Id")).thenReturn(1);
        List<Category> categoriesList = helperMethods.initCategoryList();
        Response categoriesResponse=new Response("Ok",200,false,false,categoriesList);
        when(categoryServiceMock.getAllCategories()).thenReturn(categoriesResponse);
        List<Product> productsList =helperMethods.initProductsList();
        Response productsResponse=new Response("Ok",200,false,false,productsList);
        when(productServiceMock.getProducts()).thenReturn(productsResponse);
        ModelAndView modelAndViewExpectedResult = new ModelAndView("home");
        //act
        ModelAndView result= homeController.getAllItems(httpSessionMock,modelMock);
        //assert
        assertEquals(result.getViewName(),modelAndViewExpectedResult.getViewName());
    }

    @Test
    public void getAllCategoriesTest_InvalidCategoriesAndValidProductsResponses_expectedErrorPage(){
        //arrange
        when(httpSessionMock.getAttribute("user-Id")).thenReturn(1);

        Response categoriesResponse=new Response("error",500,true);
        when(categoryServiceMock.getAllCategories()).thenReturn(categoriesResponse);
        List<Product> productsList = helperMethods.initProductsList();
        Response productsResponse=new Response("Ok",200,false,false,productsList);
        when(productServiceMock.getProducts()).thenReturn(productsResponse);
        ModelAndView modelAndViewExpectedResult = new ModelAndView("error");

        //act
        ModelAndView result= homeController.getAllItems(httpSessionMock,modelMock);
        //System.out.println(result);
        //assert
        assertEquals(result.getViewName(),modelAndViewExpectedResult.getViewName());
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
        //act
        ModelAndView result= homeController.getAllItems(httpSessionMock,modelMock);
        //assert
        assertEquals(result.getViewName(),modelAndViewExpectedResult.getViewName());
       }


    @Test
    public void getAllCategoriesTest_validCategoriesAndInvalidProductsResponses_expectedErrorPage(){
        //arrange
        when(httpSessionMock.getAttribute("user-Id")).thenReturn(1);
        List<Category> categoriesList =helperMethods.initCategoryList();
        Response categoriesResponse=new Response("Ok",200,false,false,categoriesList);
        when(categoryServiceMock.getAllCategories()).thenReturn(categoriesResponse);
        Response productsResponse=new Response("error",500,true);
        when(productServiceMock.getProducts()).thenReturn(productsResponse);
        ModelAndView modelAndViewExpectedResult = new ModelAndView("error");
        //act
        ModelAndView result= homeController.getAllItems(httpSessionMock,modelMock);
        //assert
        assertEquals(result.getViewName(),modelAndViewExpectedResult.getViewName());
       }
}
