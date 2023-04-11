package com.example.okta.utils;

import com.example.okta.servicewrapper.service.OktaBuilder;
import com.okta.sdk.authc.credentials.TokenClientCredentials;
import com.okta.sdk.client.Client;
import com.okta.sdk.client.Clients;

import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;

public class CommonUtils {
    public static Client getClientBuilder(){
        return Clients.builder()
                .setClientId("0oa4wnxjtgKUcbbYp697")
                .setScopes(new HashSet<>(Arrays.asList("okta.users.manage", "okta.apps.manage", "okta.groups.manage")))
                .setClientCredentials(new TokenClientCredentials("00X7ElCNrNWThRPG-_9PAYLU4xQY6DdKtGSf_Ky3ph"))
                .setOrgUrl("https://trial-1066840.okta.com")
                .build();
    }
    public  static OktaBuilder oktaBuilder;
    public static OktaBuilder getOktaBuilder(){
        return OktaBuilder.builder()
                .clientId("0oa4xwxe0lpPhCjWj697")
                .clientSecret("ZhuHAw8ZgwK4ajk5lQ3w0JBwlBQru09PLuOzeiHi")
                .serverUrl("https://trial-1825427.okta.com/oauth2/default")
                .build();
    }
    public static String encodeInput(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }
}
