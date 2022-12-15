package controller;

import org.example.controller.CategoryController;
import org.example.entity.Category;
import org.example.model.Response;
import org.example.service.category.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryControllerTest {

    private CategoryController categoryController;

    private CategoryService categoryServiceMock;
    private Response responseMock;
    private ModelMap modelMapMock;

    public CategoryControllerTest() {
        categoryServiceMock = Mockito.mock(CategoryService.class);
        responseMock = Mockito.mock(Response.class);
        modelMapMock = Mockito.mock(ModelMap.class);
        categoryController = new CategoryController();
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCategories_givenError_returnErrorPage() {
        //Arrange
        when(categoryServiceMock.getAllCategories()).thenReturn(new Response("error occurred while processing your request", 500, true));
        when(responseMock.isErrorOccurred()).thenReturn(true);
        when(modelMapMock.put(anyString(), any())).thenReturn("");

        //ACT
        String response = categoryController.getAllCategories(modelMapMock);

        //Assert
        assertEquals("error", response);
    }

    @Test
    public void testGetAllCategories_givenValidCategoryData_returnViewCategoriesPage(){
        //Arrange
        when(categoryServiceMock.getAllCategories()).thenReturn(new Response("Done", 200, false, new ArrayList<>()));
        when(responseMock.isErrorOccurred()).thenReturn(false);
        when(modelMapMock.addAttribute(anyString(), any())).thenReturn(modelMapMock);

        //ACT
        String response = categoryController.getAllCategories(modelMapMock);

        //Assert
        assertEquals("viewAllCategories", response);

    }
}
