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

@Controller
@RequestMapping("/user")
@SessionAttributes("userId")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getUserInfo(Model model) {
        if(model.getAttribute("userId")==null){
            return "redirect:/login";
        }
        int userId = (int) model.getAttribute("userId");
        Response<User> user = userService.getUserById(userId);
        model.addAttribute("userInfo", user.getObjectToBeReturned());
        return "userProfile";
    }
}
