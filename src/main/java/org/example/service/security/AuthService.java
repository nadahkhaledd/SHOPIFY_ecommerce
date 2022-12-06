package org.example.service.security;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.example.entity.User;
import org.example.repository.security.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthService {
    @Autowired
    AuthRepo authRepo;

    public User login(final String email, final String password) {
        final User user = this.authRepo.checkLoginCredential(email, password);
        if (user != null) {
            return user;
        }
        return null;
    }

    public boolean register(final User user) {
        return this.authRepo.register(user);
    }


    public void sendVerificationEmail(final String sendTo) {
        final Email from = new Email("prd@storkstores.com");
        final Email to = new Email(sendTo);
        final String subject = "please verify your account";
        final Content content = new Content("text/plain", "http://localhost:8080/e-commerce/activate/"+sendTo);
        final Mail mail = new Mail(from, subject, to, content);
        final SendGrid sg = new SendGrid("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");
        final Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            final Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean verifyEmail(final String email) {
        return this.authRepo.verifyEmail(email);
     }


    public boolean checkIfActivated(int id) {
        return authRepo.checkIfActivated(id);
    }
}
