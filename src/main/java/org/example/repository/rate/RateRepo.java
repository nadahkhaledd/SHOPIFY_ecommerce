package org.example.repository.rate;

import org.example.entity.Rate;
import org.example.model.Response;

public interface RateRepo {
    /**
     * add rate
     * takes rate object and adds it to database
     * @param rate rate (customer id,product id,description)
     * @Return nothing
     */
    Response addRate(Rate rate);

    /** calculate product rate (avg of users' rate to this product)
     * @param productId id of the product
     * @return rate of the product
     */
    Response<Double> calculateRateOfProduct(int productId);
}
