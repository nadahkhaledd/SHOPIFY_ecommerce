package org.example.controller;
import org.example.entity.Product;
import org.example.model.Response;
import org.example.model.UserInputReview;
import org.example.service.product.ProductService;
import org.example.service.rate.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RateController {
    @Autowired
    private RateService rateService;
    @Autowired
    ProductService productService;
    @PostMapping("/rate")
    public ModelAndView uploadRate(@RequestParam int productId, @RequestParam int rate, @RequestParam String message){
       ModelAndView modelAndView=new ModelAndView("redirect:/products/productDetails?productId="+productId);
        System.out.println("productId = " + productId + ", rate = " + rate + ", message = " + message );
        UserInputReview userInputReview=new UserInputReview(rate,1,productId,message);
        Response rateResponse= rateService.AssignRateToProduct(userInputReview);
        if(rateResponse.isErrorOccurred()){
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage",rateResponse.getMessage());
            modelAndView.addObject("statusCode",rateResponse.getStatusCode());
            return modelAndView;
        }
       return modelAndView ;
    }
}
