package org.example.service;

import org.example.model.Response;

public class ValidationService {

  public Response validateAdminEmail(String email){//check admin email domain is shopify
      String domain;
      try {
          domain = email.substring(email.indexOf("@") + 1);
          domain = domain.substring(0, domain.indexOf("."));
          System.out.println("domain:: "+domain);
      }
      catch (Exception e){
          System.out.println(e.getStackTrace());
          return new Response("error occurred while processing your request",500,true);
      }
      boolean isInvalidEmailFormat=validateEmailFormat(email).isErrorOccurred();

      if(!domain.equalsIgnoreCase("shopify") || isInvalidEmailFormat ){
          System.out.println("in validation serviceee "+isInvalidEmailFormat);
          return new Response("Wrong domain",400,true,true);
      }
      return new Response("Done",200,false,false);
  }
  public Response validateEmailFormat(String email){
      // check if email doesn't exist in the database
      String domain;//checking if email ends with .com
      try {
          domain = email.substring(email.length()-4);
          System.out.println("domain:: "+domain);
      }
      catch (Exception e){
          System.out.println(e.getStackTrace());
          return new Response("error occurred while processing your request",500,true);
      }
      if (!domain.equals(".com")){
          return new Response("Wrong domain",400,true);
      }
      return new Response("Done",200,false);
  }
}
