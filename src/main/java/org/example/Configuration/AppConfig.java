package org.example.Configuration;

import org.example.Entity.Address;
import org.example.Entity.Admin;
import org.example.Entity.Customer;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "org.example")
public class AppConfig {
    @Bean
    ViewResolver viewResolver(){
        return new InternalResourceViewResolver("/WEB-INF/views/",".jsp");
    }

  @Bean
 SessionFactory getSessionFactory(){
     return new org.hibernate.cfg.Configuration().configure("hibernate.cfg.xml").
             addAnnotatedClass(Admin.class).addAnnotatedClass(Customer.class).addAnnotatedClass(Address.class)
             .buildSessionFactory();
 }

}
