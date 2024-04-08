package com.jeuxolympiques.jo2024.handler.succesHandler;

import com.jeuxolympiques.jo2024.handler.successHandler.RegistrationSuccessHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verifyNoInteractions;

import java.io.IOException;

public class RegistrationSuccessHandlerTests {

    private RegistrationSuccessHandler registrationSuccessHandler;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        registrationSuccessHandler = new RegistrationSuccessHandler();
    }

    @Test
    public void testOnRegistrationSuccess() throws IOException {
        String username = "testUser";

        registrationSuccessHandler.onRegistrationSuccess(request, response, username);

        // Vérifiez qu'il n'y a pas d'interactions avec les objets mock (request et
        // response)
        verifyNoInteractions(request, response);
    }

}
