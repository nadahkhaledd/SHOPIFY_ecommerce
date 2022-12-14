import org.example.entity.Address;
import org.example.entity.Customer;
import org.example.enums.CustomerStatus;
import org.example.enums.Gender;
import org.example.model.Response;
import org.example.repository.address.AddressRepository;
import org.example.service.address.AddressService;
import org.example.service.address.AddressServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class AddressServiceTest {
    private final AddressService addressService;
    private final AddressRepository addressRepositoryMock;

    public AddressServiceTest() {
        addressRepositoryMock = Mockito.mock(AddressRepository.class);
        addressService = new AddressServiceImpl(addressRepositoryMock);
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addAddressTest_sendAddress_thenSaveToDB() {
        //Arrange
        Address address = new Address("Street name", 1, "City");
        Response response = new Response("Done", 200, false, false , address);
        when(addressRepositoryMock.addAddress(any(Address.class)))
                .thenReturn(response);

        //Act
        Response returnedResponse = addressService.addAddress(address);

        //Assert
        assertNotEquals(null, returnedResponse);
        assertEquals(address, returnedResponse.getObjectToBeReturned());
    }

    @Test(expected = NullPointerException.class)
    public void addAddressTest_sendNullAddress_returnNullPointerException() {
        //Act
        addressService.addAddress(null);
    }

    @Test
    public void getUserAddressesTest_sendUserId_returnUserAddresses() {
        //Arrange
        Customer customer = new Customer(1,"Youssef", "Medhat", "ym.desouky@gmail.com", "12345", Gender.male,
                new Date(), CustomerStatus.ACTIVATED, 0);
        Address address = new Address("Street name", 1, "City", customer);
        customer.getAddresses().add(address);
        Response response = new Response("Done", 200, false, false, customer.getAddresses());
        when(addressRepositoryMock.getUserAddresses(anyInt()))
                .thenReturn(response);

        //Act
        Response returnedResponse = addressService.getUserAddresses(1);

        //Assert
        assertNotNull(returnedResponse);
        assertEquals(customer.getAddresses(), returnedResponse.getObjectToBeReturned());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getUserAddressesTest_sendInvalidUserId_returnIllegalArgumentException() {
        //Act
        addressService.getUserAddresses(0);
    }

    @Test
    public void getAddressByIdTest_sendAddressId_returnAddress() {
        //Arrange
        Customer customer = new Customer(1,"Youssef", "Medhat", "ym.desouky@gmail.com", "12345", Gender.male,
                new Date(), CustomerStatus.ACTIVATED, 0);
        Address address = new Address("Street name", 1, "City", customer);
        when(addressRepositoryMock.getAddressById(anyInt()))
                .thenReturn(new Response<>("Done", 200, false, false, address));

        //Act
        Response returnedResponse = addressService.getAddressById(1);

        //Assert
        assertNotNull(returnedResponse);
        assertEquals(address, returnedResponse.getObjectToBeReturned());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getAddressByIdTest_sendInvalidAddressId_returnIllegalArgumentException() {
        //Act
        addressService.getAddressById(0);
    }

    @Test
    public void updateAddressTest_sendAddress_thenSaveToDB() {
        //Arrange
        Customer customer = new Customer(1,"Youssef", "Medhat", "ym.desouky@gmail.com", "12345", Gender.male,
                new Date(), CustomerStatus.ACTIVATED, 0);
        Address address = new Address("Street name", 1, "City", customer);
        address.setStreet("New Street Name");
        when(addressRepositoryMock.updateAddress(any(Address.class)))
                .thenReturn(new Response<>("Done", 200, false, false, address));

        //Act
        Response returnedResponse = addressService.updateAddress(address);

        //Assert
        assertNotNull(returnedResponse);
        assertEquals(address, returnedResponse.getObjectToBeReturned());
    }

    @Test(expected = NullPointerException.class)
    public void updateAddressTest_sendNullAddress_returnNullPointerException() {
        //Act
        addressService.updateAddress(null);
    }

    @Test
    public void deleteAddressTest_sendAddressId_thenRemoveFromDB() {
        //Arrange
        Response response = new Response("Done", 200, false, false, 1);
        when(addressRepositoryMock.deleteAddress(anyInt()))
                .thenReturn(response);

        //Act
        Response returnedResponse = addressService.deleteAddress(1);

        //Assert
        assertNotNull(returnedResponse);
        assertEquals(true, returnedResponse.getObjectToBeReturned());

    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteAddressTest_sendInvalidAddressId_returnIllegalArgumentException() {
        //Act
        addressService.deleteAddress(0);
    }
}
