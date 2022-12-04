package org.example.model;

public class UserInputReview {
    private int rateValue;
    private int userId;
    private int ProductId;
    private String review;
    private String userEmail;

    public UserInputReview(int rateValue, int userId, int productId, String review, String userEmail) {
        this.rateValue = rateValue;
        this.userId = userId;
        this.ProductId = productId;
        this.review = review;
        this.userEmail = userEmail;
    }

    public int getRate() {
        return rateValue;
    }

    public void setRate(int rate) {
        this.rateValue = rate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "UserInputReview{" +
                "rate=" + rateValue +
                ", userId=" + userId +
                ", ProductId=" + ProductId +
                ", review='" + review + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
