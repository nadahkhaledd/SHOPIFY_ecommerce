package org.example.controller;

import org.example.entity.User;
import org.example.service.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.jws.WebParam;

@Controller
@RequestMapping("/products")
@SessionAttributes({"user","error"})
public class ProductController {
    @Autowired
    AuthService authService;
    @GetMapping("/shop")
    public String test(Model moddel){
    if(moddel.getAttribute("user")=="0"){
        return "redirect:/Auth/login";
    }
//        User result = this.authService.login(user.getEmail(), user.getPassword());
//        if (result.getId()==0) {
//            return "redirect:/Auth/login";
//        }
//        if(model.getAttribute("userId")!="0"){
//            return "redirect:login";
//        }
        return "shop";
    }
}
