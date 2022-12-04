// 
// Decompiled by Procyon v0.5.36
// 

package org.example.controller;

import org.springframework.web.bind.annotation.*;
import org.example.entity.User;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.service.security.AuthService;
import org.springframework.stereotype.Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequestMapping({ "/Auth" })
@Controller
@SessionAttributes({"user","error"})
public class AuthController
{
    @Autowired
    AuthService authService;

    @GetMapping({ "/login" })
    public String loginForm(Model model) {
//        if (model.getAttribute("user") !=null) {
//            return "redirect:products/shop";
//        }
////        User user = new User();
////        model.addAttribute("user",user);
        return "login";
    }
    //get user type

    @PostMapping({ "/loginPost" })
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
        return "redirect:/products/shop";
    }

    @PostMapping({ "/register" })
    public String register(@ModelAttribute("regUser")  User user) {
        return "EmailSentPage";
    }

    @GetMapping({ "/logout" })
    public String logout(Model model) {
      //  User user = new User();
        model.addAttribute("user","0");
        return "redirect:/Auth/login";
    }

    @GetMapping({ "/activate/{email}" })
    public String activate(@PathVariable("email") String email) {
        if (this.authService.verifyEmail(email)) {
            return "shop";
        }
        return "EmailSentPage";
    }
}
