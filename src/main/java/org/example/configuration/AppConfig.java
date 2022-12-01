package org.example.configuration;

import org.example.entity.*;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "org.example")
public class AppConfig {

    @Bean
    ViewResolver viewResolver(){
        return new InternalResourceViewResolver("/WEB-INF/site/views/",".jsp");
    }

    @Bean
    SessionFactory getSessionFactory(){
        return new org.hibernate.cfg.Configuration().configure("hibernate.cfg.xml").
                addAnnotatedClass(Admin.class).addAnnotatedClass(Customer.class).addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class).addAnnotatedClass(Product.class)
                .addAnnotatedClass(ShoppingCartProducts.class).addAnnotatedClass(Rate.class)
                .buildSessionFactory();
    }


}
