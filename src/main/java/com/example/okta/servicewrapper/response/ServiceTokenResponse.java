package com.example.okta.servicewrapper.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ServiceTokenResponse {
    @SerializedName("access_token")
    protected String token;
    @SerializedName("token_type")
    protected String tokenType;
    @SerializedName("expires_in")
    protected String expiresIn;
    protected String scope;
}
