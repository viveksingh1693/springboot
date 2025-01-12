package com.viv.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CorsConfigurationSourceImpl implements CorsConfigurationSource {
    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedOrigins(Collections.singletonList(""));
        cors.setAllowedMethods(Collections.singletonList("*"));
        cors.setAllowCredentials(true);
        cors.setAllowedHeaders(Collections.singletonList("*"));
        cors.setExposedHeaders(List.of("Authorization"));
        cors.setMaxAge(3600L);

        return cors;
    }
}
