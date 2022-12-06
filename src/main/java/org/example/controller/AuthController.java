package org.example.controller;
import org.example.entity.Customer;
import org.example.entity.User;
import org.example.model.Response;
import org.example.service.security.AuthService;
import org.example.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Controller
@SessionAttributes({"userId","error", "isAdmin"})
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
     /*   if(authService.checkIfUserAlreadyExists(user.getEmail())){
            model.addAttribute("error","you already have an account please login directly");
            return "redirect:/login";
        }

        if(authService.register(user)){

            return "goToYourMail";
        }*/
        System.out.println("in authh controller");
        Response<Boolean> response=authService.checkIfUserAlreadyExists(user.getEmail());
        System.out.println("$$ "+response.toString());
        if(response.isErrorOccurred()){
            System.out.println("error occured in response");
            if(response.isFieldErrorOccurred()){//
                model.addAttribute("error",response.getMessage());
                return "redirect:/login";
            }
            model.addAttribute("errorMessage",response.getMessage());
            model.addAttribute("statusCode",response.getStatusCode());
            return "error";
        }
        Response registerResponse=authService.register(user);
        System.out.println("$$ 2"+registerResponse.toString());
        if(registerResponse.isErrorOccurred()){
            System.out.println("yopu have entered an invalid data");
            model.addAttribute("errorMessage",registerResponse.getMessage());
            model.addAttribute("statusCode",registerResponse.getStatusCode());
            return "error";
        }

        return "goToYourMail";//if register has no errors
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
        Response<User> responseResult = this.authService.login(user.getEmail(), user.getPassword());
        if (responseResult .isErrorOccurred()) {
            if(responseResult.isFieldErrorOccurred()){
              model.addAttribute("error",responseResult .getMessage());
              return "login";
            }
            model.addAttribute("errorMessage",responseResult.getMessage());
            model.addAttribute("statusCode",responseResult.getStatusCode());
            return "error";
        }
        User result=responseResult.getObjectToBeReturned();
        model.addAttribute("userId", result.getId());

        String regex = "[a-z0-9]+@shopify.com";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(result.getEmail());
        model.addAttribute("isAdmin", matcher.matches());
        if(matcher.matches()){
            return "redirect:/admin/home";
        }
        Response<Boolean> checkIfActivatedResponse=authService.checkIfActivated(result.getId());
       if(checkIfActivatedResponse.isErrorOccurred()){
            model.addAttribute("errorMessage",checkIfActivatedResponse.getMessage());
            model.addAttribute("statusCode",checkIfActivatedResponse.getStatusCode());
            return "error";
        }
        if(!checkIfActivatedResponse.getObjectToBeReturned()){
            return "goToYourMail";
        }
        //response hereee!!
        if(authService.checkIfSuspended(result.getEmail())){
            //send email
            return "goToYourMail";
        }


        return "redirect:/home";
    }

    @GetMapping("/activate/{email}")
    public String activate(@PathVariable("email") String email, ModelMap modelMap) {
        Response<Boolean> verifyEmailResponse=authService.verifyEmail(email);
        if(verifyEmailResponse.isErrorOccurred()){
            modelMap.addAttribute("errorMessage",verifyEmailResponse.getMessage());
            modelMap.addAttribute("statusCode",verifyEmailResponse.getStatusCode());
            return "error";
        }
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(Model model, ModelMap modelMap){
        model.addAttribute("userId", null);
        modelMap.clear();

        return  "redirect:/login";
    }

}
