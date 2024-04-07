package com.jeuxolympiques.jo2024.handler.successHandler;

import java.io.IOException;

import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;

@Component
@Slf4j
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    public static final String LOGOUT_SUCCESS_URL = "/accueil?logoutSuccess=true";

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication
    ) throws IOException {
        log.info("Chargement de la déconnexion ...");
        log.info("L'utilisateur : {} a bien été déconnecté !",
                    (authentication != null ? authentication.getName() : "Utilisateur inconnu")
                 );

        response.sendRedirect(LOGOUT_SUCCESS_URL);
    }
}

