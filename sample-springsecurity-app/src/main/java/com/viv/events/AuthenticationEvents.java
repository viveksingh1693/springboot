package com.viv.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * ProviderManager is responsible for publishing events
 */
@Component
@Slf4j
public class AuthenticationEvents {

//
    @EventListener
    public void onSuccess(AuthenticationSuccessEvent successEvent){
        log.info("Login Successful for the user: {}", successEvent.getAuthentication().getName());
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failureEvent){
        log.error("Login Failed for the user {} due to {}",failureEvent.getAuthentication().getName(),
                failureEvent.getException().getMessage());
    }
}
