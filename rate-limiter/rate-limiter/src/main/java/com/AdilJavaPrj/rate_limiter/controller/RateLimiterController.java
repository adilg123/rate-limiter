package com.AdilJavaPrj.rate_limiter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.AdilJavaPrj.rate_limiter.model.RateLimitRequest;
import com.AdilJavaPrj.rate_limiter.service.RateLimiterService;

@RestController
@RequestMapping("/api")
public class RateLimiterController {

    @Autowired
    private RateLimiterService rateLimiterService;

    @PostMapping("/check")
    public String checkRateLimit(@RequestBody RateLimitRequest request) {
        boolean allowed = rateLimiterService.isAllowed(request.getUserId());
        return allowed ? "Allowed" : "Rate limit exceeded";
    }
}

