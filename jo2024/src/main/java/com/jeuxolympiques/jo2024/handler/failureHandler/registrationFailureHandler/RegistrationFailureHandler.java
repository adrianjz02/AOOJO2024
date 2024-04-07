package com.jeuxolympiques.jo2024.handler.failureHandler.registrationFailureHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Slf4j
public class RegistrationFailureHandler implements CustomRegistrationFailureHandler {

    @Override
    public void onRegistrationFailure(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        log.error("Erreur détectée lors de l'inscription ... : {}", exception.getMessage());
        String errorMessage = "error=";
        if (exception.getMessage().equalsIgnoreCase("Votre email est déjà utilisé, veuillez réessayer !")) {
            errorMessage += "email_already_exists";
        } else if (exception.getMessage().equalsIgnoreCase("Le mot de passe doit contenir au moins 3 caractères.")) {
            errorMessage += "bad_password_length";
        } else {
            errorMessage += "technical_failure";
        }
        request.setAttribute("error", errorMessage);
        response.sendRedirect("/inscription?" + errorMessage);
    }
}