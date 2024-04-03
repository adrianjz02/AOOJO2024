package com.jeuxolympiques.jo2024.EventListener.SecuritySuccessListener;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthenticationSuccessListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {


    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
        UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();
        logAuthenticationSuccess(userDetails.getUsername());
    }

    private void logAuthenticationSuccess(String username) {
        log.info("L'utilisateur : {} a bien été connecté !", username);
    }
}
