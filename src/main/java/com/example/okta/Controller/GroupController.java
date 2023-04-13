package com.example.okta.Controller;


import com.example.okta.request.GroupRequest;
import com.example.okta.utils.CommonUtils;
import com.okta.sdk.authc.credentials.TokenClientCredentials;
import com.okta.sdk.client.Client;
import com.okta.sdk.client.Clients;
import com.okta.sdk.resource.group.Group;
import com.okta.sdk.resource.group.GroupBuilder;
import com.okta.sdk.resource.group.GroupList;
import com.okta.sdk.resource.group.rule.GroupRule;
import com.okta.sdk.resource.group.rule.GroupRuleBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@RestController
@RequestMapping(value = "/groups")
public class GroupController {

    @GetMapping
    public GroupList getGroups() {
        Client clientBuilder = CommonUtils.getClientBuilder();
        return clientBuilder.listGroups();
    }

    @PostMapping
    public GroupList getCreateGroups(@RequestBody GroupRequest groupRequest) {
        Client clientBuilder = CommonUtils.getClientBuilder();
        GroupBuilder.instance().setName("HimmatGroup").buildAndCreate(clientBuilder);
        return clientBuilder.listGroups();
    }


    @GetMapping("/{id}")
    public Group getCreateGroups(@PathVariable(name = "id") String id) {
        Client clientBuilder = CommonUtils.getClientBuilder();
        Group group = null;
        try {
            GroupList groups = clientBuilder.listGroups("HimmatGroup", null, null);
            Optional<Group> optional = groups.stream().findFirst();
            group = optional.get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return group;
    }
    @PostMapping("/{groupId}")
    public void assignToGroup(@PathVariable(name = "groupId") String groupId,@RequestParam(name = "userId") String userId) {
        Client clientBuilder = CommonUtils.getClientBuilder();

        GroupRule group=  GroupRuleBuilder.instance().setAssignUserToGroups(Collections.singletonList(userId)).buildAndCreate(clientBuilder);

//
//
//        Group group = GroupBuilder.instance()
//                .setName("a-group-name")
//                .setDescription("Example Group")
//                .buildAndCreate(g);
//
//// assign user to group
//        groupApi.assignUserToGroup(group.getId(), user.getId());
    }


}
