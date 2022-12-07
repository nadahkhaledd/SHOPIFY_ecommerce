package org.example.controller;

import org.example.entity.Address;
import org.example.entity.Customer;
import org.example.entity.User;
import org.example.model.Response;
import org.example.service.address.AddressService;
import org.example.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/address")
@SessionAttributes("userId")
public class AddressController {
    private final AddressService addressService;
    private final UserService userService;

    @Autowired
    public AddressController(AddressService addressService, UserService userService) {
        this.addressService = addressService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String newAddress(Model model) {
        model.addAttribute("address", new Address());
        return "addAddress";
    }

    @PostMapping("/add")
    public String addAddress(@Valid @ModelAttribute("address") Address address, BindingResult bindingResult,
                             Model model) {
        if(bindingResult.hasErrors()) {
            Map<String, Object> modelMap = bindingResult.getModel();
            return "addAddress";
        }
        int userId = (int)model.getAttribute("userId");
        Customer customer = (Customer) userService.getUserById(userId).getObjectToBeReturned();
        address.setCustomer(customer);
        Response response = addressService.addAddress(address);
        if(response.isErrorOccurred()) {
            if(response.isFieldErrorOccurred()) {
                model.addAttribute("addAddressErrorMessage", response.getMessage());
                return "addAddress";
            }
            model.addAttribute("statusCode", response.getStatusCode());
            model.addAttribute("errorMessage", response.getMessage());
            return "error";
        }
        return "redirect:/address/view";
    }

    @GetMapping("/delete/{id}")
    public String deleteAddress(@PathVariable int id, ModelMap modelMap) {
        Response response = addressService.deleteAddress(id);
        if(response.isErrorOccurred()) {
            modelMap.put("statusCode", response.getStatusCode());
            modelMap.put("errorMessage", response.getMessage());
            return "error";
        }
        return "redirect:/address/view";
    }

    @GetMapping("/update/{id}")
    public String newAddress(@PathVariable("id") int id, Model model) {
        Response<Address> address = addressService.getAddress(id);
        model.addAttribute("updateAddress", address.getObjectToBeReturned());
        return "updateAddress";
    }

    @PostMapping("/update/{id}")
    public String updateAddress(@Valid @ModelAttribute("updateAddress") Address address, BindingResult bindingResult,
                                 ModelMap modelMap, Model model) {
        if(bindingResult.hasErrors()) {
            Map<String, Object> bindingResultModel = bindingResult.getModel();
            return "updateAddress";
        }
        int userId = (int) model.getAttribute("userId");
        Customer customer = (Customer) userService.getUserById(userId).getObjectToBeReturned();
        address.setCustomer(customer);
        Response response = addressService.updateAddress(address);
        if(response.isErrorOccurred()) {
            if(response.isFieldErrorOccurred()) {
                modelMap.put("updateAddressErrorMessage", response.getMessage());
                return "updateAddress";
            }
            modelMap.put("statusCode", response.getStatusCode());
            modelMap.put("errorMessage", response.getMessage());
            return "error";
        }
        return "redirect:/address/view";
    }

    @GetMapping("/view")
    public String getAddresses(Model model) {
        int userId = (int) model.getAttribute("userId");
        Response<List<Address>> addresses = addressService.getUserAddresses(userId);
        if(addresses.isErrorOccurred()) {
            model.addAttribute("statusCode", addresses.getStatusCode());
            model.addAttribute("errorMessage", addresses.getMessage());
            return"error";
        }
        model.addAttribute("addresses", addresses.getObjectToBeReturned());
        return "viewAddresses";
    }
}
