package com.jeuxolympiques.jo2024.handler.failureHandler.authenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("Erreur détectée lors de la connexion ... : {}", exception.getMessage());
        String errorMessage = "error=";
        if (exception instanceof BadCredentialsException) {
            errorMessage += "bad_credentials";
        } else if (exception instanceof DisabledException) {
            errorMessage += "disabled";
        } else if (exception instanceof LockedException) {
            errorMessage += "locked";
        } else if (exception instanceof AccountExpiredException) {
            errorMessage += "expired";
        }
        response.sendRedirect("/login?" + errorMessage);
    }
}