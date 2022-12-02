package org.example.controller;

import org.example.entity.Admin;
import org.example.entity.Category;
import org.example.entity.Product;
import org.example.enums.Gender;
import org.example.service.admin.AdminService;
import org.example.service.category.CategoryService;
import org.example.service.product.ProductService;
import org.example.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final CategoryService categoryService;
    private final ProductService productService;

    private DateUtils dateUtils = new DateUtils();

    @Autowired
    public AdminController(AdminService adminService, CategoryService categoryService, ProductService productService) {
        this.adminService = adminService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("addAdmin")
    public String newAdmin(Model model) {

        List<String> genders = new ArrayList<>(
                Arrays.asList(Gender.male.toString(), Gender.female.toString()));
        model.addAttribute("admin", new Admin());
        model.addAttribute("genders", genders);
        return "addAdmin";
    }

    @PostMapping("addAdmin")
    public String addUser(@Valid @DateTimeFormat(pattern = "yyyy-MM-dd")  @ModelAttribute("admin") Admin admin, BindingResult bindingResult) {
        System.out.println("hello to post admin");
        if (bindingResult.hasErrors()) {
            Map<String, Object> model = bindingResult.getModel();
            System.out.println(model);
            return "addAdmin";
        }
        System.out.println(admin);
        adminService.addAdmin(admin);
        return "redirect:/admin/login";
    }

    @GetMapping("addCategory")
    public String newCategory(Model model) {

        model.addAttribute("category", new Category());
        return "addCategory";
    }

    @PostMapping("addCategory")
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult) {
        System.out.println("hello to post category");
        if (bindingResult.hasErrors()) {
            Map<String, Object> model = bindingResult.getModel();
            System.out.println(model);
            return "addCategory";
        }
        System.out.println(category);
        categoryService.addCategory(category);
        return "redirect:/admin/login";
    }

    @GetMapping("addProduct")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getCategoriesNames());
        return "addProduct";
    }

    @PostMapping("addProduct")
    public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {
        System.out.println("hello to post product");
        if (bindingResult.hasErrors()) {
            Map<String, Object> model = bindingResult.getModel();
            System.out.println(model);
            return "addProduct";
        }
        System.out.println(product);
        productService.addProduct(product);
        return "redirect:/admin/login";
    }

    @GetMapping("login")
    public String home() {
        return "login";
    }

}
