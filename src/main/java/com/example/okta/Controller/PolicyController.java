package com.example.okta.Controller;

import com.example.okta.utils.CommonUtils;
import com.okta.sdk.client.Client;
import com.okta.sdk.resource.policy.PolicyList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    @GetMapping
    public PolicyList getPolicy() {
        Client clientBuilder = CommonUtils.getClientBuilder();
        return clientBuilder.listPolicies("ACCESS_POLICY");
    }
}
