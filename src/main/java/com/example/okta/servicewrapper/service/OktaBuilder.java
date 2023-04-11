package com.example.okta.servicewrapper.service;

import lombok.Builder;
import lombok.NonNull;
@Builder
public class OktaBuilder extends OktaAdapter {
    @NonNull
    private String serverUrl;
    @NonNull
    private String clientId;
    @NonNull
    private String clientSecret;

    public OktaBuilder(String serverUrl, String clientId, String clientSecret) {
        super(serverUrl, clientId, clientSecret);
    }
}