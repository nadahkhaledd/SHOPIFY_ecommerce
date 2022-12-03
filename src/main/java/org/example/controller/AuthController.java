// 
// Decompiled by Procyon v0.5.36
// 

package org.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.example.entity.User;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.service.security.AuthService;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({ "/Auth" })
@Controller
@SessionAttributes({ "user" })
public class AuthController
{
    @Autowired
    AuthService authService;

    @GetMapping({ "/login" })
    public String loginForm(final Model model) {
        if (model.getAttribute("user") != null) {
            return "shop";
        }
        final User user = new User();
        model.addAttribute("user", (Object)user);
        return "login";
    }

    @PostMapping({ "/login" })
    public String login(@ModelAttribute("user") final User user, final Model model) {
        final User result = this.authService.login(user.getEmail(), user.getPassword());
        if (result == null) {
            return "login";
        }
        model.addAttribute("user", (Object)result);
        return "shop";
    }

    @PostMapping({ "/register" })
    public String register(@ModelAttribute("regUser") final User user) {
        System.out.println(user.toString());
        return "EmailSentPage";
    }

    @GetMapping({ "/logout" })
    public String logout(final Model model) {
        final User user = new User();
        model.addAttribute("user", (Object)user);
        return "login";
    }

    @GetMapping({ "/activate" })
    public String activate(final String email) {
        if (this.authService.verifyEmail(email)) {
            return "shop";
        }
        return "EmailSentPage";
    }
}