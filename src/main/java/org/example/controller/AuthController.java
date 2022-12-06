package org.example.controller;
import org.example.entity.Customer;
import org.example.entity.User;
import org.example.service.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Controller
@SessionAttributes({"userId","error"})
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

//        //check if there is user before used this email
//        Customer existingUser = userRegisterService.findByEmail(customer.getEmail());
//        if(existingUser!=null) {
//            model.addAttribute("error","There is already an account with this email:" + customer.getEmail());
//            return "alreadyRegistered";
//        }else {


    @PostMapping("/register")
    public String register(@Valid @DateTimeFormat(pattern = "yyyy-MM-dd") @ModelAttribute("user") Customer user){
        if(authService.register(user)){
            authService.sendVerificationEmail(user.getEmail());
            return "goToYourMail";
        }
        return "register";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "goToYourMail";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user")  User user, Model model) {
        if(!authService.checkIfActivated(user.getId())){
            return "goToYourMail";
        }
        User result = this.authService.login(user.getEmail(), user.getPassword());
        if (result==null) {
            model.addAttribute("error","Email or Password is Wrong");
            return "login";
        }
        String regex = "[a-z0-9]+@admin.com";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(result.getEmail());
        if(matcher.matches()){
            return "redirect:/admin/home";
        }
        model.addAttribute("userId", result.getId());
        return "redirect:/home";
    }

    @GetMapping({ "/activate/{email}" })
    public String activate(@PathVariable("email") String email) {
        authService.verifyEmail(email);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(Model model){
        model.addAttribute("userId", null);
        return  "redirect:/login";
    }



}
