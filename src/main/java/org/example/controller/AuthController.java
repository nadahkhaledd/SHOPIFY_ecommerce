package org.example.controller;
import org.example.entity.Customer;
import org.example.entity.User;
import org.example.service.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Controller
@SessionAttributes({"user","error"})
public class AuthController {
    @Autowired
    AuthService authService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("user", new Customer());
        return "register";
    }

    //...
//    @PostMapping("/register")
//    public String addUser(@Valid @ModelAttribute("customer") Customer customer,
//                          BindingResult bindingResult,Model model) throws Exception {
//        if (bindingResult.hasErrors()) {
//            return "register";
//        }
//        //check if there is user before used this email
//        Customer existingUser = userRegisterService.findByEmail(customer.getEmail());
//        if(existingUser!=null) {
//            model.addAttribute("error","There is already an account with this email:" + customer.getEmail());
//            return "alreadyRegistered";
//        }else {
            //register our user to DB
    //...

    @PostMapping("/register")
    public String register(@Valid @DateTimeFormat(pattern = "yyyy-MM-dd") @ModelAttribute("user") Customer user){
        if(authService.register(user)){

            //send verification email and then redirect him to login page
            //temp ^_^
            return "redirect:/home";
        }
        return "register";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user")  User user, Model model) {
        User result = this.authService.login(user.getEmail(), user.getPassword());
        if (result.getId()==0) {
            model.addAttribute("error","Email or Password is Wrong");
            return "login";
        }
        String regex = "[a-z0-9]+@admin.com";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(result.getEmail());
        if(matcher.matches()){
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("user", result);
        return "redirect:/home";
    }




}
