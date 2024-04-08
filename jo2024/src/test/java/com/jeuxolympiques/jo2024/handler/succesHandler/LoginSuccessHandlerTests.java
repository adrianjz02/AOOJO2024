package com.jeuxolympiques.jo2024.handler.succesHandler;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import com.jeuxolympiques.jo2024.handler.successHandler.LoginSuccessHandler;

public class LoginSuccessHandlerTests {

    @Test
    public void testOnAuthenticationSuccessWithAuthentication()
            throws IOException, ServletException, jakarta.servlet.ServletException {
        // Créer un objet Authentication simulé
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("testuser");

        // Créer des objets de requête et de réponse simulés
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Créer une instance de LoginSuccessHandler
        LoginSuccessHandler loginSuccessHandler = new LoginSuccessHandler();

        // Appeler la méthode onAuthenticationSuccess
        loginSuccessHandler.onAuthenticationSuccess(request, response, authentication);

        // Vérifier que la redirection a été effectuée avec le bon URL
        assertEquals("/accueil?loginSuccess=true", response.getRedirectedUrl());
    }

    @Test
    public void testOnAuthenticationSuccessWithNullAuthentication()
            throws IOException, ServletException, jakarta.servlet.ServletException {
        // Créer des objets de requête et de réponse simulés
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Créer une liste de messages de log simulée
        List<String> logMessages = new ArrayList<>();
        request.setAttribute("logMessages", logMessages);

        // Créer une instance de LoginSuccessHandler
        LoginSuccessHandler loginSuccessHandler = new LoginSuccessHandler();

        // Appeler la méthode onAuthenticationSuccess avec authentication = null
        loginSuccessHandler.onAuthenticationSuccess(request, response, null);

        // Vérifier que la redirection a été effectuée avec le bon URL
        assertEquals("/accueil?loginSuccess=true", response.getRedirectedUrl());

    }

}
