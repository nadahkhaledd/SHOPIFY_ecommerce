import org.example.entity.Category;
import org.example.model.Response;
import org.example.repository.category.CategoryRepository;
import org.example.service.category.CategoryService;
import org.example.service.category.CategoryServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class CategoryServiceTest {

    private CategoryService categoryService;
    private CategoryRepository categoryRepositoryMock;

    public CategoryServiceTest() {
        categoryRepositoryMock = Mockito.mock(CategoryRepository.class);
        categoryService = new CategoryServiceImplementation(categoryRepositoryMock);
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    // Test addCategory
    @Test
    public void testAddCategory_sendCategoryEntity_thenSaveToDB() {
        /*Arrange*/

        Category category = new Category("accessories",
                "https://firebasestorage.googleapis.com/v0/b/e-commerce-9ac2b.appspot.com/o/categories%2Fcat-4.jpg?alt=media&token=ecba3f3a-a12b-49e9-9617-a44ce858ec2e");

        when(categoryRepositoryMock.getCategoryByName(any(String.class))).thenReturn(new Response("Done", 200, false, null));
        when(categoryRepositoryMock.addCategory(any(Category.class))).thenReturn(new Response("Done", 200, false));

        /*ACT*/
        Response response = categoryService.addCategory(category);

        //Assert
        assertEquals(200, response.getStatusCode());
        verify(categoryRepositoryMock, times(1)).addCategory(any(Category.class));
    }

    @Test(expected = NullPointerException.class)
    public void testAddCategory_sendNullCategoryEntity_returnNullPointerException() {
        /*ACT*/
        categoryService.addCategory(null);

    }

    // Test updateCategory
    @Test
    public void testUpdateCategory_sendUpdatedCategoryEntity_thenSaveToDB() {
        /*Arrange*/

        Category category = new Category("accessories",
                "https://firebasestorage.googleapis.com/v0/b/e-commerce-9ac2b.appspot.com/o/categories%2Fcat-4.jpg?alt=media&token=ecba3f3a-a12b-49e9-9617-a44ce858ec2e");

//        when(categoryRepositoryMock.getCategoryByName(any(String.class))).thenReturn(new Response("Done", 200, false, null));
//        when(categoryRepositoryMock.getCategoryByID(any(Integer.class))).thenReturn(new Response("Done", 200, false, category));
//
//        when(categoryRepositoryMock.addCategory(any(Category.class))).thenReturn(new Response("Done", 200, false));
        when(categoryRepositoryMock.updateCategory(any(Category.class))).thenReturn(new Response("Done", 200, false));

        /*ACT*/
        Response response = categoryService.updateCategory(category);

        //Assert
        assertEquals(200, response.getStatusCode());
        verify(categoryRepositoryMock, times(1)).updateCategory(any(Category.class));
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateCategory_sendNullCategoryEntity_returnNullPointerException() {
        /*ACT*/
        categoryService.updateCategory(null);

    }

    // Test removeCategory
    @Test
    public void testRemoveCategory_sendCategoryEntity_thenReturnTrue() {
        /*Arrange*/
        Category category = new Category("accessories",
                "https://firebasestorage.googleapis.com/v0/b/e-commerce-9ac2b.appspot.com/o/categories%2Fcat-4.jpg?alt=media&token=ecba3f3a-a12b-49e9-9617-a44ce858ec2e");

        Response response = new Response("Done", 200, false, false, true);
        when(categoryRepositoryMock.removeCategory(any(Category.class)))
                .thenReturn(response);

        /*ACT*/
        Response returnedResponse = categoryService.removeCategory(category);

        /*Assert*/
        assertNotNull(returnedResponse);
        assertEquals(response, returnedResponse);
        verify(categoryRepositoryMock, times(1)).removeCategory(any());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveCategory_sendNullCategoryEntity_returnNullPointerException() {
        /*ACT*/
        categoryService.removeCategory(null);

    }

    @Test
    public void testGetAllCategories_returnAllSavedCategoryEntities() {
        /*Arrange*/
        List<Category> list = new ArrayList<>();
        list.add(new Category("accessories",
                "https://firebasestorage.googleapis.com/v0/b/e-commerce-9ac2b.appspot.com/o/categories%2Fcat-4.jpg?alt=media&token=ecba3f3a-a12b-49e9-9617-a44ce858ec2e"));
        list.add(new Category("bags",
                "https://firebasestorage.googleapis.com/v0/b/e-commerce-9ac2b.appspot.com/o/categories%2Fcat-4.jpg?alt=media&token=ecba3f3a-a12b-49e9-9617-a44ce858ec2e"));
        when(categoryRepositoryMock.getAllCategories()).thenReturn(new Response("Done", 200, false, list));

        /*ACT*/
        List<Category> returnedCategories = categoryService.getAllCategories().getObjectToBeReturned();

        /*Assert*/
        assertNotNull(returnedCategories);
        assertEquals(2, returnedCategories.size());
        verify(categoryRepositoryMock, times(1)).getAllCategories();
    }

    // Test getCategoryByName
    @Test
    public void testGetCategoryByName_sendCategoryName_returnCategoryEntity() {
        /*Arrange*/
        Category category = new Category("accessories",
                "https://firebasestorage.googleapis.com/v0/b/e-commerce-9ac2b.appspot.com/o/categories%2Fcat-4.jpg?alt=media&token=ecba3f3a-a12b-49e9-9617-a44ce858ec2e");

        Response response = new Response("Done", 200, false, category);
        when(categoryRepositoryMock.getCategoryByName(any(String.class)))
                .thenReturn(response);

        /*ACT*/
        Response returnedResponse = categoryService.getCategoryByName("accessories");

        /*Assert*/
        assertNotNull(returnedResponse);
        assertEquals(returnedResponse.getObjectToBeReturned(), category);
        verify(categoryRepositoryMock, times(1)).getCategoryByName(any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCategoryByName_sendBlankString_returnIllegalArgumentException() {
        /*ACT*/
        categoryService.getCategoryByName(" ");
    }

    @Test(expected = NullPointerException.class)
    public void testGetCategoryByName_sendNull_returnNullPointerException() {
        /*ACT*/
        categoryService.getCategoryByName(null);
    }

    // Test getCategoryByID
    @Test
    public void testGetCategoryByID_sendCategoryID_returnCategoryEntity() {
        /*Arrange*/
        Category category = new Category("accessories",
                "https://firebasestorage.googleapis.com/v0/b/e-commerce-9ac2b.appspot.com/o/categories%2Fcat-4.jpg?alt=media&token=ecba3f3a-a12b-49e9-9617-a44ce858ec2e");

        category.setId(1);
        Response<Category> response = new Response("Done", 200, false, category);
        when(categoryRepositoryMock.getCategoryByID(any(Integer.class)))
                .thenReturn(response);

        /*ACT*/
        Response<Category> returnedResponse = categoryService.getCategoryByID(1);

        /*Assert*/
        assertNotNull(returnedResponse);
        assertEquals(returnedResponse.getObjectToBeReturned(), category);
        verify(categoryRepositoryMock, times(1)).getCategoryByID(anyInt());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCategoryByID_sendNullCategoryEntity_returnIllegalArgumentException() {
        /*ACT*/
        categoryService.getCategoryByID(-1);

    }

    // Test searchByCategoryName
    @Test
    public void testSearchByCategoryName_sendPartialName_returnAllMatchedCategories() {
        /*Arrange*/
        List<Category> list = new ArrayList<>();
        list.add(new Category("bag",
                "https://firebasestorage.googleapis.com/v0/b/e-commerce-9ac2b.appspot.com/o/categories%2Fcat-4.jpg?alt=media&token=ecba3f3a-a12b-49e9-9617-a44ce858ec2e"));
        list.add(new Category("bags",
                "https://firebasestorage.googleapis.com/v0/b/e-commerce-9ac2b.appspot.com/o/categories%2Fcat-4.jpg?alt=media&token=ecba3f3a-a12b-49e9-9617-a44ce858ec2e"));
        when(categoryRepositoryMock.searchByCategoryName(anyString())).thenReturn(new Response("Done", 200, false, list));

        /*ACT*/
        List<Category> returnedCategories = categoryService.searchByCategoryName("bag").getObjectToBeReturned();

        /*Assert*/
        assertNotNull(returnedCategories);
        assertEquals(2, returnedCategories.size());
        verify(categoryRepositoryMock, times(1)).searchByCategoryName(anyString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchByCategoryName_sendBlankString_returnIllegalArgumentException() {
        /*ACT*/
        categoryService.searchByCategoryName(" ");
    }

    @Test(expected = NullPointerException.class)
    public void testSearchByCategoryName_sendNull_returnNullPointerException() {
        /*ACT*/
        categoryService.searchByCategoryName(null);
    }

    @Test
    public void testGetCategoriesNames_returnAllCategoriesNames() {
        /*Arrange*/
        List<String> list = new ArrayList<>();
        list.add("bags");
        list.add("earrings");
        when(categoryRepositoryMock.getCategoriesNames()).thenReturn(new Response("Done", 200, false, list));

        /*ACT*/
        List<String> returnedNames = categoryService.getCategoriesNames().getObjectToBeReturned();

        /*Assert*/
        assertNotNull(returnedNames);
        assertEquals(2, returnedNames.size());
        assertEquals("bags", returnedNames.get(0));
        verify(categoryRepositoryMock, times(1)).getCategoriesNames();
    }



}
