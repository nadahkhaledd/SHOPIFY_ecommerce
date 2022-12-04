package org.example.service;

import org.example.model.Response;

public class ValidationService {

  public Response validateAdminEmail(String email){
      String domain= email.substring(email.indexOf("@") + 1)
              .substring(0, email.indexOf("."));
      if(domain.equalsIgnoreCase("shopify")){
          return new Response("Wrong domain",400);
      }
      // check if email doesn't exist in the database

      return new Response("Done",200);
  }
}
