import org.example.entity.*;
import org.example.enums.CustomerStatus;
import org.example.enums.Gender;
import org.example.model.Response;
import org.example.service.shoppingcartproducts.ShoppingCartProductsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import org.example.repository.shoppingcartproducts.ShoppingCartProductsRepository;
import org.example.service.shoppingcartproducts.ShoppingCartProductsService;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ShoppingCartProductsServiceTest {
    private final ShoppingCartProductsService shoppingCartProductsService;
    private final ShoppingCartProductsRepository shoppingCartProductsRepositoryMock;

    public ShoppingCartProductsServiceTest() {
        shoppingCartProductsRepositoryMock = Mockito.mock(ShoppingCartProductsRepository.class);
        shoppingCartProductsService = new ShoppingCartProductsServiceImpl(shoppingCartProductsRepositoryMock);
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void viewCartTest_sendUserId_returnAllUsersProductsInShoppingCart() {
        //Arrange
        Customer customer = new Customer(1,"Youssef", "Medhat", "ym.desouky@gmail.com", "12345", Gender.male,
                new Date(), CustomerStatus.ACTIVATED, 0);
        Product product1 = new Product("Product_1", "imagePath", 14.0, null, 10);
        List<ShoppingCartProducts> cart = new ArrayList<>();
        cart.add(new ShoppingCartProducts(3, product1, customer));
        Response response = new Response<>("Done", 200, false, cart);

        when(shoppingCartProductsRepositoryMock.viewCart(anyInt()))
                .thenReturn(response);

        //Act
        List<ShoppingCartProducts> returnedShoppingCart = shoppingCartProductsService.viewCart(1).getObjectToBeReturned();

        //Assert
        assertNotNull(returnedShoppingCart);
        assertEquals(1, returnedShoppingCart.size());
        verify(shoppingCartProductsRepositoryMock, times(1)).viewCart(anyInt());
    }

    @Test(expected = IllegalArgumentException.class)
    public void viewCartTest_sendInvalidUserId_returnIllegalArgumentException() {
        //Act
        shoppingCartProductsService.viewCart(0);
    }

    @Test
    public void getCartItemTest_acceptProductAndUser_returnShoppingCartProduct() {
        //Arrange
        Customer customer = new Customer(1,"Youssef", "Medhat", "ym.desouky@gmail.com", "12345", Gender.male,
                new Date(), CustomerStatus.ACTIVATED, 0);
        Product product1 = new Product("Product_1", "imagePath", 14.0, null, 10);
        ShoppingCartProducts cartProduct = new ShoppingCartProducts(3, product1, customer);
        Response<ShoppingCartProducts> response = new Response<ShoppingCartProducts>("Done", 200, false, cartProduct);

        when(shoppingCartProductsRepositoryMock.getCartItem(any(Product.class), any(Customer.class))).thenReturn(response);

        //Act
        Product returnedProduct = shoppingCartProductsService.getCartItem(product1, customer).getObjectToBeReturned().getProduct();

        //Assert
        assertNotNull(returnedProduct);
        assertEquals(product1, returnedProduct);
        verify(shoppingCartProductsRepositoryMock, times(1)).getCartItem(any(Product.class), any(Customer.class));
    }

    @Test
    public void getCartItemTest_acceptCartItemId_returnShoppingCartProduct() {
        //Arrange
        Customer customer = new Customer(1,"Youssef", "Medhat", "ym.desouky@gmail.com", "12345", Gender.male,
                new Date(), CustomerStatus.ACTIVATED, 0);
        Product product1 = new Product("Product_1", "imagePath", 14.0, null, 10);
        Response<ShoppingCartProducts> response = new Response<ShoppingCartProducts>("Done", 200, false,
                new ShoppingCartProducts(3, product1, customer));

        when(shoppingCartProductsRepositoryMock.getCartItem(anyInt())).thenReturn(response);
        //Act
        ShoppingCartProducts returnedProduct = shoppingCartProductsService.getCartItem(1).getObjectToBeReturned();
        //Assert
        assertNotNull(returnedProduct);
        assertEquals("Product_1", returnedProduct.getProduct().getName());
        verify(shoppingCartProductsRepositoryMock, times(1)).getCartItem(anyInt());
    }

    @Test(expected = NullPointerException.class)
    public void getCartItemTest_sendNullProductOrUser_returnNullPointerException() {
        //Act
        shoppingCartProductsService.getCartItem(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCartItemTest_sendInvalidCartItemId_returnIllegalArgumentException() {
        //Act
        shoppingCartProductsService.getCartItem(0);
    }

    @Test
    public void calculateTotalTest_sendUserId_returnUsersShoppingCartProductsTotalPrice() {
        //Arrange
        Customer customer = new Customer(1,"Youssef", "Medhat", "ym.desouky@gmail.com", "12345", Gender.male,
                new Date(), CustomerStatus.ACTIVATED, 0);
        Product product1 = new Product("Product_1", "imagePath", 14.0, null, 10);
        Product product2 = new Product("Product_2", "imagePath", 15.0, null, 16);
        List<ShoppingCartProducts> cart = new ArrayList<>();
        cart.add(new ShoppingCartProducts(1, product1, customer));
        cart.add(new ShoppingCartProducts(1, product2, customer));
        Response<Double> response = new Response<Double>("Done", 200, false,
                cart.get(0).getProduct().getPrice()+cart.get(1).getProduct().getPrice());

        when(shoppingCartProductsRepositoryMock.calculateTotal(anyInt())).thenReturn(response);
        //Act
        Double returnedCartTotal = shoppingCartProductsService.calculateTotal(1).getObjectToBeReturned();
        //Assert
        assertNotEquals(0.0, returnedCartTotal);
        assertEquals(Double.valueOf(29.0), returnedCartTotal);
        verify(shoppingCartProductsRepositoryMock, times(1)).calculateTotal(anyInt());
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateTotalTest_sendInvalidUserId_returnIllegalArgumentException() {
        shoppingCartProductsService.calculateTotal(0);
    }

    @Test
    public void addToCartTest_sendNewCartItem_thenSaveToDB() {
        //Arrange
        Customer customer = new Customer(1,"Youssef", "Medhat", "ym.desouky@gmail.com", "12345", Gender.male,
                new Date(), CustomerStatus.ACTIVATED, 0);
        Product product1 = new Product("Product_1", "imagePath", 14.0, null, 10);
        Response response = new Response("Done", 200, false, false);

        when(shoppingCartProductsRepositoryMock.addToCart(any(ShoppingCartProducts.class)))
                .thenReturn(response);
        //Act
        Response returnedAddToCartResponse = shoppingCartProductsService.addToCart(new ShoppingCartProducts(3, product1, customer));
        //Assert
        assertNotEquals(null, returnedAddToCartResponse);
        assertEquals("Done", response.getMessage());
        verify(shoppingCartProductsRepositoryMock, times(1)).addToCart(any(ShoppingCartProducts.class));
    }

    @Test
    public void addToCartTest_sendExistingCartItem_thenUpdateExistingCartItem() {
        //Arrange
        Customer customer = new Customer(1,"Youssef", "Medhat", "ym.desouky@gmail.com", "12345", Gender.male,
                new Date(), CustomerStatus.ACTIVATED, 0);
        Product product1 = new Product("Product_1", "imagePath", 14.0, null, 10);
        ShoppingCartProducts cartProduct = new ShoppingCartProducts(1, product1, customer);

        when(shoppingCartProductsRepositoryMock.getCartItem(any(Product.class), any(User.class))).thenReturn(new Response<ShoppingCartProducts>("Done",200,
                false, cartProduct));
        when(shoppingCartProductsRepositoryMock.updateProductQuantityInCart(anyInt(), anyInt()))
                .thenReturn(new Response<>("Done", 200, false, 1));
        //Act
        Response returnedAddToCartResponse = shoppingCartProductsService.addToCart(cartProduct);
        //Assert
        assertNotEquals(null, returnedAddToCartResponse);
        assertEquals(200, returnedAddToCartResponse.getStatusCode());
        verify(shoppingCartProductsRepositoryMock, times(1)).getCartItem(any(Product.class), any(User.class));
        verify(shoppingCartProductsRepositoryMock, times(1)).updateProductQuantityInCart(anyInt(), anyInt());
    }

    @Test(expected = NullPointerException.class)
    public void addToCartTest_sendNullCartItem_returnNullPointerException() {
        shoppingCartProductsService.addToCart(null);
    }

    @Test
    public void updateProductQuantityInCartTest_sendNewQuantityMoreThanAvailableQuantity_returnInvalidActionResponse() {
        //Arrange
        Customer customer = new Customer(1,"Youssef", "Medhat", "ym.desouky@gmail.com", "12345", Gender.male,
                new Date(), CustomerStatus.ACTIVATED, 0);
        Product product1 = new Product("Product_1", "imagePath", 14.0, null, 4);
        ShoppingCartProducts cartProduct = new ShoppingCartProducts(1, product1, customer);

        when(shoppingCartProductsRepositoryMock.getCartItem(anyInt())).thenReturn(new Response<ShoppingCartProducts>("Done",200,
                false, cartProduct));
        //Act
        Response returnedUpdateQuantityInCartResponse = shoppingCartProductsService.updateProductQuantityInCart(1, 5);
        //Assert
        assertNotEquals(null, returnedUpdateQuantityInCartResponse);
        assertEquals(402, returnedUpdateQuantityInCartResponse.getStatusCode());
        verify(shoppingCartProductsRepositoryMock, times(1)).getCartItem(anyInt());
    }

    @Test
    public void updateProductQuantityInCartTest_sendNewQuantityMoreThanZero_thenSaveToDB() {
        //Arrange
        Customer customer = new Customer(1,"Youssef", "Medhat", "ym.desouky@gmail.com", "12345", Gender.male,
                new Date(), CustomerStatus.ACTIVATED, 0);
        Product product1 = new Product("Product_1", "imagePath", 14.0, null, 4);
        ShoppingCartProducts cartProduct = new ShoppingCartProducts(2, product1, customer);

        when(shoppingCartProductsRepositoryMock.getCartItem(anyInt())).thenReturn(new Response<ShoppingCartProducts>("Done",200,
                false, cartProduct));
        when(shoppingCartProductsRepositoryMock.updateProductQuantityInCart(anyInt(), anyInt()))
                .thenReturn(new Response<>("Done", 200, false, 1));
        //Act
        Response returnedUpdateQuantityInCartResponse = shoppingCartProductsService.updateProductQuantityInCart(1, 3);
        //Assert
        assertNotEquals(null, returnedUpdateQuantityInCartResponse);
        assertEquals(1, returnedUpdateQuantityInCartResponse.getObjectToBeReturned());
        verify(shoppingCartProductsRepositoryMock, times(1)).getCartItem(anyInt());
        verify(shoppingCartProductsRepositoryMock, times(1)).updateProductQuantityInCart(anyInt(), anyInt());
    }

    @Test
    public void updateProductQuantityInCartTest_sendNewQuantityLessThanZero_thenRemoveFromDB() {
        //Arrange
        Customer customer = new Customer(1,"Youssef", "Medhat", "ym.desouky@gmail.com", "12345", Gender.male,
                new Date(), CustomerStatus.ACTIVATED, 0);
        Product product1 = new Product("Product_1", "imagePath", 14.0, null, 4);
        ShoppingCartProducts cartProduct = new ShoppingCartProducts(2, product1, customer);

        when(shoppingCartProductsRepositoryMock.getCartItem(anyInt())).thenReturn(new Response<ShoppingCartProducts>("Done",200,
                false, cartProduct));
        when(shoppingCartProductsRepositoryMock.removeFromCart(anyInt()))
                .thenReturn(new Response<>("Done", 200, false, true));
        //Act
        Response returnedUpdateQuantityInCartResponse = shoppingCartProductsService.updateProductQuantityInCart(1, -1);
        //Assert
        assertNotEquals(null, returnedUpdateQuantityInCartResponse);
        assertEquals(0, returnedUpdateQuantityInCartResponse.getObjectToBeReturned());
        verify(shoppingCartProductsRepositoryMock, times(1)).getCartItem(anyInt());
        verify(shoppingCartProductsRepositoryMock, times(1)).removeFromCart(anyInt());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateProductQuantityInCartTest_sendInvalidCartItemId_returnIllegalArgumentException() {
        //Act
        shoppingCartProductsService.updateProductQuantityInCart(0,0);
    }

    @Test
    public void removeFromCartTest_sendCartItemId_thenRemoveFromDB() {
        //Arrange
        Customer customer = new Customer(1,"Youssef", "Medhat", "ym.desouky@gmail.com", "12345", Gender.male,
                new Date(), CustomerStatus.ACTIVATED, 0);
        Product product1 = new Product("Product_1", "imagePath", 14.0, null, 4);
        ShoppingCartProducts cartProduct = new ShoppingCartProducts(2, product1, customer);

        when(shoppingCartProductsRepositoryMock.removeFromCart(anyInt())).thenReturn(new Response<>("Done", 200, false));
        //Act
        Response returnedRemoveFromCartResponse = shoppingCartProductsService.removeFromCart(1);
        //Assert
        assertNotEquals(null, returnedRemoveFromCartResponse);
        assertEquals(200, returnedRemoveFromCartResponse.getStatusCode());
        verify(shoppingCartProductsRepositoryMock, times(1)).removeFromCart(anyInt());
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeFromCartTest_sendInvalidCartItemId() {
        shoppingCartProductsService.removeFromCart(0);
    }
}
