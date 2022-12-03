package org.example.service.rate;

import org.example.entity.Rate;
import org.example.repository.rate.RateRepo;
import org.example.repository.rate.RateRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateServiceImpl implements RateService {
    private RateRepo rateRepository;
    @Autowired
    public RateServiceImpl(RateRepo rateRepository) {
        this.rateRepository = rateRepository;
    }
    public RateServiceImpl(){}
    @Override
    public void AssignRateToProduct(Rate rate){
        rateRepository.addRate(rate);
    }

    @Override
    public double calculateRateOfProduct(int productId) {
        double rate=rateRepository.calculateRateOfProduct(productId);
       // System.out.println("in rate service "+rate);
        return rate;
    }
  /*  public List<Float> getRates(int productId){
        return rateRepository.getRates(productId);
    }*/
}
