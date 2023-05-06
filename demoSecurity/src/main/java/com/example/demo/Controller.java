package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/hello")
    public ResponseEntity<?> getGreeting(){
        return ResponseEntity.ok("Hello world");
    }
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(){
        return ResponseEntity.ok("Profile");
    }
    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser() {
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication());
    }
}
