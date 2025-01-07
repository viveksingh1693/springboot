package com.viv.oauth.config;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
      
        httpSecurity.authorizeHttpRequests(
            requests -> requests.requestMatchers("/secure").authenticated()
        .anyRequest().permitAll())
        .formLogin(Customizer.withDefaults())
        .oauth2Login(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration githubClientRegistration = githubClientRegistration();
        ClientRegistration facebookClientRegistration = facebookClientRegistration();
        return new InMemoryClientRegistrationRepository(githubClientRegistration
        ,facebookClientRegistration
        );
    }

    private ClientRegistration githubClientRegistration(){
        
       return CommonOAuth2Provider.GITHUB.getBuilder("github")
        .clientId("Ov23li2OfKRAJkuf8uX3")
        .clientSecret("6f50d174c1368ead7b620ac49fbc98fda3168cc5")
        .build();
    }

    private ClientRegistration facebookClientRegistration(){
        
        return CommonOAuth2Provider.FACEBOOK.getBuilder("facebook")
         .clientId("3985628125094419")
         .clientSecret("1149eaeb25c317d18c903ec184162b89")
         .build();
     }
}
