package org.example.model;

public class RemoveUserFields {
    private int userID;
    private String userEmail;
    private String userType;

    public RemoveUserFields() {
    }

    public RemoveUserFields(int userID, String userEmail, String userType) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.userType = userType;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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
