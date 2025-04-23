package com.AdilJavaPrj.rate_limiter.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

/**
 * RateLimiterService is responsible for enforcing rate limits on user requests.
 * It uses a fixed window algorithm to track and restrict the number of requests
 * a user can make within a given time frame.
 *
 * This implementation is thread-safe and suitable for single-instance services.
 */
@Service
public class RateLimiterService {

    // Maximum number of allowed requests per user within a time window
    @Value("${rate.limit.maxRequests}")
    private int MAX_REQUESTS;

    // Duration of the time window (in seconds) for rate limiting
    @Value("${rate.limit.windowSeconds}")
    private int WINDOW_SECONDS;

    // Thread-safe map to track request count and window start time for each user
    private final ConcurrentHashMap<String, UserRequestInfo> requestMap = new ConcurrentHashMap<>();

    /**
     * Checks if a user is allowed to make a request based on their rate limit status.
     *
     * @param userId the unique identifier for the user
     * @return true if the request is within the rate limit, false otherwise
     */
    public boolean isAllowed(String userId) {
        long currentTime = Instant.now().getEpochSecond();

        // Retrieve existing user info or initialize if user not seen before
        UserRequestInfo info = requestMap.getOrDefault(userId, new UserRequestInfo(0, currentTime));

        // If the current time has exceeded the window, reset the count and window start
        if (currentTime - info.windowStart >= WINDOW_SECONDS) {
            info = new UserRequestInfo(1, currentTime); // Start a new window with 1 request
        } else {
            // If the request count has reached the limit, deny access
            if (info.requestCount >= MAX_REQUESTS) return false;

            // Increment request count within the current window
            info.requestCount++;
        }

        // Update the user's request info in the map
        requestMap.put(userId, info);

        // Allow the request
        return true;
    }

    /**
     * Helper class to store request metadata for a user.
     * Contains the request count and the start timestamp of the current window.
     */
    private static class UserRequestInfo {
        int requestCount;   // Number of requests made in the current window
        long windowStart;   // Epoch time (in seconds) when the window started

        public UserRequestInfo(int requestCount, long windowStart) {
            this.requestCount = requestCount;
            this.windowStart = windowStart;
        }
    }
}
