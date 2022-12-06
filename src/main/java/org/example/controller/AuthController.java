package org.example.controller;
import org.example.entity.Customer;
import org.example.entity.User;
import org.example.service.security.AuthService;
import org.example.utility.DateUtils;
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

    private DateUtils dateUtils = new DateUtils();

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("date", dateUtils.dateYearsAgo(18));
        model.addAttribute("user", new Customer());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @DateTimeFormat(pattern = "yyyy-MM-dd") @ModelAttribute("user") Customer user,Model model){
        if(authService.checkIfUserAlreadyExists(user.getEmail())){
            model.addAttribute("error","you already have an account please login directly");
            return "redirect:/login";
        }

        if(authService.register(user)){
            //authService.sendVerificationEmail(user.getEmail());
            return "goToYourMail";
        }
        model.addAttribute("error","you've entered an invalid data, please try again");
        return "register";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user")  User user, Model model) {

        if(authService.checkIfSuspended(user.getEmail())){
           // authService.sendVerificationEmail(user.getEmail());
            return "goToYourMail";
        }

        User result = this.authService.login(user.getEmail(), user.getPassword());
        if (result==null) {
            model.addAttribute("error","Email or Password is Wrong");
            return "login";
        }else{
            model.addAttribute("userId", result.getId());
            String regex = "[a-z0-9]+@shopify.com";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(result.getEmail());
            if(matcher.matches()){
                return "redirect:/admin/home";
            }


            if(!authService.checkIfActivated(result.getId())){
                return "goToYourMail";
            }
        }

        return "redirect:/home";
    }

    @GetMapping("/activate/{email}")
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
