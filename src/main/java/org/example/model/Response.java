package org.example.model;

public class Response<T> {
    private String message;
    private int statusCode;
    private boolean errorOccurred;

    private T objectToBeReturned;

    public T getObjectToBeReturned() {
        return objectToBeReturned;
    }

    public void setObjectToBeReturned(T objectToBeReturned) {
        this.objectToBeReturned = objectToBeReturned;
    }

    public boolean isErrorOccurred() {
        return errorOccurred;
    }

    public void setErrorOccurred(boolean errorOccurred) {
        this.errorOccurred = errorOccurred;
    }

    public Response(String message, int statusCode, boolean errorOccurred, T objectToBeReturned) {
        this.message = message;
        this.statusCode = statusCode;
        this.errorOccurred = errorOccurred;
        this.objectToBeReturned = objectToBeReturned;
    }

    public Response(String message, int statusCode, boolean errorOccurred) {
        this.message = message;
        this.statusCode = statusCode;
        this.errorOccurred=errorOccurred;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                ", statusCode=" + statusCode +
                ", errorOccurred=" + errorOccurred +
                '}';
    }
}
