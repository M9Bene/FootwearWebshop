package com.flashforward.backendspring.security;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final Filter JWTAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                // disable csrf because of the usage of another token mechanism.
                .csrf()
                .disable()
                .authorizeHttpRequests()
                // whitelist for endpoints which we don't want to be an authenticated endpoint
                .requestMatchers("")
                .permitAll()
                // except whitelist, any other endpoint should be authenticated
                .anyRequest()
                .authenticated()
                .and()
                // We don't store the session/auth state because we use oncePerRequestFilter in JWTAuthenticationFilter
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // add auth Provider + JWTAuthenticationFilter
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(JWTAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
