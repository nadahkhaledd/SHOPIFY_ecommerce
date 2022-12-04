package org.example.controller;

import org.example.entity.Address;
import org.example.entity.Customer;
import org.example.entity.User;
import org.example.service.address.AddressService;
import org.example.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;
    private final UserService userService;

    @Autowired
    public AddressController(AddressService addressService, UserService userService) {
        this.addressService = addressService;
        this.userService = userService;
    }

    @GetMapping("/add/{userId}")
    public String newAddress(Model model) {
        model.addAttribute("address", new Address());
        return "addAddress";
    }

    @PostMapping("/add/{userId}")
    public String addAddress(@Valid @ModelAttribute("address") Address address, BindingResult bindingResult,
                             @PathVariable("userId") int userId) {
        if(bindingResult.hasErrors()) {
            Map<String, Object> model = bindingResult.getModel();
            return "addAddress";
        }
        Customer customer = (Customer) userService.getUserById(userId);
        address.setCustomer(customer);
        addressService.addAddress(address);
        return "redirect:/address/view?id="+userId;
    }

    @GetMapping("/delete/{userId}")
    public String deleteAddress(@PathVariable("userId") int userId,@RequestParam int id) {
        addressService.deleteAddress(id);
        return "redirect:/address/view?id=" + userId;
    }

    @GetMapping("/update/{userId}")
    public String newAddress(@RequestParam int id, Model model) {
        Address address = addressService.getAddress(id);
        model.addAttribute("updateAddress", address);
        return "updateAddress";
    }

    @PostMapping("/update/{userId}")
    public String updateAddress(@Valid @ModelAttribute("updateAddress") Address address, BindingResult bindingResult,
                             @PathVariable("userId") int userId) {
        if(bindingResult.hasErrors()) {
            Map<String, Object> model = bindingResult.getModel();
            return "updateAddress";
        }
        addressService.updateAddress(address);
        return "redirect:/address/view?id="+userId;
    }

    @GetMapping("/view")
    public String getAddresses(@RequestParam int id, Model model) {
        List<Address> addresses = addressService.getUserAddresses(id);
        model.addAttribute("addresses", addresses);
        return "viewAddresses";
    }
}
