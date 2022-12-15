package org.example.controller;

import org.example.entity.Product;
import org.example.entity.ShoppingCartProducts;
import org.example.entity.User;
import org.example.model.Response;
import org.example.model.Star;
import org.example.model.UserInputReview;
import org.example.service.product.ProductService;
import org.example.service.rate.RateService;
import org.example.service.shoppingcartproducts.ShoppingCartProductsService;
import org.example.service.user.UserService;
import org.example.utility.RateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductController {

    ProductService productService;
    RateService rateService;
    UserService userService;
    ShoppingCartProductsService cartService;

    @Autowired
    public ProductController(ProductService productService, RateService rateService, UserService userService, ShoppingCartProductsService cartService) {
        this.productService = productService;
        this.rateService = rateService;
        this.userService = userService;
        this.cartService = cartService;
    }
    @GetMapping("/productDetails")
    public ModelAndView getProductDetails( @RequestParam int productId){
        RateUtils rateUtils=new RateUtils();
        ModelAndView modelAndView=new ModelAndView("productDetails");
        Response<Product> productResponse=productService.getProductsById(productId);
        if(productResponse.isErrorOccurred()){
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage",productResponse.getMessage());
            modelAndView.addObject("statusCode",productResponse.getStatusCode());
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
        modelAndView.addObject("newCartItem", new ShoppingCartProducts());
        return modelAndView;
    }

    @PostMapping("/productDetails")
    public String addToCart(@Valid @ModelAttribute("newCartItem") ShoppingCartProducts cartProducts, BindingResult bindingResult,
                            @RequestParam int productId, HttpSession session) {
        if(  session.getAttribute("user-Id")==null){
            return "redirect:/login";
        }
        if(bindingResult.hasErrors()) {
            Map<String, Object> modelMap = bindingResult.getModel();
            return "productDetails";
        }
        Response<Product> product = productService.getProductsById(productId);
        if(product.getObjectToBeReturned().getAvailableQuantity() > 0) {
            int userId =(int) session.getAttribute("user-Id");
            Response<User> user = userService.getUserById(userId);
            cartProducts.setProductQuantity(1);
            cartProducts.setProduct(product.getObjectToBeReturned());
            cartProducts.setUser(user.getObjectToBeReturned());
            cartService.addToCart(cartProducts);
        }
        return "redirect:/products/getAllProducts";
    }

    @GetMapping("/getAllProducts")
    public String getAllProducts(ModelMap modelMap){
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
