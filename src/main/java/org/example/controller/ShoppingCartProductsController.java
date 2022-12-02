package org.example.controller;

import org.example.entity.Product;
import org.example.entity.ShoppingCartProducts;
import org.example.entity.User;
import org.example.service.admin.AdminService;
import org.example.service.shoppingcartproducts.ShoppingCartProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class ShoppingCartProductsController {
    private final ShoppingCartProductsService cartServices;

    @Autowired
    public ShoppingCartProductsController(ShoppingCartProductsService cartServices,
                                          AdminService adminService) {
        this.cartServices = cartServices;
    }

    @GetMapping("/delete/{userId}/{id}")
    public String deleteCartItem(@PathVariable("userId") int userId, @PathVariable("id") int id) {
        boolean isRemoved = cartServices.removeFromCart(id);
        return "redirect:/cart/view?id="+userId;
    }

    @GetMapping("/view")
    public String viewCart(Model model, @RequestParam int id) {
        List<ShoppingCartProducts> cartProducts = cartServices.viewCart(id);
        Double cartTotal = cartServices.calculateTotal(id);
        model.addAttribute("cartProducts", cartProducts);
        model.addAttribute("cartTotal", cartTotal);
        return "cart";
    }
}
