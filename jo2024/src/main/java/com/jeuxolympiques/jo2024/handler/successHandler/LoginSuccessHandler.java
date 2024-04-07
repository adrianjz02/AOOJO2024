package com.jeuxolympiques.jo2024.handler.successHandler;

import java.io.IOException;


import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;

@Component
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private static final String LOGIN_SUCCESS_URL = "/accueil?loginSuccess=true";

    @Override
    public void onAuthenticationSuccess(
        jakarta.servlet.http.HttpServletRequest request,
        jakarta.servlet.http.HttpServletResponse response,
        Authentication authentication
    ) throws IOException, jakarta.servlet.ServletException {
        log.info("L'utilisateur : {} a bien été connecté !",
            (authentication != null ? authentication.getName() : "Utilisateur inconnu")
        );
        response.sendRedirect(LOGIN_SUCCESS_URL);  
    }
}