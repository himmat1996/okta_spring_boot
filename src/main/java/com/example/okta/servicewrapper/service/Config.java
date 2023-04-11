package com.example.okta.servicewrapper.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Config {
    private String serverUrl;
    private String clientId;
    private String clientSecret;
}
