package org.example.controller;

import org.example.entity.Category;
import org.example.entity.Product;
import org.example.model.Response;
import org.example.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/getAllCategories")
    public String getAllCategories(ModelMap modelMap){
        Response<List<Category>> categoriesResponse=categoryService.getAllCategories();
        if(categoriesResponse.isErrorOccurred()){
            modelMap.put("errorMessage",categoriesResponse.getMessage());
            modelMap.put("statusCode",categoriesResponse.getStatusCode());
            return "error";
        }
        //modelMap.addAttribute("products",productsResponse.getObjectToBeReturned());

        modelMap.addAttribute("categories",categoriesResponse.getObjectToBeReturned());
        return "viewAllCategories";

    }
}
