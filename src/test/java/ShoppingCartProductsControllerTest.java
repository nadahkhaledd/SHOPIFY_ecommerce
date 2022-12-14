import org.example.controller.ShoppingCartProductsController;
import org.example.entity.Address;
import org.example.entity.ShoppingCartProducts;
import org.example.model.Response;
import org.example.service.address.AddressService;
import org.example.service.shoppingcartproducts.ShoppingCartProductsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ShoppingCartProductsControllerTest {

    private final ShoppingCartProductsController shoppingCartProductsController;
    private final ShoppingCartProductsService cartServicesMock;
    private final AddressService addressServiceMock;
    private final Model modelMock;
    private final HttpSession httpSessionMock;

    public ShoppingCartProductsControllerTest() {
        cartServicesMock = Mockito.mock(ShoppingCartProductsService.class);
        addressServiceMock = Mockito.mock(AddressService.class);
        shoppingCartProductsController = new ShoppingCartProductsController(cartServicesMock, addressServiceMock);
        modelMock = Mockito.mock(Model.class);
        httpSessionMock = Mockito.mock(HttpSession.class);
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void viewCartTest_sendNullSessionAttribute_returnLoginPage() {
        //Arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(null);

        //Act
        String returnedPage = shoppingCartProductsController.viewCart(modelMock, httpSessionMock);

        //Assert
        assertEquals("redirect:/login", returnedPage);
        verify(httpSessionMock, times(1)).getAttribute(anyString());
    }

    @Test
    public void viewCartTest_sendValidSessionAttribute_ExceptionOccurred_returnErrorPage() {
        //Arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(1);
        Response<List<ShoppingCartProducts>> response = new Response<List<ShoppingCartProducts>>("error", 400, true, false);
        when(cartServicesMock.viewCart(anyInt())).thenReturn(response);

        //Act
        String returnedPage = shoppingCartProductsController.viewCart(modelMock, httpSessionMock);

        //Assert
        assertEquals("error", returnedPage);
        verify(cartServicesMock, times(1)).viewCart(anyInt());
    }

    @Test
    public void viewCartTest_sendValidSessionAttribute_returnCartPage() {
        //Arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(1);
        Response<List<ShoppingCartProducts>> response = new Response<List<ShoppingCartProducts>>("Done", 200,
                false, false, new ArrayList<>());
        when(cartServicesMock.viewCart(anyInt())).thenReturn(response);

        //Act
        String returnedPage = shoppingCartProductsController.viewCart(modelMock, httpSessionMock);

        //Assert
        assertEquals("cart", returnedPage);
        verify(cartServicesMock, times(1)).viewCart(anyInt());
    }

    @Test
    public void updateQuantityTest_sendCartItemIdAndNewQuantity_exceptionOccurred_returnErrorPage() {
        //Arrange
        when(cartServicesMock.updateProductQuantityInCart(anyInt(), anyInt()))
                .thenReturn(new Response("error", 400, true, false, false));

        //Act
        String returnedPage = shoppingCartProductsController.updateQuantity(1,2,modelMock);

        //Assert
        assertEquals("error", returnedPage);
    }

    @Test
    public void updateQuantityTest_sendCartItemIdAndNewQuantity_returnCartPage() {
        //Arrange
        when(cartServicesMock.updateProductQuantityInCart(anyInt(), anyInt()))
                .thenReturn(new Response("Done", 200, false, false, true));

        //Act
        String returnedPage = shoppingCartProductsController.updateQuantity(1,2,modelMock);

        //Assert
        assertEquals("redirect:/cart/view", returnedPage);
    }

    @Test
    public void deleteCartItemTest_sendCartItemId_exceptionOccurred_returnErrorPage() {
        //Arrange
        when(cartServicesMock.removeFromCart(anyInt()))
                .thenReturn(new Response<>("error", 400, true, false, false));

        //Act
        String returnedPage = shoppingCartProductsController.deleteCartItem(1, modelMock);

        //Assert
        assertEquals("error", returnedPage);
    }

    @Test
    public void deleteCartItemTest_sendCartItemId_returnCartPage() {
        //Arrange
        when(cartServicesMock.removeFromCart(anyInt()))
                .thenReturn(new Response<>("Done", 200, false, false, true));

        //Act
        String returnedPage = shoppingCartProductsController.deleteCartItem(1, modelMock);

        //Assert
        assertEquals("redirect:/cart/view", returnedPage);
    }

    @Test
    public void checkOutTest_sendInvalidSessionAttribute_returnLoginPage() {
        //Arrange
        when(httpSessionMock.getAttribute(anyString()))
                .thenReturn(null);

        //Act
        String returnedPage = shoppingCartProductsController.checkOut(modelMock, httpSessionMock);

        //Assert
        assertEquals("redirect:/login", returnedPage);
    }

    @Test
    public void checkOutTest_sendValidSessionAttribute_returnCheckOutPage() {
        //Arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(1);
        when(cartServicesMock.viewCart(anyInt()))
                .thenReturn(new Response<List<ShoppingCartProducts>>("Done", 200, false, false, new ArrayList<>()));
        when(cartServicesMock.calculateTotal(anyInt()))
                .thenReturn(new Response<>("Done", 200, false, false , 29.0));
        when(addressServiceMock.getUserAddresses(anyInt()))
                .thenReturn(new Response<List<Address>>("Done", 200, false, false, new ArrayList<>()));
        //Act
        String returnedPage = shoppingCartProductsController.checkOut(modelMock, httpSessionMock);

        //Assert
        assertEquals("checkout", returnedPage);
    }

}
