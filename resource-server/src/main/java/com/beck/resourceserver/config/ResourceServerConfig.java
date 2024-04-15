package com.beck.resourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig {

    // apply config to request authentication to all requests made for /articles endpoint
    //  oauth2ResourceServer() method configure oauth server connection based on application.yaml properties

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/articles/**")
                .authorizeHttpRequests(req -> req
                        .anyRequest()
                        .hasAuthority("SCOPE_articles.read"))
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(withDefaults()));
        return http.build();
    }

}