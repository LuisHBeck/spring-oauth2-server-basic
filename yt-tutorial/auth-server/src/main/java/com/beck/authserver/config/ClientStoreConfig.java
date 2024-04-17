package com.beck.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.UUID;

import static org.springframework.security.oauth2.core.AuthorizationGrantType.*;
import static org.springframework.security.oauth2.core.ClientAuthenticationMethod.CLIENT_SECRET_BASIC;
import static org.springframework.security.oauth2.core.oidc.OidcScopes.OPENID;
import static org.springframework.security.oauth2.core.oidc.OidcScopes.PROFILE;

@Configuration
public class ClientStoreConfig {

    // RegisteredClientRepository have all needed to register oauth2 client in auth-server
    @Bean
    public RegisteredClientRepository registeredClientRepository() {

        // registering a client
        var registeredClient = RegisteredClient
                .withId(UUID.randomUUID().toString())
                .clientId("client-server") // like 'name'
                .clientSecret("{noop}secret") // like password
                .clientAuthenticationMethod(CLIENT_SECRET_BASIC) // header with client id and secret
                .authorizationGrantType(AUTHORIZATION_CODE)
                .authorizationGrantType(REFRESH_TOKEN)
                .authorizationGrantType(CLIENT_CREDENTIALS)
                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/client-server-oidc")
                .scope(OPENID)
                .scope(PROFILE)
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build()) // show consent screen with scopes
                .build();

        return new InMemoryRegisteredClientRepository(registeredClient);
    }

}
