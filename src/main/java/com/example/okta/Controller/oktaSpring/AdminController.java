package com.example.okta.Controller.oktaSpring;

import com.okta.sdk.client.Client;
import com.okta.sdk.resource.user.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    public Client client;

    @GetMapping("/spring/users")
    public UserList getUsers() {
        return client.listUsers();
    }
}