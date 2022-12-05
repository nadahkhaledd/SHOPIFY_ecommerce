package org.example.controller;

import org.example.entity.Product;
import org.example.model.Response;
import org.example.model.Star;
import org.example.model.UserInputReview;
import org.example.service.product.ProductService;
import org.example.service.rate.RateService;
import org.example.utility.RateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    RateService rateService;
    @GetMapping("/productDetails")
    public ModelAndView getProductDetails( @RequestParam int productId,ModelMap modelMap){
        RateUtils rateUtils=new RateUtils();
        ModelAndView modelAndView=new ModelAndView("productDetails");
        Response<Product> productResponse=productService.getProductsById(productId);
        if(productResponse.isErrorOccurred()){
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage",productResponse.getMessage());
            modelMap.put("statusCode",productResponse.getStatusCode());
            return modelAndView;
        }
        Response rateResponse= rateService.setProductRate(productResponse.getObjectToBeReturned());
        if(rateResponse.isErrorOccurred()){
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage",rateResponse.getMessage());
            modelAndView.addObject("statusCode",rateResponse.getStatusCode());
            return modelAndView;
        }
        modelAndView.addObject("product",productResponse.getObjectToBeReturned());
        Star star=rateUtils.computeNumberOfStars(productResponse.getObjectToBeReturned().getRate());
        System.out.println(star.toString());
        modelAndView.addObject("stars",star);
        return modelAndView;
    }

    @GetMapping("/getAllProducts")
    public String getAllProducts(ModelMap modelMap){
        System.out.println("innnnnn productss controller");
        Response<List<Product>> productsResponse=productService.getProducts();
        if(productsResponse.isErrorOccurred()){
            modelMap.put("errorMessage",productsResponse.getMessage());
            modelMap.put("statusCode",productsResponse.getStatusCode());
            return "error";
        }
        modelMap.addAttribute("products",productsResponse.getObjectToBeReturned());

        return "viewProducts";

    }
    @GetMapping("/getCategoryProducts")
    public String getCategoryProducts(ModelMap modelMap, @RequestParam int categoryId){
        Response<List<Product>> productsResponse=productService.getProductsByCategory(categoryId);
        if(productsResponse.isErrorOccurred()){
            modelMap.put("errorMessage",productsResponse.getMessage());
            modelMap.put("statusCode",productsResponse.getStatusCode());
            return "error";
        }
        modelMap.addAttribute("products",productsResponse.getObjectToBeReturned());
        return "viewProducts";

    }

}
