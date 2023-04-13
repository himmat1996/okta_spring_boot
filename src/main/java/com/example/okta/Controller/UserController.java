package com.example.okta.Controller;


import com.example.okta.request.UserRequest;
import com.example.okta.utils.CommonUtils;
import com.okta.sdk.client.Client;
import com.okta.sdk.resource.application.AppUser;
import com.okta.sdk.resource.application.AppUserList;
import com.okta.sdk.resource.user.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
        Map<String,Object> map = new HashMap<>();
        map.put("userId",CommonUtils.generateUUID());
        return UserBuilder.instance()
                .setProfileProperties(map)
                .setEmail(userRequest.getEmail())
                .setFirstName(userRequest.getName())
                .setLastName(userRequest.getLastName())
                .setPassword(userRequest.getPassword().toCharArray())
//                .setGroups(userRequest.getGroupId())
                .setActive(true)
                .setMobilePhone(userRequest.getMobileNumber())
                .setLogin(userRequest.getEmail())
                .buildAndCreate(clientBuilder);
    }

    @PostMapping("/temp/{userId}")
    public String generateTempPassword(@PathVariable("userId") String userId) {
        Client clientBuilder = CommonUtils.getClientBuilder();
        User user = clientBuilder.getUser(userId);
        return user.expirePasswordAndGetTemporaryPassword().getTempPassword();
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable("userId") String userId) {
        Client clientBuilder = CommonUtils.getClientBuilder();
        clientBuilder.getUser(userId).delete();
    }

    @PutMapping("/{userId}")
    public void updateUserById(@PathVariable("userId") String userId, @RequestBody UserRequest userRequest) {
        Client clientBuilder = CommonUtils.getClientBuilder();
        UserProfile userProfile = clientBuilder.instantiate(UserProfile.class);
        userProfile.setEmail(userRequest.getEmail());
        userProfile.setFirstName(userRequest.getName());
        userProfile.setLastName(userRequest.getLastName());
        userProfile.setMobilePhone(userRequest.getMobileNumber());
        User user = clientBuilder.getUser(userId);
        user.setProfile(userProfile);
        user.update();
    }
}
