package org.example.entity;
import org.example.enums.Gender;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("0")
public class Admin extends  User{


    public Admin() {
    }

    public Admin(String firstname, String lastname, String email, String password, Gender gender, Date dateOfBirth) {
        super(firstname, lastname, email, password, gender, dateOfBirth);
    }

}
