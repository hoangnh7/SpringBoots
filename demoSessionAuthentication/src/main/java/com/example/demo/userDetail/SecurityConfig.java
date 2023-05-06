package com.example.demo.userDetail;

import com.example.demo.userDetail.ApiAuthorizationFilter;
import com.example.demo.userDetail.CustomAuthenticationEntryPoint;
import com.example.demo.userDetail.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
//    @Bean
//// authentication
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//    UserDetails admin = User.withUsername("hach")
//            .password(encoder.encode("hacheery"))
//            .roles("ADMIN")
//            .build();
//    UserDetails user = User.withUsername("user")
//            .password(encoder.encode("pwd1"))
//            .roles("USER")
//            .build();
//    return new InMemoryUserDetailsManager(admin, user);
//}
    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private ApiAuthorizationFilter apiAuthorizationFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(new RegexRequestMatcher("/register","POST")).permitAll() // Api đăng nhập đăng kí không cần kiểm tra xác thực
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/authenticate","POST").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)

                .and()
                .logout()
                .logoutUrl("/logout-test")
                .logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)))
                .and()
                .addFilterBefore(apiAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
