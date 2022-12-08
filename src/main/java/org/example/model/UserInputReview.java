package org.example.model;

public class UserInputReview {
    private int rateValue;
    private int userId;
    private int ProductId;
    private String review;


    public UserInputReview(int rateValue, int userId, int productId, String review) {
        this.rateValue = rateValue;
        this.userId = userId;
        this.ProductId = productId;
        this.review = review;

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

    @Override
    public String toString() {
        return "UserInputReview{" +
                "rate=" + rateValue +
                ", userId=" + userId +
                ", ProductId=" + ProductId +
                ", review='" + review + '\'' +
                '}';
    }
}
