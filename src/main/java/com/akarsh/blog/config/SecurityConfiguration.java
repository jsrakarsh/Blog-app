package com.akarsh.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
    	
    	http
    	
        // Removed CSRF protection since we're dealing with APIs (not a web client) and using Postman for testing
        .csrf(csrf -> csrf.disable())   

        // Ensuring all API endpoints require authentication
        .authorizeHttpRequests(auth ->  
            auth.anyRequest().authenticated()  
        )

        // Using HTTP Basic authentication to secure the API
        .httpBasic();   

    // Building and applying the security configuration
    return http.build();

    }
    
    
    // ADDING SPRING SECURITY DEPENDENCY ENABLE "FORM - BASED" AUTHENTICATION AUTOMATICALLY
    
    
    // USING  httpBasic(); helps in JSON based authentication
}
