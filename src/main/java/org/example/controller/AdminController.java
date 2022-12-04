package org.example.controller;

import org.example.entity.Admin;
import org.example.entity.Category;
import org.example.entity.Product;
import org.example.enums.Gender;
import org.example.model.RemoveUserFields;
import org.example.service.admin.AdminService;
import org.example.service.category.CategoryService;
import org.example.service.product.ProductService;
import org.example.typeEditor.CategoryTypeEditor;
import org.example.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;

import java.text.SimpleDateFormat;
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

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }

    @InitBinder
    public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
    {
        binder.registerCustomEditor(Category.class,
                new CategoryTypeEditor(categoryService));

    }

    @GetMapping("adminHome")
    public String adminHome(Model model) {
        //model.addAttribute("admin", new Admin());
        model.addAttribute("name", "Admin");
        return "adminHome";
    }

    @GetMapping("showCategories")
    public String showCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "showCategories";
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
        return "redirect:/admin/adminHome";
    }

    @GetMapping("addCategory")
    public String newCategory(Model model) {

        model.addAttribute("category", new Category());
        return "addCategory";
    }

    @GetMapping("deleteCategory/{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryService.removeCategory(id);
        return "redirect:/admin/showCategories";
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
        return "redirect:/admin/adminHome";
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
        return "redirect:/admin/adminHome";
    }

    @GetMapping("removeUser")
    public String removeUser(Model model) {
        List<String> userTypes = new ArrayList<>(
                Arrays.asList("admin", "customer"));
        model.addAttribute("fields", new RemoveUserFields());
        model.addAttribute("userTypes", userTypes);
        return "removeUser";
    }

    @PostMapping("removeUser")
    public String deleteUser(@Valid @ModelAttribute("fields") RemoveUserFields fields, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, Object> model = bindingResult.getModel();
            System.out.println(model);
            return "removeUser";
        }
        if(fields.getUserType().equals("admin")){
            adminService.removeAdmin(fields.getUserID(), fields.getUserEmail());
        }
        else
            adminService.deactivateCustomer(fields.getUserID(), fields.getUserEmail());

        return "redirect:/admin/adminHome";
    }



    @GetMapping("login")
    public String loginAdmin() {
        return "login";
    }

}
