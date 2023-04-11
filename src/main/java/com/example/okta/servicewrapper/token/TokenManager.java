package com.example.okta.servicewrapper.token;


import com.example.okta.servicewrapper.request.ServiceRequest;
import com.example.okta.servicewrapper.response.ServiceTokenResponse;
import com.example.okta.servicewrapper.service.Config;
import com.example.okta.servicewrapper.service.FeignClientBuilder;

import java.util.HashMap;
import java.util.Map;


public class TokenManager {
    private final Config config;
    private final TokenService tokenService;
    private long expirationTime;
    private ServiceTokenResponse currentToken;

    public TokenManager(Config config) {
        this.config = config;
        tokenService = FeignClientBuilder.createClientWithBasicAuth(TokenService.class, config.getServerUrl(), config.getClientId(), config.getClientSecret());
        currentToken = grantToken();
    }

    public synchronized ServiceTokenResponse getAccessToken() {
        if (currentToken == null || tokenExpired()) {
            grantToken();
        }
        return currentToken;
    }

    private ServiceTokenResponse grantToken() {
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setGrant_type("client_credentials");
        serviceRequest.setScope("BackendScope");
        synchronized (this) {
            currentToken = tokenService.grantToken(serviceRequest);
            expirationTime = System.currentTimeMillis() * Long.parseLong(currentToken.getExpiresIn());
        }
        return currentToken;
    }

    private synchronized boolean tokenExpired() {
        return System.currentTimeMillis() >= expirationTime;
    }
}
