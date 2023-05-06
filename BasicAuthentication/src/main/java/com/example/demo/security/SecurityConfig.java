package com.example.demo.security;

import com.example.demo.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfig  {
    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
             http
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()// với endpoint /hello thì sẽ được cho qua
                .exceptionHandling()
                .and()
                .httpBasic()
                .authenticationEntryPoint(myAuthenticationEntryPoint);

             return http.build();

    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/hello").permitAll()
//                .and().logout()// với endpoint /hello thì sẽ được cho qua
//                .and()
//                .authorizeHttpRequests()
//                .requestMatchers("/profile").hasRole("ADMIN") // với endpoint /customer/** sẽ yêu cầu authenticate
//                .and().formLogin() // trả về page login nếu chưa authenticate
//                .and().build();
//    }
}
