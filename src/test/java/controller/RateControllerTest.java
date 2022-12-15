package controller;

import org.example.controller.RateController;
import org.example.entity.Product;
import org.example.entity.ShoppingCartProducts;
import org.example.model.Response;
import org.example.service.rate.RateService;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class RateControllerTest {
    private RateService rateServiceMock;
    private RateController rateController;
    private HttpSession httpSessionMock;

    public RateControllerTest() {
        rateServiceMock = Mockito.mock(RateService.class);
        rateController =new RateController(rateServiceMock);
        httpSessionMock = Mockito.mock(HttpSession.class);

    }

    @Test
    public void uploadRate_SendNullUserId_expectedLoginPage(){
        //arrange
        when(httpSessionMock.getAttribute("user-Id")).thenReturn(null);
        //act
        String result=rateController.uploadRate(1,5,"good",httpSessionMock).getViewName();
        //assert
        assertEquals(result,"redirect:/login");
    }
    @Test
    public void uploadRate_SendValidRateResponse_expectedRedirectToProductDetailsPage(){
        //arrange
        when(httpSessionMock.getAttribute("user-Id")).thenReturn(1);
        Response rateResponse=new Response("Ok",200,false);

        when(rateServiceMock.AssignRateToProduct(any())).thenReturn(rateResponse);
        //act
        String result=rateController.uploadRate(1,5,"good",httpSessionMock).getViewName();
        //assert
        assertEquals(result,"redirect:/products/productDetails?productId=1");
    }

    @Test
    public void uploadRate_SendInvalidRateResponse_expectedErrorPage(){
        //arrange
        when(httpSessionMock.getAttribute("user-Id")).thenReturn(1);
        Response rateResponse=new Response("error",500,true);

        when(rateServiceMock.AssignRateToProduct(any())).thenReturn(rateResponse);
        //act
        String result=rateController.uploadRate(1,5,"good",httpSessionMock).getViewName();
        //assert
        assertEquals(result,"error");
    }
}
