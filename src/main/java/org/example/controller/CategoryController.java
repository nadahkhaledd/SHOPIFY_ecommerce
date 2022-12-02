package org.example.controller;

import org.example.entity.Category;
import org.example.entity.Product;
import org.example.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String getAllCategories(Model model){
        List<Category> categories=categoryService.getAllCategories();
        model.addAttribute("categories",categories);
        return "viewAllCategories";

    }
}
