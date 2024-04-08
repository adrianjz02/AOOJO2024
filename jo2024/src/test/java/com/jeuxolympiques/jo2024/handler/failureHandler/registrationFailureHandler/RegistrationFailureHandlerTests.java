package com.jeuxolympiques.jo2024.handler.failureHandler.registrationFailureHandler;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class RegistrationFailureHandlerTests {

    @Test
    public void testOnRegistrationFailure_EmailAlreadyExists() throws IOException {
        // Créer des objets de requête et de réponse simulés
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Créer une instance de RegistrationFailureHandler
        RegistrationFailureHandler failureHandler = new RegistrationFailureHandler();

        // Appeler la méthode onRegistrationFailure avec une exception d'email déjà
        // existant
        failureHandler.onRegistrationFailure(request, response,
                new RuntimeException("Votre email est déjà utilisé, veuillez réessayer !"));

        // Vérifier si la redirection a été effectuée correctement
        assertEquals("/inscription?error=email_already_exists", response.getRedirectedUrl());
    }

    @Test
    public void testOnRegistrationFailure_BadPasswordLength() throws IOException {
        // Créer des objets de requête et de réponse simulés
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Créer une instance de RegistrationFailureHandler
        RegistrationFailureHandler failureHandler = new RegistrationFailureHandler();

        // Appeler la méthode onRegistrationFailure avec une exception de longueur de
        // mot de passe insuffisante
        failureHandler.onRegistrationFailure(request, response,
                new RuntimeException("Le mot de passe doit contenir au moins 3 caractères."));

        // Vérifier si la redirection a été effectuée correctement
        assertEquals("/inscription?error=bad_password_length", response.getRedirectedUrl());
    }

    @Test
    public void testOnRegistrationFailure_TechnicalFailure() throws IOException {
        // Créer des objets de requête et de réponse simulés
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Créer une instance de RegistrationFailureHandler
        RegistrationFailureHandler failureHandler = new RegistrationFailureHandler();

        // Appeler la méthode onRegistrationFailure avec une exception de défaillance
        // technique
        failureHandler.onRegistrationFailure(request, response,
                new RuntimeException("Une erreur technique s'est produite."));

        // Vérifier si la redirection a été effectuée correctement
        assertEquals("/inscription?error=technical_failure", response.getRedirectedUrl());
    }
}
