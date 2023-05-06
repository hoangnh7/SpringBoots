package com.example.demo.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
    private String REALM ="Who are you?";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("WWW-Authenticate","Basic realm= "+ REALM);
        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 401 : "+authException.getMessage());
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName(REALM);
        super.afterPropertiesSet();
    }
}
