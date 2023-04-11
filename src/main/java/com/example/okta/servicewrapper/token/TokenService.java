package com.example.okta.servicewrapper.token;

import com.example.okta.servicewrapper.request.ServiceRequest;
import com.example.okta.servicewrapper.response.ServiceTokenResponse;
import feign.Headers;
import feign.RequestLine;


public interface TokenService {
    @RequestLine("POST /v1/token")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    ServiceTokenResponse grantToken(ServiceRequest request);
}
