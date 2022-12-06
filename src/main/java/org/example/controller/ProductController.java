package org.example.controller;

import org.example.entity.Product;
import org.example.entity.ShoppingCartProducts;
import org.example.entity.User;
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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    RateService rateService;
    @Autowired
    UserService userService;
    @Autowired
    ShoppingCartProductsService cartServices;
    @GetMapping("/productDetails")
    public ModelAndView getProductDetails( @RequestParam int productId,ModelMap modelMap){
        RateUtils rateUtils=new RateUtils();
        ModelAndView modelAndView=new ModelAndView("productDetails");
        Product product=productService.getProductsById(productId);
        System.out.println(product.toString());
        rateService.calculateProductRate(product);
        modelAndView.addObject("product",product);
 //       modelAndView.addObject("rate",new UserInputReview());
        Star star=rateUtils.computeNumberOfStars(product.getRate());
        System.out.println(star.toString());
        modelAndView.addObject("stars",star);
        modelAndView.addObject("cartItem", new ShoppingCartProducts());
        return modelAndView;
    }
    @PostMapping("/productDetails")
    public String addToCart(@Valid @ModelAttribute("cartItem") ShoppingCartProducts cartProducts, BindingResult bindingResult,
                            @RequestParam int productId) {
        if(bindingResult.hasErrors()) {
            Map<String, Object> model = bindingResult.getModel();
            return "productDetails";
        }
        Product product = productService.getProductsById(productId);
        User user = userService.getUserById(1);
        cartProducts.setProduct(product);
        cartProducts.setUser(user);
        cartServices.addToCart(cartProducts);
        return "redirect:/products/getAllProducts";
    }

    @GetMapping("/getAllProducts")
    public String getAllProducts(Model model){
        System.out.println("innnnnn productsss");
        List<Product> products=productService.getProducts();
        products.forEach(System.out::println);
        model.addAttribute("products",products);
        return "viewProducts";

    }
    @GetMapping("/getCategoryProducts")
    public String getCategoryProducts(Model model, @RequestParam int categoryId){
        List<Product> products=productService.getProductsByCategory(categoryId);
        model.addAttribute("products",products);
        return "viewProducts";

    }

}
