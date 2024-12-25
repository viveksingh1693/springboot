package com.viv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;
import javax.sql.DataSource;


@Configuration
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
            http.authorizeHttpRequests((requests) -> requests
            .requestMatchers("/myAccount","/myBalance","/myLoans","/myCards").authenticated()
            .requestMatchers("/notices","/contacts","/error").permitAll()
            );
			http.formLogin(withDefaults());
            // http.formLogin(flc->flc.disable()); //it will enable the basic http authentication
			http.httpBasic(withDefaults());
			return http.build();
		}

        /***
         * This method is used to create the Inmemory user details manager
         * @return
         */
        // @Bean
        // UserDetailsService UserDetailsManager(){
        //   UserDetails user =  User.withUsername("user")
        //     .password("{noop}12345")
        //     .authorities("read")
        //     .build();
        //   UserDetails admin =  User.withUsername("{noop}admin")
        //     .password("54321")
        //     .authorities("admin")
        //     .build();

        //     return new InMemoryUserDetailsManager(user,admin);
        // }



        @Bean
        UserDetailsService UserDetailsManager(DataSource dataSource){
            return new JdbcUserDetailsManager(dataSource);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        }

}
