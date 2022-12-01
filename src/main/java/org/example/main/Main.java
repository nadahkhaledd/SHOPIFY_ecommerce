package org.example.main;

import org.example.configuration.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
       // ProductService productService=context.getBean(ProductServiceImpl.class);
       // RateServiceImpl rateService=context.getBean(RateServiceImpl.class);
       /// System.out.println( rateService.getRates(1));
       // System.out.println(rateService.calculateRateOfProduct(1));
      // rateService.AssignRateToProduct(new Rate("dummy",2,2,);

    }
}
