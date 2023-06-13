package com.LunaProject.LibrarySearch.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

@Configuration
public class SecurityConfiguration {
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //protect endpoint /api/orders
        http.authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers("/api/orders/**")
                                .authenticated()
                                .anyRequest().permitAll())//allow other endpoints to access without authentication
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(Customizer.withDefaults()));
        //configure the resource server
        //Customizer is an interface that allows you to customize
        //and configure various aspects of the security configuration.

        // + CORS filters
        http.cors(Customizer.withDefaults());

        // + content negotiation strategy
        http.setSharedObject(ContentNegotiationStrategy.class, new HeaderContentNegotiationStrategy());

        // + non-empty response body for 401 (more friendly)
        Okta.configureResourceServer401ResponseBody(http);

        // we are not using Cookies for session tracking >> disable CSRF
        http.csrf(csrf->csrf.disable());
        // CSRF is an attack where a malicious website tricks a user's browser
        // into making unintended requests to a target website on which the user is authenticated

        return http.build();
    }
}

// The HttpSecurity object represents the main configuration entry point
// for setting up security-related behavior in your application.
// It allows you to configure various aspects of security,
// such as authentication, authorization, request filtering, and more.

// This means that when the server processes a request,
// it will use the HeaderContentNegotiationStrategy implementation to determine the appropriate content type
// for the response based on the Accept header of the client's request.
