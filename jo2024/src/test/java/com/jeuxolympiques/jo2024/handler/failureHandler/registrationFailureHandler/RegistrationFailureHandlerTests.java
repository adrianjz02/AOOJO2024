package com.jeuxolympiques.jo2024.handler.failureHandler.registrationFailureHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationFailureHandlerTests {

    private RegistrationFailureHandler registrationFailureHandler;

    @BeforeEach
    public void setup() {
        registrationFailureHandler = new RegistrationFailureHandler();
    }

    @Test
    public void testOnRegistrationFailure_EmailAlreadyExists() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        Exception exception = new Exception("Votre email est déjà utilisé, veuillez réessayer !");

        registrationFailureHandler.onRegistrationFailure(request, response, exception);

        assertEquals("/registration?error=email_already_exists",
                ((MockHttpServletResponse) response).getRedirectedUrl());
    }

    @Test
    public void testOnRegistrationFailure_BadPasswordLength() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        Exception exception = new Exception("Le mot de passe doit contenir au moins 3 caractères.");

        registrationFailureHandler.onRegistrationFailure(request, response, exception);

        assertEquals("/registration?error=bad_password_length",
                ((MockHttpServletResponse) response).getRedirectedUrl());
    }

    @Test
    public void testOnRegistrationFailure_TechnicalFailure() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        Exception exception = new Exception("Erreur technique");

        registrationFailureHandler.onRegistrationFailure(request, response, exception);

        assertEquals("/registration?error=technical_failure", ((MockHttpServletResponse) response).getRedirectedUrl());
    }
}
