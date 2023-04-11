package com.example.okta.servicewrapper.header;

import com.example.okta.servicewrapper.token.TokenManager;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import static com.example.okta.servicewrapper.constant.AdapterConstant.*;


public class BearerAuthServiceInterceptor implements RequestInterceptor {
    private TokenManager tokenManager;

    public BearerAuthServiceInterceptor(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header(HEADER_AUTHORIZATION, TOKEN_PREFIX.concat(tokenManager.getAccessToken().getToken()));
    }
}
