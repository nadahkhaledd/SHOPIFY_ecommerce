package org.example.controller;

import org.example.entity.Product;
import org.example.entity.ShoppingCartProducts;
import org.example.entity.User;
import org.example.service.admin.AdminService;
import org.example.service.product.ProductService;
import org.example.service.shoppingcartproducts.ShoppingCartProductsService;
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
@RequestMapping("/cart")
public class ShoppingCartProductsController {
    private final ShoppingCartProductsService cartServices;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public ShoppingCartProductsController(ShoppingCartProductsService cartServices,
                                          ProductService productService, UserService userService) {
        this.cartServices = cartServices;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/update/{userId}/{id}")
    public String updateQuantity(@PathVariable("userId") int userId, @PathVariable("id") int id,
                                 @RequestParam int quantity) {
        cartServices.updateProductQuantityInCart(id, quantity);
        return "redirect:/cart/view?id="+userId;
    }

    @GetMapping("/delete/{userId}/{id}")
    public String deleteCartItem(@PathVariable("userId") int userId, @PathVariable("id") int id) {
        cartServices.removeFromCart(id);
        return "redirect:/cart/view?id="+userId;
    }

    @GetMapping("/add/{userId}")
    public String addToCart(@PathVariable int userId, @RequestParam int productId){
        ShoppingCartProducts cartProduct = new ShoppingCartProducts();
        cartProduct.setProductQuantity(1);
        cartProduct.setProduct(productService.getProduct(productId));
        cartProduct.setUser(userService.getUserById(userId));
        cartServices.addToCart(cartProduct);
        return "redirect:/products/getAllProducts";
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
