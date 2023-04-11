package com.example.okta.Controller;

//import com.okta.sdk.authc.credentials.TokenClientCredentials;
//import com.okta.sdk.client.Client;
//import com.okta.sdk.client.Clients;
//import com.okta.sdk.resource.application.ApplicationBuilder;
//import com.okta.sdk.resource.group.rule.GroupRuleList;
//import com.okta.sdk.resource.user.UserBuilder;
//import com.okta.sdk.resource.user.UserList;

import com.example.okta.request.UserRequest;
import com.example.okta.servicewrapper.service.OktaBuilder;
import com.example.okta.utils.CommonUtils;
import com.okta.sdk.client.Client;
import com.okta.sdk.resource.user.User;
import com.okta.sdk.resource.user.UserBuilder;
import com.okta.sdk.resource.user.UserList;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @GetMapping("/list")
    public UserList getUsers() {
        Client clientBuilder = CommonUtils.getClientBuilder();
        return clientBuilder.listUsers();
    }

    @GetMapping("/check")
    public UserList getCheckUsers(@RequestParam("email") String email) {
        Client clientBuilder = CommonUtils.getClientBuilder();
        return clientBuilder.listUsers(email, null, null, null, null);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable("userId") String userId) {
        Client clientBuilder = CommonUtils.getClientBuilder();
        return clientBuilder.getUser(userId);
    }

    @PostMapping("/create")
    public User getCreateUsers(@RequestBody UserRequest userRequest) {
        Client clientBuilder = CommonUtils.getClientBuilder();

        return UserBuilder.instance()
                .setEmail(userRequest.getEmail())
                .setFirstName(userRequest.getName())
                .setLastName(userRequest.getLastName())
                .setPassword(userRequest.getPassword().toCharArray())
                .setGroups(userRequest.getGroupId())
                .setActive(true)
                .setMobilePhone(userRequest.getMobileNumber())
                .setLogin(userRequest.getEmail())
                .buildAndCreate(clientBuilder);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable("userId") String userId) {
        Client clientBuilder = CommonUtils.getClientBuilder();
        clientBuilder.getUser(userId).delete();
    }


    @GetMapping("/service/list")
    public void getServiceUsers() {
        OktaBuilder oktaBuilder = CommonUtils.getOktaBuilder();
        System.out.println(oktaBuilder);
    }
}
