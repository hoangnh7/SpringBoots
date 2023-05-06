package com.example.demo.controller;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.request.CreateUserReq;
import com.example.demo.security.AuthenticateReq;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public ResponseEntity<?> getProfile() {
        return ResponseEntity.ok("Hello World");
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserReq req) {
        UserDto result = userService.createUser(req);
        return ResponseEntity.ok(result);
    }
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticateReq req, HttpSession session) {
        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );
        System.out.println(req.getEmail());
        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute("TECHMASTER_SESSION", authentication.getName());

        return ResponseEntity.ok("Đăng nhập thành công");
    }
    @GetMapping("/logout-test")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();

        return ResponseEntity.ok("Logout successfully");
    }
}
