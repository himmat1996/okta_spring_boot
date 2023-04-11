package com.example.okta.servicewrapper.service;

import com.example.okta.servicewrapper.exception.CustomErrorDecoder;

import com.example.okta.servicewrapper.header.BasicAuthInterceptor;
import com.example.okta.servicewrapper.header.BearerAuthServiceInterceptor;
import com.example.okta.servicewrapper.header.BearerAuthUserInterceptor;
import com.example.okta.servicewrapper.token.TokenManager;
import feign.Feign;
import feign.Logger;
import feign.form.FormEncoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.slf4j.Slf4jLogger;

public class FeignClientBuilder {

    public static <T> T createClient(Class<T> type, String uri) {
        return Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(type))
                .target(type, uri);
    }

    public static <T> T createClientWithBasicAuth(Class<T> type, String uri, String clientId, String clientSecret) {
        return Feign.builder()
                .requestInterceptor(new BasicAuthInterceptor(clientId, clientSecret ))
                .encoder(new FormEncoder())
                .errorDecoder(new CustomErrorDecoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(type))
                .logLevel(Logger.Level.HEADERS)
                .target(type, uri);
    }

    public static <T> T createClientWithBearerAuth(Class<T> type, String uri, TokenManager tokenManager) {
        return Feign.builder()
                .requestInterceptor(new BearerAuthServiceInterceptor(tokenManager))
                .encoder(new GsonEncoder())
                .errorDecoder(new CustomErrorDecoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(type))
                .logLevel(Logger.Level.HEADERS)
                .target(type, uri);
    }

    public static <T> T createClientWithBearerAuthForUser(Class<T> type, String uri, String token) {
        return Feign.builder()
                .requestInterceptor(new BearerAuthUserInterceptor(token))
                .encoder(new GsonEncoder())
                .errorDecoder(new CustomErrorDecoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(type))
                .logLevel(Logger.Level.HEADERS)
                .target(type, uri);
    }
}
