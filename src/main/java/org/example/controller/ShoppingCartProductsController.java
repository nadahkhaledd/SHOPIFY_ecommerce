package org.example.controller;

import org.example.entity.*;
import org.example.service.order.OrderServiceImpl;
import org.example.service.address.AddressService;
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
    private final OrderServiceImpl orderServiceImpl;
    private final AddressService addressService;

    @Autowired
    public ShoppingCartProductsController(ShoppingCartProductsService cartServices, OrderServiceImpl orderServiceImpl,
                                          ProductService productService, UserService userService, AddressService addressService) {
        this.cartServices = cartServices;
        this.productService = productService;
        this.userService = userService;
        this.orderServiceImpl = orderServiceImpl;
        this.addressService = addressService;
    }

    @GetMapping("/update/{userId}/{id}")
    public String updateQuantity(@PathVariable("userId") int userId, @PathVariable("id") int id,
                                 @RequestParam int quantity, Model model) {
        String updatedMessage = cartServices.updateProductQuantityInCart(id, quantity);
        model.addAttribute("updateError", updatedMessage);
        return "redirect:/cart/view?id="+userId;
    }

    @GetMapping("/delete/{userId}/{id}")
    public String deleteCartItem(@PathVariable("userId") int userId, @PathVariable("id") int id) {
        cartServices.removeFromCart(id);
        return "redirect:/cart/view?id="+userId;
    }

    @GetMapping("/add/{userId}")
    public String createNewCartItem(Model model) {
        model.addAttribute("cartItem", new ShoppingCartProducts());
        return "";
    }

    @PostMapping("/add/{userId}")
    public String addToCart(@Valid @ModelAttribute("cartItem") ShoppingCartProducts cartProducts, BindingResult bindingResult,
                            @PathVariable("userId") int userId, @RequestParam int productId) {
        if(bindingResult.hasErrors()) {
            Map<String, Object> model = bindingResult.getModel();
            return "";
        }
        Product product = productService.getProductsById(productId);
        User user = userService.getUserById(userId);
        cartProducts.setProductQuantity(1);
        cartProducts.setProduct(product);
        cartProducts.setUser(user);
        cartServices.addToCart(cartProducts);
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

    @GetMapping("/checkout/{userId}")
    public String checkOut(@PathVariable("userId") int userId, Model model) {
        List<ShoppingCartProducts> cartProducts = cartServices.viewCart(userId);
        Double cartTotal = cartServices.calculateTotal(userId);
        List<Address> addresses = addressService.getUserAddresses(userId);
        model.addAttribute("cartProducts", cartProducts);
        model.addAttribute("cartTotal",  cartTotal);
        model.addAttribute("addresses", addresses);
        return "checkout";
    }
}
