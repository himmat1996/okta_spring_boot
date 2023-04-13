package com.example.okta.servicewrapper.header;

import com.example.okta.utils.CommonUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class BasicAuthInterceptor implements RequestInterceptor {
    private String clientId;
    private String clientSecret;

    public BasicAuthInterceptor(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", "Basic " + CommonUtils.encodeInput(this.clientId + ":" + this.clientSecret));
//        template.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
    }
}
