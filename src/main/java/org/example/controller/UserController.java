package org.example.controller;

import org.example.entity.User;
import org.example.model.Response;
import org.example.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getUserInfo(Model model, HttpSession session) {
        if  ( session.getAttribute("user-Id")==null){
            return "redirect:/login";
        }
        int userId = (int) session.getAttribute("user-Id");
        Response<User> user = userService.getUserById(userId);
        model.addAttribute("userInfo", user.getObjectToBeReturned());
        return "userProfile";
    }
}
