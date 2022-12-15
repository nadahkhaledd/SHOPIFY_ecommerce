package controller;

import org.example.controller.AddressController;
import org.example.entity.Address;
import org.example.entity.Customer;
import org.example.entity.User;
import org.example.model.Response;
import org.example.service.address.AddressService;
import org.example.service.user.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class AddressControllerTest {
    private final AddressController addressController;
    private final AddressService addressServiceMock;
    private final UserService userServiceMock;
    private final Model modelMock;
    private final HttpSession httpSessionMock;
    private final BindingResult bindingResultMock;

    public AddressControllerTest() {
        addressServiceMock = Mockito.mock(AddressService.class);
        userServiceMock = Mockito.mock(UserService.class);
        modelMock = Mockito.mock(Model.class);
        httpSessionMock = Mockito.mock(HttpSession.class);
        bindingResultMock = Mockito.mock(BindingResult.class);
        addressController = new AddressController(addressServiceMock, userServiceMock);
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void newAddressTest_returnAddAddressPage() {
        //Act
        String returnedPage = addressController.newAddress(modelMock);

        //Assert
        assertEquals("addAddress", returnedPage);
    }

    @Test
    public void addAddressTest_sendInvalidAddress_returnAddAddressPage() {
        //Arrange
        when(bindingResultMock.hasErrors()).thenReturn(true);

        //Act
        String returnedPage = addressController.addAddress(new Address(), bindingResultMock, modelMock, httpSessionMock);

        //Assert
        assertEquals("addAddress", returnedPage);
    }

    @Test
    public void addAddressTest_sendAddress_internalErrorOccurred_returnErrorPage() {
        //Arrange
        User user = new Customer();
        Address address = new Address();
        when(httpSessionMock.getAttribute(anyString())).thenReturn(1);
        when(userServiceMock.getUserById(anyInt()))
                .thenReturn(new Response<>("Done", 200, false, false, user));
        when(addressServiceMock.addAddress(address))
                .thenReturn(new Response("Done", 200, true, false, address));

        //Act
        String returnedPage = addressController.addAddress(address, bindingResultMock, modelMock, httpSessionMock);

        //Assert
        assertEquals("error", returnedPage);
    }

    @Test
    public void addAddressTest_sendAddress_fieldErrorOccurred_returnAddAddressPage() {
        //Arrange
        User user = new Customer();
        Address address = new Address();
        when(httpSessionMock.getAttribute(anyString())).thenReturn(1);
        when(userServiceMock.getUserById(anyInt()))
                .thenReturn(new Response<>("Done", 200, false, false, user));
        when(addressServiceMock.addAddress(address))
                .thenReturn(new Response("Done", 200, true, true, address));

        //Act
        String returnedPage = addressController.addAddress(address, bindingResultMock, modelMock, httpSessionMock);

        //Assert
        assertEquals("addAddress", returnedPage);
    }

    @Test
    public void addAddressTest_sendAddress_returnViewAddressesPage() {
        //Arrange
        User user = new Customer();
        Address address = new Address();
        when(httpSessionMock.getAttribute(anyString())).thenReturn(1);
        when(userServiceMock.getUserById(anyInt()))
                .thenReturn(new Response<>("Done", 200, false, false, user));
        when(addressServiceMock.addAddress(address))
                .thenReturn(new Response("Done", 200, false, false, address));

        //Act
        String returnedPage = addressController.addAddress(address, bindingResultMock, modelMock, httpSessionMock);

        //Assert
        assertEquals("redirect:/address/view", returnedPage);
    }

    @Test
    public void deleteAddressTest_sendAddressId_returnViewAddressesPage() {
        //Arrange
        Response response = new Response("Done", 200, false, false, true);
        when(addressServiceMock.deleteAddress(anyInt())).thenReturn(response);

        //Act
        String returnedPage = addressController.deleteAddress(1, modelMock);

        //Assert
        assertEquals("redirect:/address/view", returnedPage);
    }

    @Test
    public void deleteAddressTest_sendAddressId_ExceptedOccurred_returnErrorPage() {
        //Arrange
        Response response = new Response("Done", 200, true, false, false);
        when(addressServiceMock.deleteAddress(anyInt())).thenReturn(response);

        //Act
        String returnedPage = addressController.deleteAddress(1, modelMock);

        //Assert
        assertEquals("error", returnedPage);
    }

    @Test
    public void newAddressTest_sendAddressId_returnUpdateAddressPage() {
        //Arrange
        Response response = new Response("Done", 200, false, false, new Address());
        when(addressServiceMock.getAddressById(anyInt())).thenReturn(response);

        //Act
        String returnedPage = addressController.newAddress(1, modelMock);

        //Assert
        assertEquals("updateAddress", returnedPage);
        verify(modelMock, times(1)).addAttribute(anyString(), any());
    }

    @Test
    public void updateAddressTest_sendAddress_validationErrorOccurred_returnUpdateAddressPage() {
        //Arrange
        when(bindingResultMock.hasErrors()).thenReturn(true);
        Address address = new Address();

        //Act
        String returnedPage = addressController.updateAddress(address, bindingResultMock, modelMock, httpSessionMock);

        //Assert
        assertEquals("updateAddress", returnedPage);
    }

    @Test
    public void updateAddressTest_sendAddress_fieldErrorOccurred_returnUpdateAddressPage() {
        //Arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(1);
        when(userServiceMock.getUserById(anyInt())).thenReturn(new Response<>("Done", 200, false, false, new Customer()));
        Response response = new Response("error", 400, true, true, false);
        when(addressServiceMock.updateAddress(any(Address.class))).thenReturn(response);
        Address address = new Address();

        //Act
        String returnedPage = addressController.updateAddress(address, bindingResultMock, modelMock, httpSessionMock);

        //Assert
        assertEquals("updateAddress", returnedPage);
    }

    @Test
    public void updateAddressTest_sendAddress_internalErrorOccurred_returnErrorPage() {
        //Arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(1);
        when(userServiceMock.getUserById(anyInt())).thenReturn(new Response<>("Done", 200, false, false, new Customer()));
        Response response = new Response("error", 400, true, false, false);
        when(addressServiceMock.updateAddress(any(Address.class))).thenReturn(response);
        Address address = new Address();

        //Act
        String returnedPage = addressController.updateAddress(address, bindingResultMock, modelMock, httpSessionMock);

        //Assert
        assertEquals("error", returnedPage);
    }

    @Test
    public void updateAddressTest_sendAddress_returnViewAddressesPage() {
        //Arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(1);
        when(userServiceMock.getUserById(anyInt())).thenReturn(new Response<>("Done", 200, false, false, new Customer()));
        Response response = new Response("Done", 200, false, false, true);
        when(addressServiceMock.updateAddress(any(Address.class))).thenReturn(response);
        Address address = new Address();

        //Act
        String returnedPage = addressController.updateAddress(address, bindingResultMock, modelMock, httpSessionMock);

        //Assert
        assertEquals("redirect:/address/view", returnedPage);
    }

    @Test
    public void getAddressesTest_internalErrorOccurred_returnErrorPage() {
        //Arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(1);
        Response response = new Response("Done", 200, true, false, new ArrayList<>());
        when(addressServiceMock.getUserAddresses(anyInt())).thenReturn(response);

        //Act
        String returnedPage = addressController.getAddresses(modelMock, httpSessionMock);

        //Assert
        assertEquals("error", returnedPage);
    }

    @Test
    public void getAddressesTest_returnViewAddressesPage() {
        //Arrange
        when(httpSessionMock.getAttribute(anyString())).thenReturn(1);
        Response response = new Response("Done", 200, false, false, new ArrayList<>());
        when(addressServiceMock.getUserAddresses(anyInt())).thenReturn(response);

        //Act
        String returnedPage = addressController.getAddresses(modelMock, httpSessionMock);

        //Assert
        assertEquals("viewAddresses", returnedPage);
    }

}
