package com.example.okta.servicewrapper.header;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import static com.example.okta.servicewrapper.constant.AdapterConstant.API_KEY;
import static com.example.okta.servicewrapper.constant.AdapterConstant.HEADER_AUTHORIZATION;

public class BearerAuthUserInterceptor implements RequestInterceptor {
    private String token;


    public BearerAuthUserInterceptor(String token) {
        this.token = token;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header(HEADER_AUTHORIZATION, this.token);
    }
}
