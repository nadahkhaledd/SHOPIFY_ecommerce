package org.example.model;

import jakarta.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class RemoveUserFields {

    @NotNull
    @NotBlank
    private String userEmail;

    @NotNull
    private String userType;

    public RemoveUserFields() {
    }

    public RemoveUserFields(int userID, String userEmail, String userType) {
        this.userEmail = userEmail;
        this.userType = userType;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
