package org.example.controller;
import org.example.entity.Customer;
import org.example.entity.User;
import org.example.enums.CustomerStatus;
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

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Controller
@SessionAttributes({"isAdmin"})
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
    static boolean doLookup( String hostName ) throws NamingException {
        Hashtable env = new Hashtable();
        env.put("java.naming.factory.initial",
                "com.sun.jndi.dns.DnsContextFactory");
        DirContext ictx = new InitialDirContext( env );
        Attributes attrs =
                ictx.getAttributes( hostName, new String[] { "MX" });
        Attribute attr = attrs.get( "MX" );
        if( attr == null ) return( false );

        return( attr.size()==0?false:true );
    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("date", dateUtils.dateYearsAgo(18));
        model.addAttribute("user", new Customer());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @DateTimeFormat(pattern = "yyyy-MM-dd") @ModelAttribute("user") Customer user,Model model){
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(user.getEmail());
        if(!matcher.matches()){
            model.addAttribute("registerError","Please enter a valid Email");
            return "register";
        }
        try {
            String[] host = user.getEmail().split("@");
            doLookup(host[1]);
        }
        catch (Exception e){
            model.addAttribute("registerError","Please enter a valid Email");
            return "register";
        }

        Response<Boolean> response=authService.checkIfUserAlreadyExists(user.getEmail());
        if(response.isErrorOccurred()){
            if(response.isFieldErrorOccurred()){
                model.addAttribute("error",response.getMessage());
                return "redirect:/login";
            }
            model.addAttribute("errorMessage",response.getMessage());
            model.addAttribute("statusCode",response.getStatusCode());
            return "error";
        }
        Response registerResponse=authService.register(user);
        if(registerResponse.isErrorOccurred()){
            model.addAttribute("errorMessage",registerResponse.getMessage());
            model.addAttribute("statusCode",registerResponse.getStatusCode());
            return "error";
        }
        authService.sendVerificationEmail(user.getEmail());
        return "goToYourMail";//if register has no errors
    }
    @GetMapping("/reset")
    public String resetPass(Model model) {
        model.addAttribute("user", new User());
        return "resetPassword";
    }
    @PostMapping("/reset")
    public String resetPassword(@ModelAttribute("user")  User user, Model model, HttpSession session) {
        Response resetPassResponse=authService.resetPassword(user.getEmail(), user.getPassword());
        if(resetPassResponse.isFieldErrorOccurred()){
            model.addAttribute("error",resetPassResponse.getMessage());
            return "redirect:/reset";
        }
        return "redirect:/login";
    }

        @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user")  User user, Model model, HttpSession session) {

        if(authService.checkIfSuspended(user.getEmail())){
            authService.sendVerificationEmail(user.getEmail());
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
            session.setAttribute("user-Id",responseResult.getObjectToBeReturned().getId());
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


        session.setAttribute("user-Id",responseResult.getObjectToBeReturned().getId());
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
        return "redirect:/reset";
    }

    @GetMapping("/logout")
    public String logout(Model model,HttpSession session){
      //  model.addAttribute("userId", null);
        session.invalidate();
        return  "redirect:/login";
    }

}
