package com.example.okta.Controller;

import com.example.okta.request.FactorRequest;
import com.example.okta.utils.CommonUtils;
import com.okta.sdk.client.Client;
import com.okta.sdk.resource.user.User;
import com.okta.sdk.resource.user.factor.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/factors")
public class FactorController {

    @GetMapping("/{userId}")
    public UserFactorList getUserFactorList(@PathVariable("userId") String userId) {
        Client clientBuilder = CommonUtils.getClientBuilder();
        return clientBuilder.getUser(userId).listFactors();
    }

    @PutMapping("/{userId}")
    public UserFactorList updateFactor(@PathVariable("userId") String userId, @RequestBody FactorRequest factorRequest) {
        Client clientBuilder = CommonUtils.getClientBuilder();
        User user = clientBuilder.getUser(userId);
        UserFactor userFactor = clientBuilder.instantiate(UserFactor.class);
        userFactor.setFactorType(factorRequest.getFactorType());
        userFactor.setProvider(FactorProvider.OKTA);
        userFactor.setVerify(clientBuilder.instantiate(VerifyFactorRequest.class));
        user.enrollFactor(userFactor);
        user.update();
        return clientBuilder.getUser(userId).listFactors();
    }

    @GetMapping("/{userId}/{factorId}")
    public UserFactor getFactorById(@PathVariable("userId") String userId, @PathVariable("factorId") String factorId) {
        Client clientBuilder = CommonUtils.getClientBuilder();
        User user = clientBuilder.getUser(userId);
        return user.getFactor(factorId);
    }

    @DeleteMapping("/{userId}/{factorId}")
    public void deleteFactorById(@PathVariable("userId") String userId, @PathVariable("factorId") String factorId) {
        Client clientBuilder = CommonUtils.getClientBuilder();
        User user = clientBuilder.getUser(userId);
        user.deleteFactor(factorId);
    }
}
