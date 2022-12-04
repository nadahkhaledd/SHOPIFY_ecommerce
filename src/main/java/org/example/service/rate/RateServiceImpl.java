package org.example.service.rate;

import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.entity.Rate;
import org.example.entity.User;
import org.example.model.UserInputReview;
import org.example.repository.rate.RateRepo;
import org.example.repository.rate.RateRepoImpl;

import org.example.service.customer.CustomerService;
import org.example.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateServiceImpl implements RateService {
    private RateRepo rateRepository;
    private CustomerService customerService;
    private ProductService productService;
    @Autowired
    public RateServiceImpl(RateRepo rateRepository,CustomerService customerService,ProductService productService) {
        this.rateRepository = rateRepository;
        this.customerService=customerService;
        this.productService=productService;
    }
    @Override
    public void AssignRateToProduct(UserInputReview userRate){
        Customer customer=customerService.getCustomerById(userRate.getUserId());
        Product product=productService.getProductsById(userRate.getProductId());
        Rate rate=new Rate(userRate.getRate(),userRate.getReview(),customer,product);
        System.out.println(rate.toString());
        rateRepository.addRate(rate);
    }

    /** calculate product rate (avg of users' rate to this product)
     * @param productId id of the product
     * @return rate of the product
     */
    private double calculateRateOfProduct(int productId) {
        double rate=rateRepository.calculateRateOfProduct(productId);
       // System.out.println("in rate service "+rate);
        return rate;
    }
    @Override
    public void calculateProductRate(Product product) {
        if(!product.getRates().isEmpty())
            product.setRate(calculateRateOfProduct(product.getId()));
    }
  /*  public List<Float> getRates(int productId){
        return rateRepository.getRates(productId);
    }*/
}
