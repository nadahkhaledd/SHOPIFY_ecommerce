package org.example.service.rate;

import org.example.entity.Product;
import org.example.entity.Rate;
import org.example.model.Response;
import org.example.model.UserInputReview;

public interface RateService {
    /**
     * add rate
     * takes rate object and adds it to database
     * @param rate rate (customer id,product id,description)
     * @Return nothing
     */
    Response AssignRateToProduct(UserInputReview rate);

    /**
     *  calculate product rate
     * product having list<Rate> and calculate rate of the product
     * and put the value in the transient parameter rate to be displayed in product details view
     * @param product product
     * @return nothing
     */
     Response setProductRate(Product product);
     double calculateRateOfProduct_(int productId);

}
