package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private AuthenticationManager authenticationManager;
    @GetMapping("/hello")
    public ResponseEntity<?> getProfile() {
        return ResponseEntity.ok("Hello World");
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser( @RequestBody CreateUserReq req) {
        UserDto result = userService.createUser(req);
        return ResponseEntity.ok(result);
    }
}
