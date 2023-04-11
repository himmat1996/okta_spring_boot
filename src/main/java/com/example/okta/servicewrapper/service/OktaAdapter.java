package com.example.okta.servicewrapper.service;


import com.example.okta.servicewrapper.resources.AccessControlResources;
import com.example.okta.servicewrapper.token.TokenManager;

public class OktaAdapter {
    private Config config;
    private TokenManager tokenManager;

    public OktaAdapter(String serverUrl, String clientId, String clientSecret) {
        this.config = new Config(serverUrl, clientId, clientSecret);
        tokenManager = new TokenManager(config);
    }

    public AccessControlResources accessControl() {
        return FeignClientBuilder.createClientWithBearerAuth(AccessControlResources.class, config.getServerUrl(), tokenManager);
    }

}