package com.AdilJavaPrj.rate_limiter.model;

public class RateLimitRequest {
    private String userId;

    public RateLimitRequest() {}

    public RateLimitRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
