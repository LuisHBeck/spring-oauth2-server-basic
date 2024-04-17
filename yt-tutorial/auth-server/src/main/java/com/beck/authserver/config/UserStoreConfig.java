package com.beck.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserStoreConfig {

    // user detail service is used to consult users in spring security authentication
    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsManager =  new InMemoryUserDetailsManager(); // memory storage

        // crete user to our memory storage
        userDetailsManager.createUser(
                User.withUsername("user")
                        .password("{noop}password")
                        .roles("USER")
                        .build()
        );

        return userDetailsManager;
    }
}
