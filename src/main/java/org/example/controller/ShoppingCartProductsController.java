package org.example.controller;

import org.example.entity.Address;
import org.example.entity.Product;
import org.example.entity.ShoppingCartProducts;
import org.example.model.Response;
import org.example.service.OrderService;
import org.example.service.address.AddressService;
import org.example.service.product.ProductService;
import org.example.service.shoppingcartproducts.ShoppingCartProductsService;
import org.example.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/// all controller needs to be updated
@Controller
@RequestMapping("/cart")
@SessionAttributes("userId")
public class ShoppingCartProductsController {
    private final ShoppingCartProductsService cartServices;
    private final ProductService productService;
    private final UserService userService;
    private final OrderService orderService;
    private final AddressService addressService;

    @Autowired
    public ShoppingCartProductsController(ShoppingCartProductsService cartServices, OrderService orderService,
                                          ProductService productService, UserService userService, AddressService addressService) {
        this.cartServices = cartServices;
        this.productService = productService;
        this.userService = userService;
        this.orderService = orderService;
        this.addressService = addressService;
    }

    @GetMapping("/update/{id}")
    public String updateQuantity(@PathVariable("id") int id, @RequestParam int quantity,
                                 Model model, ModelMap modelMap) {
        //modelMap.put("updateQuantityErrorMessage","");//initialize as empty
        Response response = cartServices.updateProductQuantityInCart(id, quantity);
        if(response.isErrorOccurred()){
            if(response.isFieldErrorOccurred()){
                model.addAttribute("updateError", response.getMessage());
            }
            modelMap.put("errorMessage",response.getMessage());
            return "error";
        }
//        model.addAttribute("updateError", response.getMessage());
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

    /// to be modified and separated to post
    @GetMapping("/add/{userId}")
    public String addToCart(@PathVariable int userId, @RequestParam int productId, ModelMap modelMap){
        ShoppingCartProducts cartProduct = new ShoppingCartProducts();
        cartProduct.setProductQuantity(1);
        Response productResponse=productService.getProduct(productId);
        if(productResponse.isErrorOccurred()){
            modelMap.put("errorMessage",productResponse.getMessage());
            return "error";
        }

        cartProduct.setProduct((Product) productResponse.getObjectToBeReturned());
        cartProduct.setUser(userService.getUserById(userId).getObjectToBeReturned());
        cartServices.addToCart(cartProduct);
        return "redirect:/products/getAllProducts";
    }

    @GetMapping("/view")
    public String viewCart(Model model) {
        int userId = (int) model.getAttribute("userId");
        Response<List<ShoppingCartProducts>> cartProducts = cartServices.viewCart(userId);
        if(cartProducts.isErrorOccurred()) {
            model.addAttribute("statusCode", cartProducts.getStatusCode());
            model.addAttribute("errorMessage", cartProducts.getMessage());
            return"redirect:/user/profile";
        }
        Response<Double> cartTotal = cartServices.calculateTotal(userId);
        model.addAttribute("cartProducts", cartProducts.getObjectToBeReturned());
        model.addAttribute("cartTotal", cartTotal.getObjectToBeReturned());
        return "cart";
    }


    /// to be modified
    @GetMapping("/checkout")
    public String checkOut(Model model) {
        int userId = (int) model.getAttribute("userId");
        List<ShoppingCartProducts> cartProducts = cartServices.viewCart(userId).getObjectToBeReturned();
        Double cartTotal = cartServices.calculateTotal(userId).getObjectToBeReturned();
        Response<List<Address>> addresses = addressService.getUserAddresses(userId);
        model.addAttribute("cartProducts", cartProducts);
        model.addAttribute("cartTotal",  cartTotal);
        model.addAttribute("addresses", addresses.getObjectToBeReturned());
        return "checkout";
    }
}
