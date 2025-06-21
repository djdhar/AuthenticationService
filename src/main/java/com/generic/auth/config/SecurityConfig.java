package com.generic.auth.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // disable CSRF for Postman testing
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/api/auth/signup", "/api/auth/login").permitAll()  // allow signup without auth
                        .anyExchange().authenticated()                // everything else requires auth
                )
                .httpBasic().disable()
                .formLogin().disable()
                .build();
    }
}

