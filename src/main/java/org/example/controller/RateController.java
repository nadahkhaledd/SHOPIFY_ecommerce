package org.example.controller;
import org.example.entity.Product;
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
    public ModelAndView uploadRate(@RequestParam int productId, @RequestParam int rate, @RequestParam String message, @RequestParam String email){
       ModelAndView modelAndView=new ModelAndView("productDetails");
        System.out.println("productId = " + productId + ", rate = " + rate + ", message = " + message + ", email = " + email);
        UserInputReview userInputReview=new UserInputReview(rate,1,productId,message,email);
        rateService.AssignRateToProduct(userInputReview);
        Product product=productService.getProductsById(productId);
        modelAndView.addObject("product",product);
       return modelAndView ;
    }
}
