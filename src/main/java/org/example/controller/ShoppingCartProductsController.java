package org.example.controller;

import org.example.entity.Address;
import org.example.entity.Order;
import org.example.entity.ShoppingCartProducts;
import org.example.entity.User;
import org.example.model.Response;
import org.example.service.address.AddressService;
import org.example.service.order.OrderService;
import org.example.service.product.ProductService;
import org.example.service.shoppingcartproducts.ShoppingCartProductsService;
import org.example.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/// all controller needs to be updated
@Controller
@RequestMapping("/cart")
public class ShoppingCartProductsController {
    private final ShoppingCartProductsService cartServices;
    private final AddressService addressService;

    @Autowired
    public ShoppingCartProductsController(ShoppingCartProductsService cartServices, AddressService addressService) {
        this.cartServices = cartServices;
        this.addressService = addressService;
    }

    @GetMapping("/update/{id}")
    public String updateQuantity(@PathVariable("id") int id, @RequestParam int quantity,
                                 Model model) {
        Response response = cartServices.updateProductQuantityInCart(id, quantity);

        if(response.isErrorOccurred()){
            if(response.isFieldErrorOccurred()){
                model.addAttribute("updateError", response.getMessage());
            }
            model.addAttribute("errorMessage", response.getMessage());
            return "error";
        }
        return "redirect:/cart/view";
    }

    @GetMapping("/delete/{id}")
    public String deleteCartItem(@PathVariable("id") int id,
                                 Model model) {
        Response response = cartServices.removeFromCart(id);
        if(response.isErrorOccurred()) {
            model.addAttribute("statusCode", response.getMessage());
            model.addAttribute("errorMessage", response.getMessage());
            return "error";
        }
        return "redirect:/cart/view";
    }

    @GetMapping("/view")
    public String viewCart(Model model, HttpSession session) {
        if(session.getAttribute("user-Id")==null){
            return "redirect:/login";
        }
        int userId = (int) session.getAttribute("user-Id");
        Response<List<ShoppingCartProducts>> cartProducts = cartServices.viewCart(userId);
        if(cartProducts.isErrorOccurred()) {
            model.addAttribute("statusCode", cartProducts.getStatusCode());
            model.addAttribute("errorMessage", cartProducts.getMessage());
            return "error";
        }
        model.addAttribute("cartProducts", cartProducts.getObjectToBeReturned());
        if(cartProducts.getObjectToBeReturned().isEmpty()) {
            model.addAttribute("cartTotal", 0.0);
            model.addAttribute("shipping", 0.0);
        } else {
            Response<Double> cartTotal = cartServices.calculateTotal(userId);
            model.addAttribute("cartTotal", cartTotal.getObjectToBeReturned());
            model.addAttribute("shipping", 10.0);
        }
        return "cart";
    }


    /// to be modified
    @GetMapping("/checkout")
    public String checkOut(Model model, HttpSession session) {
        if(session.getAttribute("user-Id")==null){
            return "redirect:/login";
        }
        int userId = (int) session.getAttribute("user-Id");
        Response<List<ShoppingCartProducts>> response = cartServices.viewCart(userId);
        List<ShoppingCartProducts> cartProducts = response.getObjectToBeReturned();
        Double cartTotal = cartServices.calculateTotal(userId).getObjectToBeReturned();
        Response<List<Address>> addresses = addressService.getUserAddresses(userId);
        model.addAttribute("cartProducts", cartProducts);
        model.addAttribute("cartTotal",  cartTotal);
        model.addAttribute("addresses", addresses.getObjectToBeReturned());
        model.addAttribute("newOrder", new Order());
        return "checkout";
    }
}
