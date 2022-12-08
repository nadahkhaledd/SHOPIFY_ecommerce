package org.example.service;

import org.example.model.Response;
import org.example.service.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationService {
  public Response validateAdminEmail(String email){//check admin email domain is shopify
      String domain;
      String regex = "[a-z0-9]+@shopify.com";
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(email);
      if(!matcher.matches()){
          System.out.println("in validation serviceee ");
          return new Response("Wrong domain",400,true,true);
      }
      return new Response("Done",200,false,false);
  }

}
