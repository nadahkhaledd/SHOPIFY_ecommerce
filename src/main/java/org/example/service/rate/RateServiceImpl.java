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
    @Override
    public void AssignRateToProduct(Rate rate){
        rateRepository.addRate(rate);
    }

    @Override
    public double calculateRateOfProduct(int productId) {
       return rateRepository.calculateRateOfProduct(productId);
    }
  /*  public List<Float> getRates(int productId){
        return rateRepository.getRates(productId);
    }*/
}
