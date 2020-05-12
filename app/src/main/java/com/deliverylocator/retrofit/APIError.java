package com.deliverylocator.retrofit;

/**
 * Created by HP on 2/27/2018.
 */

public class APIError {

    private final static String TAG = "APIError";
    private int statusCode;
    private String message;

    public APIError() {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
