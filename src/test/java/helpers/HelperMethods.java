package helpers;

import org.example.entity.Category;
import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.entity.Rate;
import org.example.enums.CustomerStatus;
import org.example.enums.Gender;
import org.example.model.Star;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class HelperMethods {
    public Star initStar(int numberOfFullStars,boolean isHalfStar,int numberOfEmptyStars){
        return new Star(numberOfEmptyStars,isHalfStar,numberOfFullStars);
    }
    public List<Product> initProductsList(){
        List<Product> list = new ArrayList<>();
        Category mensDresses=new Category();
        mensDresses.setName("men's dresses");
        mensDresses.setImagePath("dummy pic");
        mensDresses.setId(1);
        Product product1=new Product("tshirt","dummy pic",120.0,mensDresses,12);
        Product product2=new Product("red tshirt","dummy pic",120.0,mensDresses,1);
        list.add(product1);
        list.add(product2);
        mensDresses.setProducts(list);
        return list;
    }
    public Product initProduct(){
        Category mensDresses=new Category();
        mensDresses.setName("men's dresses");
        mensDresses.setImagePath("dummy pic");
        Product product=new Product("tshirt","dummy pic",120.0,mensDresses,12);
        product.setId(1);
        product.setRate(5.0);
        product.setRates(Arrays.asList(new Rate()));
        return product;
    }
    public Customer initCustomer(){
        return new Customer(
                1,"Hagar", "Ehab", "hagar123@gmail.com", "hello", Gender.female,new Date(), CustomerStatus.ACTIVATED
        );
    }
    public List<Category> initCategoryList(){
        List<Category> categoriesList = new ArrayList<>();
        Category kidsDresses=new Category();
        kidsDresses.setName("kid's dresses");
        kidsDresses.setImagePath("dummy pic");
        Category womenDresses=new Category();
        womenDresses.setName("women's dresses");
        womenDresses.setImagePath("dummy pic");
        categoriesList.add(kidsDresses);
        categoriesList.add(womenDresses);
        Product product1=new Product("kids tshirt","dummy pic",120.0,kidsDresses,12);
        Product product2=new Product("women tshirt","dummy pic",120.0,womenDresses,12);
        kidsDresses.setProducts(Arrays.asList(product1));
        womenDresses.setProducts(Arrays.asList(product2));
        return categoriesList;
    }
}
