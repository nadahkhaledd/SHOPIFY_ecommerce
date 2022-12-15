import org.example.entity.User;
import org.example.model.Response;
import org.example.repository.order.OrderRepositoryImpl;
import org.example.repository.security.AuthRepo;
import org.example.repository.user.UserRepository;
import org.example.service.ValidationService;
import org.example.service.security.AuthService;
import org.example.service.security.EncryptionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AuthServiceTest {

    private AuthService authService;
    private AuthRepo authRepositoryMock;
    private UserRepository userRepositoryMock;
    private EncryptionService encryptionService;
    @Before
    public void setup() {
        authRepositoryMock = Mockito.mock(AuthRepo.class);
        userRepositoryMock = Mockito.mock(UserRepository.class);
        authService = new AuthService(authRepositoryMock);
        encryptionService = new EncryptionService();
    }

    @Test
    public void loginTest_return200IncaseCorrectCredentials(){
        User user =new User();
        Response response = new Response("OK", 200, false, false, user);
        when(authRepositoryMock.checkLoginCredential(any(String.class),any(String.class))).thenReturn(response);
        Response result = authService.login("o@o.o","1234");
        assertEquals(200, result.getStatusCode());
    }

    @Test
    public void loginTest_return400IncaseInorrectCredentials(){
        User user =new User();
        Response response = new Response<User>("email or password is wrong", 400, true, true, null);
        when(authRepositoryMock.checkLoginCredential(any(String.class),any(String.class))).thenReturn(response);
        Response result = authService.login("o@o.o","1234");
        assertEquals(400, result.getStatusCode());
    }

    @Test
    public void registerTest_return200IncaseValidCredentials(){
        User user =new User();
        user.setPassword("Omar");
        Response response = new Response<Boolean>("OK", 200, false, false, true);
        when(authRepositoryMock.register(any(User.class))).thenReturn(response);

        Response result = authService.register(user);
        assertEquals(200, result.getStatusCode());
    }

    @Test
    public void verifyEmailTest_returned200InCaseValidEmail(){

        Response response = new Response<Boolean>("Ok", 200, false, false, true);
        when(authRepositoryMock.verifyEmail(any(String.class))).thenReturn(response);
        Response result = authService.verifyEmail("user@vodafone.com");
        assertEquals(200, result.getStatusCode());
    }

    @Test
    public void verifyEmailTest_returned500InCaseValidEmail(){

        Response response = new Response<Boolean>("failed", 500, false, false, false);
        when(authRepositoryMock.verifyEmail(any(String.class))).thenReturn(response);
        Response result = authService.verifyEmail("user@vodafone.com");
        assertEquals(500, result.getStatusCode());
    }

    @Test
    public void checkIfActivatedTest_return200IfUserActivated(){
        Response response = new Response<>("Ok", 200, false, false, true);
        when(authRepositoryMock.checkIfActivated(any(Integer.class))).thenReturn(response);
        Response result = authService.checkIfActivated(1);
        assertEquals(200, result.getStatusCode());
    }

    @Test
    public void resetPasswordTest_return200whenPasswordChanged(){
        Response response= new Response("ok",200,false,false);
        when(authRepositoryMock.resetPassword(any(String.class),any(String.class))).thenReturn(response);
        Response result = authService.resetPassword("omar@vodafone.com", "1234");
        assertEquals(200, result.getStatusCode());
    }

    @Test
    public void checkIfUserAlreadyExistsTest_return200ifNotExists(){
        Response response = new Response<>("Ok", 200, false, false, false);
        when(authRepositoryMock.checkIfUserAlreadyExists(any(String.class))).thenReturn(response);
        Response result = authService.checkIfUserAlreadyExists("omar@vodafone.com");
        assertEquals(200, result.getStatusCode());
    }

    @Test
    public void checkIfSuspendedTest_return200ifNotExists(){
        when(authRepositoryMock.checkIfSuspended(any(String.class))).thenReturn(true);
        boolean result = authService.checkIfSuspended("omar@vodafone.com");
        assertTrue(result);
    }
}
