package org.example.controller;

import org.example.entity.Product;
import org.example.service.product.ProductService;
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
    @GetMapping("/productDetails")
    public ModelAndView getProductDetails( @RequestParam int productId){
        ModelAndView modelAndView=new ModelAndView("productDetails");
        Product product=productService.getProductsById(productId);
        modelAndView.addObject("product",product);
        return modelAndView;
    }

    @GetMapping("/shop")
    public String test(){
        return "detail";
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
