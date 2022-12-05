package org.example.controller;
import org.example.entity.Product;
import org.example.model.UserInputReview;
import org.example.service.product.ProductService;
import org.example.service.rate.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@SessionAttributes("userId")
@Controller
public class RateController {
    @Autowired
    private RateService rateService;
    @Autowired
    ProductService productService;
    @PostMapping("/rate")
    public ModelAndView uploadRate(@RequestParam int productId, @RequestParam int rate, @RequestParam String message, @RequestParam String email, Model model){
       ModelAndView modelAndView=new ModelAndView("redirect:/products/productDetails?productId="+productId);
        System.out.println("productId = " + productId + ", rate = " + rate + ", message = " + message + ", email = " + email);
        UserInputReview userInputReview=new UserInputReview(rate, (Integer) model.getAttribute("userId"),productId,message,email);
        rateService.AssignRateToProduct(userInputReview);
       // Product product=productService.getProductsById(productId);
       // modelAndView.addObject("product",product);
       return modelAndView ;
    }
}
