package service;

import org.example.model.Response;
import org.example.service.ValidationService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class ValidationServiceTest {
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
