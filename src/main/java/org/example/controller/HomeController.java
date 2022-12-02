package org.example.controller;

import org.example.entity.Category;
import org.example.entity.Product;
import org.example.service.category.CategoryService;
import org.example.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;


    @GetMapping("/home")
    public ModelAndView getAllItems() {
        List<Category> categories = categoryService.getAllCategories();
        categories.forEach(System.out::println);
        List<Product> products = productService.getProducts();
        products.forEach(System.out::println);
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("products", products);
        //  model.addAttribute("categories",categories);
        return modelAndView;
    }

}
