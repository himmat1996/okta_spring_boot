package com.example.okta.Controller;

import com.example.okta.utils.CommonUtils;
import com.okta.sdk.client.Client;
import com.okta.sdk.resource.application.ApplicationList;
import com.okta.sdk.resource.user.factor.UserFactorList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/factors")
public class FactorController {

    @GetMapping
    public ApplicationList getApplications() {
        Client clientBuilder = CommonUtils.getClientBuilder();

        return clientBuilder.listApplications();
    }

    @GetMapping("/{userId}")
    public UserFactorList getUserFactorList(@PathVariable("userId") String userId) {
        Client clientBuilder = CommonUtils.getClientBuilder();
        return clientBuilder.getUser(userId).listFactors();
    }
}
