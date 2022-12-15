import org.example.entity.Category;
import org.example.entity.Product;
import org.example.model.Response;
import org.example.repository.product.ProductRepo;
import org.example.service.product.ProductService;
import org.example.service.product.ProductServiceImpl;
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

public class ProductServiceTest {
    private ProductRepo productRepositoryMock;
    private ProductService productService;
    public ProductServiceTest(){
      productRepositoryMock=Mockito.mock(ProductRepo.class);
      productService=new ProductServiceImpl(productRepositoryMock) ;
    }
    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void getProductsByCategoryTest_sendCategoryId_returnResponseHavingTheProductsListUnderThisCategory(){
        //arrange
        List<Product> list = new ArrayList<>();
        Category mensDresses=new Category();
        mensDresses.setName("men's dresses");
        mensDresses.setImagePath("dummy pic");
        mensDresses.setId(1);
        Product product1=new Product("tshirt","dummy pic",120.0,mensDresses,12);
        Product product2=new Product("red tshirt","dummy pic",120.0,mensDresses,1);
        list.add(product1);
        list.add(product2);
        mensDresses.setProducts(list);
        Response<List<Product>> response=new Response("Ok",200,false,false,list);
        when(productRepositoryMock.getProductsByCategory(anyInt())).thenReturn(response);
        //act
        Response<List<Product>> productsResponse=productService.getProductsByCategory(1);
        //assert
        assertNotNull(productsResponse);
        assertEquals(2, productsResponse.getObjectToBeReturned().size());
        verify(productRepositoryMock, times(1)).getProductsByCategory(anyInt());

    }
    @Test
    public void getALlProductsTest_returnResponseHavingTheProductsList(){
        //arrange
        List<Product> list = new ArrayList<>();
        Category mensDresses=new Category();
        mensDresses.setName("men's dresses");
        mensDresses.setImagePath("dummy pic");
        Product product1=new Product("tshirt","dummy pic",120.0,mensDresses,12);
        Product product2=new Product("red tshirt","dummy pic",120.0,mensDresses,1);
        list.add(product1);
        list.add(product2);
        mensDresses.setProducts(list);
        Response<List<Product>> response=new Response("Ok",200,false,false,list);
        when(productRepositoryMock.getProducts()).thenReturn(response);

        //act
        Response<List<Product>> productsResponse=productService.getProducts();
        //assert
        assertNotNull(productsResponse);
        assertEquals(2, productsResponse.getObjectToBeReturned().size());
        verify(productRepositoryMock, times(1)).getProducts();

    }

    @Test
    public void getProductById_sendProductId_returnResponseHavingTheProductWithTheGivenId(){
        //arrange
        Category mensDresses=new Category();
        mensDresses.setName("men's dresses");
        mensDresses.setImagePath("dummy pic");
        Product product1=new Product("tshirt","dummy pic",120.0,mensDresses,12);
        Response<Product> expectedResponse=new Response<Product>("Ok",200,false,false,product1);
        when(productRepositoryMock.getProductsById(anyInt())).thenReturn(expectedResponse);
        //act
        Response<Product> productResponse=productService.getProductsById(1);
        //assert
        assertNotNull(productResponse.getObjectToBeReturned());
        assertEquals(expectedResponse,productResponse);
        verify(productRepositoryMock, times(1)).getProductsById(anyInt());

    }
    @Test
    public void searchByProductNameTest_sendSearchValue_returnResponseHavingTheProductsListWithNamesMatchingTheSearchValue(){
        //arrange
        String productName="test";
        List<Product> list = new ArrayList<>();
        Category mensDresses=new Category();
        mensDresses.setName("men's dresses");
        mensDresses.setImagePath("dummy pic");
        Product product1=new Product("test1","dummy pic",120.0,mensDresses,12);
        Product product2=new Product("test2","dummy pic",120.0,mensDresses,1);
        list.add(product1);
        list.add(product2);
        mensDresses.setProducts(list);
        Response<List<Product>> expectedResponse=new Response<List<Product>>("Ok",200,false,false,list);
        when(productRepositoryMock.searchByProductName(any())).thenReturn(expectedResponse);
        //act
        Response<List<Product>> searchResponse=productService.searchByProductName(productName);
        //assert
        assertNotNull(searchResponse);
        assertEquals(expectedResponse,searchResponse);
        verify(productRepositoryMock, times(1)).searchByProductName(any());


    }
    @Test
    public void updateProductQuantityTest_SendNewQuantity_sendProductId_returnResponseHavingBooleanValue(){
        //arrange
        int newQuantity=20;
        int productId=1;
        Response<Boolean> expectedResponse=new Response<Boolean>("Ok",200,false,false,true);
        when(productRepositoryMock.updateProductQuantity(anyInt(),anyInt())).thenReturn(expectedResponse);
        //act
        Response<Boolean>  updateQuantityResponse=productService.updateProductQuantity(productId,newQuantity);
        //assert
        assertNotNull(updateQuantityResponse);
        assertEquals(expectedResponse,updateQuantityResponse);
        verify(productRepositoryMock, times(1)).updateProductQuantity(anyInt(),anyInt());

    }

