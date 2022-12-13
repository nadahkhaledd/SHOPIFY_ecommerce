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
        mensDresses.setId(1);
        Product product1=new Product("tshirt","dummy pic",120.0,mensDresses,12);
        Product product2=new Product("red tshirt","dummy pic",120.0,mensDresses,1);
        list.add(product1);
        list.add(product2);
        mensDresses.setProducts(list);
        Response<List<Product>> response=new Response("Ok",200,false,false,list);
        when(productRepositoryMock.getProducts()).thenReturn(response);
        //act
        Response<List<Product>> productsResponse=productService.getProducts();
        assertNotNull(productsResponse);
        assertEquals(2, productsResponse.getObjectToBeReturned().size());
        verify(productRepositoryMock, times(1)).getProducts();

    }

    @Test
    public void getProductById_sendProductId_returnResponseHavingTheProductWithTheGivenId(){
        Category mensDresses=new Category();
        mensDresses.setName("men's dresses");
        mensDresses.setImagePath("dummy pic");
        mensDresses.setId(1);
        Product product1=new Product("tshirt","dummy pic",120.0,mensDresses,12);
        product1.setId(1);
        Response<Product> expectedResponse=new Response<Product>("Ok",200,false,false,product1);
        when(productRepositoryMock.getProduct(anyInt())).thenReturn(expectedResponse);
        Response<Product> productResponse=productService.getProduct(1);
        assertNotNull(productResponse.getObjectToBeReturned());
        assertEquals(expectedResponse,productResponse);
        verify(productRepositoryMock, times(1)).getProduct(anyInt());

    }
    @Test
    public void searchByProductNameTest_sendSearchValue_returnResponseHavingTheProductsListWithNamesMatchingTheSearchValue(){
        String productName="test";
        List<Product> list = new ArrayList<>();
        Category mensDresses=new Category();
        mensDresses.setName("men's dresses");
        mensDresses.setImagePath("dummy pic");
        mensDresses.setId(1);
        Product product1=new Product("test1","dummy pic",120.0,mensDresses,12);
        Product product2=new Product("test2","dummy pic",120.0,mensDresses,1);
        list.add(product1);
        list.add(product2);
        mensDresses.setProducts(list);
        Response<List<Product>> expectedResponse=new Response<List<Product>>("Ok",200,false,false,list);
        when(productRepositoryMock.searchByProductName(any())).thenReturn(expectedResponse);
        Response<List<Product>> searchResponse=productService.searchByProductName(productName);
        assertNotNull(searchResponse);
        assertEquals(expectedResponse,searchResponse);
        verify(productRepositoryMock, times(1)).searchByProductName(any());


    }
    @Test
    public void updateProductQuantityTest_SendNewQuantity_sendProductId_returnResponseHavingBooleanValue(){
        int newQuantity=20;
        int productId=1;
        Response<Boolean> expectedResponse=new Response<Boolean>("Ok",200,false,false,true);
        when(productRepositoryMock.updateProductQuantity(anyInt(),anyInt())).thenReturn(expectedResponse);
        Response<Boolean>  updateQuantityResponse=productService.updateProductQuantity(productId,newQuantity);
        assertNotNull(updateQuantityResponse);
        assertEquals(expectedResponse,updateQuantityResponse);
        verify(productRepositoryMock, times(1)).updateProductQuantity(anyInt(),anyInt());

    }

    @Test
    public void addProduct_sendProduct_returnResponseObject(){
        Category mensDresses=new Category();
        mensDresses.setName("men's dresses");
        mensDresses.setImagePath("dummy pic");
        mensDresses.setId(1);
        Product product1=new Product("tshirt","dummy pic",120.0,mensDresses,12);
        product1.setId(1);
        Response expectedResponse=new Response("Ok",200,false,false);
        when(productRepositoryMock.addProduct(any())).thenReturn(expectedResponse);
        Response productResponse=productService.addProduct(product1);
        assertNotNull(productResponse);
        assertEquals(expectedResponse,productResponse);
        verify(productRepositoryMock, times(1)).addProduct(any());

    }

    @Test
    public void updateProduct_sendProduct_returnResponseObject(){
        Category mensDresses=new Category();
        mensDresses.setName("men's dresses");
        mensDresses.setImagePath("dummy pic");
        mensDresses.setId(1);
        Product product1=new Product("tshirt","dummy pic",120.0,mensDresses,12);
        product1.setId(1);
        Response expectedResponse=new Response("Ok",200,false,false);

        when(productRepositoryMock.updateProduct(any())).thenReturn(expectedResponse);
        Response productResponse=productService.updateProduct(any());
        assertNotNull(productResponse);
        assertEquals(expectedResponse,productResponse);
        verify(productRepositoryMock, times(1)).updateProduct(any());


    }
    @Test(expected = NullPointerException.class)
    public void deleteProduct_sendNullProduct_returnException(){
        productService.deleteProduct(null);
       // verify(productRepositoryMock, times(1)).deleteProduct(null);

    }
    @Test(expected = NullPointerException.class)
    public void searchByProductName_sendNullProductName_returnException(){
        productService.searchByProductName(null);
        // verify(productRepositoryMock, times(1)).deleteProduct(null);

    }
    @Test
    public void deleteProduct_sendProduct_returnResponseObject(){
        Category mensDresses=new Category();
        mensDresses.setName("men's dresses");
        mensDresses.setImagePath("dummy pic");
        mensDresses.setId(1);
        Product product1=new Product("tshirt","dummy pic",120.0,mensDresses,12);
        product1.setId(1);
        Response expectedResponse=new Response("Ok",200,false,false);
        when(productRepositoryMock.deleteProduct(any())).thenReturn(expectedResponse);
        Response productResponse=productService.deleteProduct(product1);
        assertNotNull(productResponse);
        assertEquals(expectedResponse,productResponse);
        verify(productRepositoryMock, times(1)).deleteProduct(any());

    }
}

