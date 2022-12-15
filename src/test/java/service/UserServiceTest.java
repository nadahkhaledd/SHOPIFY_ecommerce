package service;
import org.example.entity.Admin;
import org.example.entity.User;
import org.example.model.Response;
import org.example.repository.admin.AdminRepository;
import org.example.repository.user.UserRepository;
import org.example.service.ValidationService;
import org.example.service.admin.AdminService;
import org.example.service.admin.AdminServiceImplementation;
import org.example.service.security.AuthService;
import org.example.service.user.UserService;
import org.example.service.user.UserServiceImpl;
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
public class UserServiceTest {

    private UserService userService;
    private UserRepository userRepositoryMock;
    private Response responseMock;

    public UserServiceTest() {
        userRepositoryMock = Mockito.mock(UserRepository.class);
        responseMock= Mockito.mock(Response.class);
        userService = new UserServiceImpl(userRepositoryMock);

    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByID_sendNegativeID_expectIllegalArgumentException(){
        //ACT
        userService.getUserById(-1);
    }

    @Test
    public void testGetUserByID_givenNotFoundUserID_expectNoContentResponse(){
        //Arrange
        when(userRepositoryMock.getUserById(anyInt())).thenReturn(new Response<User>("Done", 200, false, null));
        when(responseMock.getObjectToBeReturned()).thenReturn(null);
        Response expected = new Response<>("no content", 204, false, true, null);

        //ACT
        Response actual = userService.getUserById(25);

        //Assert
        assertEquals(expected.getMessage(), actual.getMessage());
    }

    @Test
    public void testGetUserByID_givenValidID_expectReturnUser(){
        User user = new User("nadah@gmail.com");
        user.setId(1);
        Response expected = new Response<User>("Done", 200, false, user);
        when(userRepositoryMock.getUserById(anyInt())).thenReturn(expected);
        when(responseMock.getObjectToBeReturned()).thenReturn(user);

        //ACT
        Response<User> actual = userService.getUserById(1);

        //Assert
        assertEquals(expected.getMessage(), actual.getMessage());
        assertNotNull(actual.getObjectToBeReturned());

    }


    @Test(expected = NullPointerException.class)
    public void testGetUserByEmail_sendNullEmail_expectNullPointerException(){
        //ACT
        userService.getUserByEmail(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByEmail_sendEmptyString_expectIllegalArgumentException(){
        //ACT
        userService.getUserByEmail("   ");
    }

    @Test
    public void testGetUserByEmail_givenNotFoundUserEmail_expectNoContentResponse(){
        //Arrange
        when(userRepositoryMock.getUserByEmail(anyString())).thenReturn(new Response<User>("Done", 200, false, null));
        when(responseMock.getObjectToBeReturned()).thenReturn(null);
        Response expected = new Response<>("no content", 204, false, true, null);

        //ACT
        Response actual = userService.getUserByEmail("hagar@gmail.com");

        //Assert
        assertEquals(expected.getMessage(), actual.getMessage());
    }

    @Test
    public void testGetUserByEmail_givenValidEMail_expectReturnUser(){
        User user = new User("nadah@gmail.com");
        Response expected = new Response<User>("Done", 200, false, user);
        when(userRepositoryMock.getUserByEmail(anyString())).thenReturn(expected);
        when(responseMock.getObjectToBeReturned()).thenReturn(user);

        //ACT
        Response<User> actual = userService.getUserByEmail(user.getEmail());

        //Assert
        assertEquals(expected.getMessage(), actual.getMessage());
        assertNotNull(actual.getObjectToBeReturned());

    }


}
