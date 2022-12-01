package org.example.main;

import org.example.configuration.AppConfig;
import org.example.entity.Admin;
import org.example.enums.Gender;
import org.example.service.admin.AdminService;
import org.example.service.admin.AdminServiceImplementation;
import org.example.utility.DateUtils;
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


//        DateUtils dateUtils = new DateUtils();
//        AdminService adminService = context.getBean(AdminServiceImplementation.class);
//
//        Date date = dateUtils.convertStringToDate("1999-4-23");
//
//        Admin admin = new Admin("shahy", "admin",
//                "adminshahy@shop.com", "adm1nsh4h7",
//                Gender.female, date);
//
//        adminService.addAdmin(admin);

    }
}
