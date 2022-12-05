package org.example.controller;

import org.example.entity.Category;
import org.example.entity.Product;
import org.example.model.Response;
import org.example.service.category.CategoryService;
import org.example.service.product.ProductService;
import org.example.utility.ProductsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    ProductsUtils productsUtils=new ProductsUtils();
    @PostMapping
    public ModelAndView searchByName(@RequestParam String searchValue){
        ModelAndView modelAndView = new ModelAndView("viewProducts");
        List<Category> categories = categoryService.searchByCategoryName(searchValue);
        Response <List<Product>> productsResponse = productService.searchByProductName(searchValue);
        if (productsResponse.isErrorOccurred()){
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage",productsResponse.getMessage());
            return modelAndView;
        }
        Set<Product> allReturnedProducts=productsUtils.
                mergingProductsAndCategoryProducts(categories,productsResponse.getObjectToBeReturned());
        System.out.println("in search");
        System.out.println(allReturnedProducts.toString());
        modelAndView.addObject("products", allReturnedProducts);
        return modelAndView;
    }
}
