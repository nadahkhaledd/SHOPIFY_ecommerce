package org.example.controller;
import org.example.entity.Product;
import org.example.model.Response;
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

import javax.servlet.http.HttpSession;


@Controller
public class RateController {
    @Autowired
    private RateService rateService;

    @PostMapping("/rate")
    public ModelAndView uploadRate(@RequestParam int productId, @RequestParam int rate, @RequestParam String message, HttpSession session, Model model){
       ModelAndView modelAndView=new ModelAndView("redirect:/products/productDetails?productId="+productId);
       if(  session.getAttribute("user-Id")==null){
           modelAndView.setViewName("redirect:/login");
           return modelAndView;
       }
       int userId=   (int) session.getAttribute("user-Id");
        System.out.println(userId+" productId = " + productId + ", rate = " + rate + ", message = " + message);
        UserInputReview userInputReview=new UserInputReview(rate,userId,productId,message);
        System.out.println("productId = " + productId + ", rate = " + rate + ", message = " + message );
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
