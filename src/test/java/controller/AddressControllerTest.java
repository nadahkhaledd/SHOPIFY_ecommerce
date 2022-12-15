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

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

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

//    @Test
//    public void addAddressTest_sendAddress_InternalErrorOccurred_returnErrorPage() {
//        //Arrange
//        User user = new User();
//        Address address = new Address();
//        when(httpSessionMock.getAttribute(anyString())).thenReturn(1);
//        when(userServiceMock.getUserById(anyInt()))
//                .thenReturn(new Response<>("Done", 200, false, false, user));
//        when(addressServiceMock.addAddress(address))
//                .thenReturn(new Response("Done", 200, true, false, address));
//
//        //Act
//        String returnedPage = addressController.addAddress(address, bindingResultMock, modelMock, httpSessionMock);
//
//        //Assert
//        assertEquals("error", returnedPage);
//    }
}
