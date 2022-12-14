import org.example.model.Response;
import org.example.repository.order.OrderRepositoryImpl;
import org.example.repository.product.ProductRepoImpl;
import org.example.repository.user.UserRepositoryImpl;
import org.example.service.ValidationService;
import org.example.service.order.OrderServiceImpl;
import org.example.service.product.ProductServiceImpl;
import org.example.service.user.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class ValidationTest {
    private ValidationService validationService;

    @Before
    public void setup() {
        validationService = new ValidationService();
       }
    @Test
    public void validateAdminEmailTest_return200WhenDomainIsCorrect(){
       Response result= validationService.validateAdminEmail("omar@shopify.com");
        assertEquals(200, result.getStatusCode());
    }
    @Test
    public void validateAdminEmailTest_return400WhenDomainIsIncorrect(){
        Response result= validationService.validateAdminEmail("omar@vodaafone.com");
        assertEquals(400, result.getStatusCode());
    }
}
