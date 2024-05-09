package com.example.demo.config.user.config.controller;

import com.example.demo.config.authentication.AuthenticationService;
import com.example.demo.config.user.config.dto.RegisteredUser;
import com.example.demo.config.user.config.dto.SaveUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping
    public ResponseEntity<RegisteredUser> registerOne (@RequestBody @Valid SaveUser newUser){
        RegisteredUser registeredUser = authenticationService.registerOneCustomer(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
}
