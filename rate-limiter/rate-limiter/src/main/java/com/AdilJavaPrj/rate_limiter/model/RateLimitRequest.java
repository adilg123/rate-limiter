package com.AdilJavaPrj.rate_limiter.model;

/**
 * This class represents a request payload for the rate limiter API.
 * It encapsulates a single field: userId, which is used to identify the client making requests.
 */
public class RateLimitRequest {

    // The userId of the client making the request
    private String userId;

    /**
     * Default constructor required for deserialization (e.g., by Spring or Jackson).
     */
    public RateLimitRequest() {}

    /**
     * Parameterized constructor to create a RateLimitRequest with a specific userId.
     *
     * @param userId the identifier of the user
     */
    public RateLimitRequest(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the userId of the request.
     *
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the userId for this request.
     *
     * @param userId the identifier to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
