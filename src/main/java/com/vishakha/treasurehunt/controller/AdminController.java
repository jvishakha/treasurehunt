package com.vishakha.treasurehunt.controller;

import com.vishakha.treasurehunt.model.User;
import com.vishakha.treasurehunt.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class AdminController {

    @Autowired
    private RegistrationService registrationService;

    @Value("${admin.token}")
    private String adminToken;

    @PostMapping(value="/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String userRegistration(@Valid @RequestBody User user, @RequestHeader (name = "token") String token){
        if(token.equals(adminToken)) {
            return registrationService.register(user);
        }
        else
            return "Not authorized";
    }

    @PostMapping(value="/token", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String tokenGeneration(@Valid @RequestBody User user, @RequestHeader (name = "token") String token){
        if(token.equals(adminToken)) {
            return registrationService.createToken(user);
        }
        else
            return "Not authorized";
    }
}
