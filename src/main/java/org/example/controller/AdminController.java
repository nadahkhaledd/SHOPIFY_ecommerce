package org.example.controller;

import org.example.entity.Admin;
import org.example.entity.Category;
import org.example.entity.Product;
import org.example.entity.User;
import org.example.enums.Gender;
import org.example.model.RemoveUserFields;
import org.example.model.Response;
import org.example.repository.user.UserRepository;
import org.example.service.admin.AdminService;
import org.example.service.category.CategoryService;
import org.example.service.product.ProductService;
import org.example.service.user.UserService;
import org.example.typeEditor.CategoryTypeEditor;
import org.example.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
@SessionAttributes({"userId","error"})
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final CategoryService categoryService;
    private final ProductService productService;

    private DateUtils dateUtils = new DateUtils();

    @Autowired
    public AdminController(AdminService adminService, UserService userService, UserRepository userRepository, CategoryService categoryService, ProductService productService) {
        this.adminService = adminService;
        this.userService = userService;
        this.userRepository = userRepository;
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

    @GetMapping("home")
    public String adminHome(Model model) {

        Integer id = (Integer) model.getAttribute("userId");

        if(id==null)
            return "login";
        model.addAttribute("name", userRepository.getUsernameByID(id).getObjectToBeReturned());
        return "adminHome";
    }

    @GetMapping("admins")
    public String getAdmins(Model model) {
        Response<List<Admin>> admins = adminService.getAllAdmins();
        model.addAttribute("admins", admins.getObjectToBeReturned());
        return "showAdmins";
    }

    @GetMapping("showCategories")
    public String showCategories(Model model) {
        Response<List<Category>> categoriesResponse = categoryService.getAllCategories();
        model.addAttribute("categories", categoriesResponse.getObjectToBeReturned());
        return "showCategories";
    }

    @GetMapping("addAdmin")
    public String newAdmin(Model model) {

        List<String> genders = new ArrayList<>(
                Arrays.asList(Gender.male.toString(), Gender.female.toString()));
        model.addAttribute("admin", new Admin());
        model.addAttribute("date", dateUtils.dateYearsAgo(18));
        return "addAdmin";
    }

    @PostMapping("addAdmin")
    public String addUser(@Valid @DateTimeFormat(pattern = "yyyy-MM-dd")  @ModelAttribute("admin") Admin admin, BindingResult bindingResult ,
                          ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            Map<String, Object> model = bindingResult.getModel();
            return "addAdmin";
        }
        modelMap.put("emailErrorMessage","");//initialize as empty
        Response response= adminService.addAdmin(admin);
        //System.out.println("responseeeee "+response.toString());

        if(response.isErrorOccurred()){
            if(response.isFieldErrorOccurred()){
                modelMap.put("emailErrorMessage",response.getMessage());
                return "addAdmin";
            }
            modelMap.put("statusCode", response.getStatusCode());
            modelMap.put("errorMessage",response.getMessage());
            return "error";
        }

        return "redirect:/admin/admins";
    }

    @GetMapping("addCategory")
    public String newCategory(Model model) {
        model.addAttribute("category", new Category());
        return "addCategory";
    }

    @PostMapping("addCategory")
    public String addCategory(@Valid @ModelAttribute("category") Category category,
                              BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            Map<String, Object> model = bindingResult.getModel();
            return "addCategory";
        }
        modelMap.put("addCategoryErrorMessage","");//initialize as empty
        Response response = categoryService.addCategory(category);

        if(response.isErrorOccurred()){
            if(response.isFieldErrorOccurred()){
                modelMap.put("addCategoryErrorMessage", response.getMessage());
                return "addCategory";
            }
            modelMap.put("statusCode", response.getStatusCode());
            modelMap.put("errorMessage",response.getMessage());
            return "error";
        }

        return "redirect:/admin/showCategories";
    }

    @GetMapping("deleteCategory/{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryService.removeCategory(id);
        return "redirect:/admin/showCategories";
    }

    @GetMapping("deleteAdmin/{id}")
    public String deleteAdmin(@PathVariable int id) {
        adminService.removeAdmin(id);
        return "redirect:/admin/admins";
    }

    @GetMapping("updateAdmin/{id}")
    public String updateAdmin(Model model, @PathVariable int id) {
        Response<User> admin = userService.getUserById(id);
        model.addAttribute("admin", admin.getObjectToBeReturned());

        return "updateAdmin";
    }

    @PostMapping("updateAdmin/{id}")
    public String updateAdmin(@Valid @DateTimeFormat(pattern = "yyyy-MM-dd") @ModelAttribute("admin") User admin,
                                 BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            Map<String, Object> model = bindingResult.getModel();
            return "updateAdmin";
        }
        modelMap.put("updateAdminErrorMessage","");//initialize as empty
        Response response = adminService.updateAdmin(admin);

        if(response.isErrorOccurred()){
            if(response.isFieldErrorOccurred()){
                modelMap.put("updateAdminErrorMessage",response.getMessage());
                return "updateAdmin";
            }
            modelMap.put("statusCode", response.getStatusCode());
            modelMap.put("errorMessage",response.getMessage());
            return "error";
        }
        return "redirect:/admin/admins";
    }

    @GetMapping("updateCategory/{id}")
    public String updateCategory(Model model, @PathVariable int id) {
        Response<Category> categoryResponse = categoryService.getCategoryByID(id);
        model.addAttribute("category", categoryResponse.getObjectToBeReturned());

        return "updateCategory";
    }

    @PostMapping("updateCategory/{id}")
    public String updateCategory(@Valid @ModelAttribute("category") Category category,
                                 BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            Map<String, Object> model = bindingResult.getModel();
            return "updateCategory";
        }
        modelMap.put("updateCategoryErrorMessage","");//initialize as empty
        Response response = categoryService.updateCategory(category);

        if(response.isErrorOccurred()){
            if(response.isFieldErrorOccurred()){
                modelMap.put("updateCategoryErrorMessage",response.getMessage());
                return "updateCategory";
            }
            modelMap.put("statusCode", response.getStatusCode());
            modelMap.put("errorMessage",response.getMessage());
            return "error";
        }
        return "redirect:/admin/showCategories";
    }

    @GetMapping("addProduct")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getCategoriesNames().getObjectToBeReturned());
        return "addProduct";
    }

    @PostMapping("addProduct")
    public String addProduct(@Valid @ModelAttribute("product") Product product,
                             BindingResult bindingResult, ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.getCategoriesNames().getObjectToBeReturned());

        if (bindingResult.hasErrors()) {
            Map<String, Object> model = bindingResult.getModel();
            return "addProduct";
        }
        modelMap.put("addProductErrorMessage","");//initialize as empty
        Response response = productService.addProduct(product);

        if(response.isErrorOccurred()){
            if(response.isFieldErrorOccurred()){
                modelMap.put("addProductErrorMessage",response.getMessage());
                return "addProduct";
            }
            modelMap.put("statusCode", response.getStatusCode());
            modelMap.put("errorMessage",response.getMessage());
            return "error";
        }
        return "redirect:/admin/home";
    }

    @GetMapping("removeUser")
    public String removeUser(Model model) {
        model.addAttribute("fields", new RemoveUserFields());

        return "removeUser";
    }

    @PostMapping("removeUser")
    public String deleteUser(@Valid @ModelAttribute("fields") RemoveUserFields fields,
                             BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            Map<String, Object> model = bindingResult.getModel();

            return "removeUser";
        }
        modelMap.put("removeUserErrorMessage","");//initialize as empty
        Response response;
        if(fields.getUserType().equals("admin"))
            response = adminService.removeAdmin(fields.getUserID(), fields.getUserEmail());
        else
            response = adminService.deactivateCustomer(fields.getUserID(), fields.getUserEmail());

        if(response.isErrorOccurred()){
            if(response.isFieldErrorOccurred()){
                modelMap.put("removeUserErrorMessage",response.getMessage());
                return "removeUser";
            }
            modelMap.put("statusCode", response.getStatusCode());
            modelMap.put("errorMessage",response.getMessage());
            return "error";
        }

        return "redirect:/admin/home";
    }

}
