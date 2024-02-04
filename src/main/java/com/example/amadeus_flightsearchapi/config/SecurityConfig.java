package com.example.amadeus_flightsearchapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Apply CSRF protection selectively or disable it for stateless sessions
                .csrf().disable()

                // Ensure the application does not create HTTP sessions
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // Configure authorization rules
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/**").permitAll() // Allow all GET requests

                // Secure POST, PUT, PATCH, DELETE endpoints
                .antMatchers(HttpMethod.POST, "/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/**").authenticated()
                .antMatchers(HttpMethod.PATCH, "/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/**").authenticated()

                // Any other request must be authenticated
                .anyRequest().authenticated()

                // Optional: Configure form login and/or httpBasic for authentication
                .and()
                .httpBasic(); // or formLogin() as per your requirements
    }
}
