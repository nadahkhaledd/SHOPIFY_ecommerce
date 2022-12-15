package org.example.controller;

import org.example.entity.Category;
import org.example.entity.Product;
import org.example.model.Response;
import org.example.service.category.CategoryService;
import org.example.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
@Controller

public class HomeController {

    CategoryService categoryService;
    ProductService productService;
    @Autowired
    public HomeController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/home")
    public ModelAndView getAllItems(HttpSession session,Model model) {
        ModelAndView modelAndView = new ModelAndView("home");
        model.addAttribute("userId",session.getAttribute("user-Id"));
        Response<List<Category>> categoriesResponse = categoryService.getAllCategories();
        //categories.forEach(System.out::println);
        Response<List<Product>> productsResponse = productService.getProducts();
        if (productsResponse.isErrorOccurred()||categoriesResponse.isErrorOccurred()){
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage",productsResponse.isErrorOccurred()?productsResponse.getMessage():categoriesResponse.getMessage());
            modelAndView.addObject("statusCode",productsResponse.isErrorOccurred()?productsResponse.getStatusCode():categoriesResponse.getStatusCode());
            return modelAndView;
        }

        modelAndView.addObject("categories", categoriesResponse.getObjectToBeReturned());
        modelAndView.addObject("products", productsResponse.getObjectToBeReturned());
        return modelAndView;
    }

}
