package controller;

import helpers.HelperMethods;
import org.example.controller.SearchController;
import org.example.entity.Category;
import org.example.entity.Product;
import org.example.model.Response;
import org.example.service.category.CategoryService;
import org.example.service.product.ProductService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.Header;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SearchControllerTest {
    private CategoryService categoryServiceMock;
    private ProductService productServiceMock;
    private SearchController searchController;
    private HelperMethods helperMethods;
    public SearchControllerTest(){
        categoryServiceMock = Mockito.mock(CategoryService.class);
        productServiceMock=Mockito.mock(ProductService.class);
        searchController=new SearchController(categoryServiceMock,productServiceMock);
        helperMethods=new HelperMethods();
    }
    @Test
    public void searchByNameTest_sendValidCategoriesAndProductsResponses_expectedViewProductsPage(){
        //arrange
        List<Category> categoriesList = helperMethods.initCategoryList();
        Response categoriesResponse=new Response("Ok",200,false,false,categoriesList);
        when(categoryServiceMock.searchByCategoryName(any())).thenReturn(categoriesResponse);
        List<Product> productsList =helperMethods.initProductsList();
        Response productsResponse=new Response("Ok",200,false,false,productsList);
        when(productServiceMock.searchByProductName(any())).thenReturn(productsResponse);
        //act
        ModelAndView result=searchController.searchByName("test");
        //assert
        assertEquals("viewProducts",result.getViewName());
    }
    @Test
    public void searchByNameTest_sendInvalidCategoriesAndProductsResponses_expectedErrorPage(){
        //arrange

        Response categoriesResponse=new Response("error",500,true);
        when(categoryServiceMock.searchByCategoryName(any())).thenReturn(categoriesResponse);
        List<Product> productsList =helperMethods.initProductsList();
        Response productsResponse=new Response("Ok",200,false,false,productsList);
        when(productServiceMock.searchByProductName(any())).thenReturn(productsResponse);
        //act
        ModelAndView result=searchController.searchByName("test");
        //assert
        assertEquals("error",result.getViewName());
    }
    @Test
    public void searchByNameTest_sendValidCategoriesAndInvalidProductsResponses_expectedErrorPage(){
        //arrange
        List<Category> categoriesList = helperMethods.initCategoryList();
        Response categoriesResponse=new Response("Ok",200,false,false,categoriesList);
        when(categoryServiceMock.searchByCategoryName(any())).thenReturn(categoriesResponse);
        Response productsResponse=new Response("error",500,true);
        when(productServiceMock.searchByProductName(any())).thenReturn(productsResponse);
        //act
        ModelAndView result=searchController.searchByName("test");
        //assert
        assertEquals("error",result.getViewName());
    }
}
