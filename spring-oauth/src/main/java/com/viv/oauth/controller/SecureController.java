package com.viv.oauth.controller;


import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class SecureController {

    @GetMapping("/secure")
    public String securePage(Authentication authentication){ {

        if(authentication instanceof UsernamePasswordAuthenticationToken){
            log.info("User Name: "+authentication.getName());
        }else if(authentication instanceof OAuth2AuthenticationToken){
            log.info(authentication.getName());
            
        }
        return "secure.html";
    }
    

}
