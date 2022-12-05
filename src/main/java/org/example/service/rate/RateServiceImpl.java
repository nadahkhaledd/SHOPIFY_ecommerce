package org.example.service.rate;

import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.entity.Rate;
import org.example.model.Response;
import org.example.model.UserInputReview;
import org.example.repository.rate.RateRepo;

import org.example.service.customer.CustomerService;
import org.example.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Response AssignRateToProduct(UserInputReview userRate){
        Customer customer=customerService.getCustomerById(userRate.getUserId());
        Response<Product> productResponse=productService.getProductsById(userRate.getProductId());
        Rate rate=new Rate(userRate.getRate(),userRate.getReview(),customer,productResponse.getObjectToBeReturned());
        System.out.println(rate.toString());
        Response rateResponse;
        if(!productResponse.isErrorOccurred()) {
            rateResponse = rateRepository.addRate(rate);
            if(rateResponse.isErrorOccurred()){
                return rateResponse;
            }
        }
        else{
            return productResponse;
        }
        return new Response("Ok",200,false);
    }

    /** calculate product rate (avg of users' rate to this product)
     * @param productId id of the product
     * @return rate of the product
     */
    private double calculateRateOfProduct(int productId) {
        double rate=rateRepository.calculateRateOfProduct(productId).getObjectToBeReturned();
       // System.out.println("in rate service "+rate);
        return rate;
    }
    @Override
    public Response setProductRate(Product product) {
        if(!product.getRates().isEmpty())
            product.setRate(calculateRateOfProduct(product.getId()));
        return new Response("Done", 200, false);

      //  return new Response("no rates for the given products", 404, true);
    }
  /*  public List<Float> getRates(int productId){
        return rateRepository.getRates(productId);
    }*/
}
