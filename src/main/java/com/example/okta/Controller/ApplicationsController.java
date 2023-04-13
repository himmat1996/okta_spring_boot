package com.example.okta.Controller;

import com.example.okta.utils.CommonUtils;
import com.okta.jwt.AccessTokenVerifier;
import com.okta.jwt.Jwt;
import com.okta.jwt.JwtVerificationException;
import com.okta.jwt.JwtVerifiers;
import com.okta.sdk.client.Client;
import com.okta.sdk.resource.application.AppUser;
import com.okta.sdk.resource.application.AppUserList;
import com.okta.sdk.resource.application.ApplicationList;

import org.springframework.web.bind.annotation.*;

import java.time.Duration;
@RestController
@RequestMapping("/applications")
public class ApplicationsController {


    @GetMapping
    public ApplicationList getApplications() {
        Client clientBuilder = CommonUtils.getClientBuilder();
        return clientBuilder.listApplications();
    }
    @GetMapping("/{applicationId}")
    public AppUserList getUsersByAppId(@PathVariable("applicationId") String applicationId) {
        Client clientBuilder = CommonUtils.getClientBuilder();
        return clientBuilder.getApplication(applicationId).listApplicationUsers();
    }
    @PostMapping("/users/{applicationId}")
    public void assignUserToApplication(@PathVariable("applicationId") String applicationId, @RequestParam("userId") String userId) {
        Client clientBuilder = CommonUtils.getClientBuilder();
        AppUser appUser = clientBuilder.instantiate(AppUser.class);
        appUser.setId(userId);
        clientBuilder.getApplication(applicationId).assignUserToApplication(appUser);
    }

    @GetMapping("/verify")
    public void verify() throws JwtVerificationException {
        String token = "eyJraWQiOiJLaExrN1N2VGV2RnAxRzdaRjhFMjFFaHpxdGEzbGhiQm9TSVhuOWFxc0NzIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULkg2Qmc5SUtQZmoyZV9sWWlpQ2s2RF91Mlk4bkEyWTdzSU1UWF9HMmsxQ1kiLCJpc3MiOiJodHRwczovL3RyaWFsLTEwNjY4NDAub2t0YS5jb20vb2F1dGgyL2RlZmF1bHQiLCJhdWQiOiJhcGk6Ly9kZWZhdWx0IiwiaWF0IjoxNjgwODY5Mjk4LCJleHAiOjE2ODA4NzI4OTgsImNpZCI6IjBvYTR3bnhqdGdLVWNiYllwNjk3Iiwic2NwIjpbIkhpbW1hdCJdLCJzdWIiOiIwb2E0d254anRnS1VjYmJZcDY5NyJ9.tX61SIcCXoqNJNZWXloXhcOhVS6XXTA9QckozMrRww6jNOyhbKh3jM2bjaZDnJSmqJsCHihwoJb1Cizp4Rl6-cLh99WrGck8Hs9C_OvvMQC1CEu4slxL7FX4fD4R_Nl0I0GRyeV_jGYOJGn_UBLXhMGOhaQ2xd8PjR5mhPCWZgErFe_lXhnew5_wyYRBvvmTS3no381m7NFhEJGqQUWwgworjfmOkz062-MOMNxI9hbGxcUgMka7djHoTRqhqI8wuAu_Kscp-HsRjTl9GgiCbyIc-F3zFMAb9Hwc1unGNB14sqB8jpzxL6-lSH197p1UqmqVYoKzm1sUK7tLyC8xAQ";
        AccessTokenVerifier jwtVerifier = JwtVerifiers.accessTokenVerifierBuilder()
                .setIssuer("https://trial-1066840.okta.com/oauth2/default")
                .setAudience("api://default")// defaults to 'api://default'
                .setConnectionTimeout(Duration.ofSeconds(1))    // defaults to 1s
                .setRetryMaxAttempts(2)                     // defaults to 2
                .setRetryMaxElapsed(Duration.ofSeconds(10)) // defaults to 10s
                .build();
        Jwt jwt = jwtVerifier.decode(token);
        System.out.println(jwt);
    }

}
