package com.example.okta.servicewrapper.request;

import lombok.Data;

@Data
public class ServiceRequest {
    public String grant_type;
    public String scope;
}
