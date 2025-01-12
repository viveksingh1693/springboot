package com.viv.config;

import com.viv.filter.JWTTokenGeneratorFilter;
import com.viv.filter.JWTTokenValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import static org.springframework.security.config.Customizer.withDefaults;

import java.security.Provider;


@Configuration
@Profile("!prod")
public class ProjectSecurityConfig {
        /**
         * This method is used to configure the security filter chain.
         * Overriding default method of spring class
         * SpringBootWebSecurityConfiguration.java
         *
         * @param http
         * @return
         * @throws Exception
         */
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

            CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();

            http.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .cors(corsConfig  -> corsConfig.configurationSource(new CorsConfigurationSourceImpl()))
                    .authorizeHttpRequests(requests -> requests
                            .requestMatchers("/myAccount").hasRole("USER")
                            .requestMatchers("/myBalance").hasAuthority("VIEWBALANCE")
                            .requestMatchers("/myLoans").hasAuthority("VIEWLOANS") //authority based control
                            .requestMatchers("/myCards").hasAnyRole("ADMIN","USER")  //Role bas control
                            .requestMatchers("/user").authenticated()
                            .requestMatchers("/notices","/contacts","/error","/register","/invalidSession","/login","/").permitAll())
                //     .addFilterAfter(new JWTTokenGeneratorFilter(),BasicAuthenticationFilter.class)
                //     .addFilterBefore(new JWTTokenValidatorFilter(),BasicAuthenticationFilter.class)
                        .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                    .formLogin(withDefaults())
                    .httpBasic(withDefaults());
                     return http.build();
		}

        @Bean
        public PasswordEncoder passwordEncoder() {
            return PasswordEncoderFactories
                    .createDelegatingPasswordEncoder();
        }


        @Bean
        public AuthenticationManager   authenticationManager(UserDetailsService userDetailsService,
                PasswordEncoder passwordEncoder
        ) throws Exception {
           EasyBankUsernamePwdAuthenticationProvider provider = new EasyBankUsernamePwdAuthenticationProvider(userDetailsService,passwordEncoder);
        
                ProviderManager providerManager = new ProviderManager(provider);
                providerManager.setEraseCredentialsAfterAuthentication(false);
                return providerManager;
        }

}