    @Test
    public void addProduct_sendProduct_returnResponseObject(){
        //arrange
        Category mensDresses=new Category();
        mensDresses.setName("men's dresses");
        mensDresses.setImagePath("dummy pic");
        Product product1=new Product("tshirt","dummy pic",120.0,mensDresses,12);
        Response expectedResponse=new Response("Ok",200,false,false);
        when(productRepositoryMock.addProduct(any())).thenReturn(expectedResponse);
       //act
        Response productResponse=productService.addProduct(product1);
        //assert
        assertNotNull(productResponse);
        assertEquals(expectedResponse,productResponse);
        verify(productRepositoryMock, times(1)).addProduct(any());

    }

    @Test
    public void updateProduct_sendProduct_returnResponseObject(){
        //arrange
        Category mensDresses=new Category();
        mensDresses.setName("men's dresses");
        mensDresses.setImagePath("dummy pic");
        Product product1=new Product("tshirt","dummy pic",120.0,mensDresses,12);

        Response expectedResponse=new Response("Ok",200,false,false);

        when(productRepositoryMock.updateProduct(any())).thenReturn(expectedResponse);
        //act
        Response productResponse=productService.updateProduct(product1);
        //assert
        assertNotNull(productResponse);
        assertEquals(expectedResponse,productResponse);
        verify(productRepositoryMock, times(1)).updateProduct(any());


    }
    @Test(expected = NullPointerException.class)
    public void deleteProduct_sendNullProduct_returnException(){
        productService.deleteProduct(null);
    }
    @Test(expected = NullPointerException.class)
    public void searchByProductName_sendNullProductName_returnException(){
        productService.searchByProductName(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void searchByProductName_sendBlankProductName_returnException(){
        productService.searchByProductName("    ");
    }
    @Test(expected = NullPointerException.class)
    public void updateProduct_sendNullProduct_returnException(){
        productService.updateProduct(null);
    }
    @Test(expected = NullPointerException.class)
    public void addProductTest_sendNullProduct_returnException(){
        productService.addProduct(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void getProductBYIdTest_sendNegativeNumberAsId_returnException(){
        productService.getProductsById(-1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void updateProductQuantityTest_sendNegativeNumberAsId_returnException(){
        productService.updateProductQuantity(-1,9);
    }
    @Test(expected = IllegalArgumentException.class)
    public void updateProductQuantityTest_sendNegativeNumberAsQuantity_returnException(){
        productService.updateProductQuantity(1,-1);
    }
    @Test
    public void deleteProductTest_sendProduct_returnResponseObject(){
        //arrange
        Category mensDresses=new Category();
        mensDresses.setName("men's dresses");
        mensDresses.setImagePath("dummy pic");
        Product product1=new Product("tshirt","dummy pic",120.0,mensDresses,12);
        Response expectedResponse=new Response("Ok",200,false,false);
        when(productRepositoryMock.deleteProduct(any())).thenReturn(expectedResponse);
        //act
        Response productResponse=productService.deleteProduct(product1);
        //assert
        assertNotNull(productResponse);
        assertEquals(expectedResponse,productResponse);
        verify(productRepositoryMock, times(1)).deleteProduct(any());

    }
}

