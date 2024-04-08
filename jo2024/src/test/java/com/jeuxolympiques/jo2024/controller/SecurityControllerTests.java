package com.jeuxolympiques.jo2024.controller;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.jeuxolympiques.jo2024.controller.SecurityController;
import com.jeuxolympiques.jo2024.handler.failureHandler.registrationFailureHandler.RegistrationFailureHandler;
import com.jeuxolympiques.jo2024.handler.successHandler.RegistrationSuccessHandler;
import com.jeuxolympiques.jo2024.model.user.User;
import com.jeuxolympiques.jo2024.service.userService.UserRegistrationService;

import jakarta.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SecurityControllerTests {

    @Test
    public void testShowRegistrationForm() {
        SecurityController controller = new SecurityController(
                mock(UserRegistrationService.class),
                mock(RegistrationSuccessHandler.class),
                mock(RegistrationFailureHandler.class)
        );
        Model model = new ExtendedModelMap();
        String error = "email_already_exists";
        
        String viewName = controller.showRegistrationForm(error, model);

        assertEquals("securityViews/registration", viewName);
        assertEquals("Votre email est déjà utilisé, veuillez réessayer !", model.getAttribute("error"));
    }

    @Test
    public void testLoginForm() {
        SecurityController controller = new SecurityController(
                mock(UserRegistrationService.class),
                mock(RegistrationSuccessHandler.class),
                mock(RegistrationFailureHandler.class)
        );
        Model model = new ExtendedModelMap();
        String error = "bad_credentials";

        String viewName = controller.loginForm(error, model);

        assertEquals("securityViews/login", viewName);
        assertEquals("Email ou mot de passe incorrect", model.getAttribute("error"));
    }

    @Test
    public void testRegister() throws IOException, ServletException {
        UserRegistrationService mockUserRegistrationService = mock(UserRegistrationService.class);
        RegistrationSuccessHandler mockSuccessHandler = mock(RegistrationSuccessHandler.class);
        RegistrationFailureHandler mockFailureHandler = mock(RegistrationFailureHandler.class);

        SecurityController controller = new SecurityController(
                mockUserRegistrationService,
                mockSuccessHandler,
                mockFailureHandler
        );

        User user = new User();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Mocking successful registration
        doNothing().when(mockUserRegistrationService).registerUser(user);

        String viewName = controller.register(user, request, response);

        assertEquals("redirect:/login?registrationSuccess=true", viewName);
        verify(mockSuccessHandler, times(1)).onRegistrationSuccess(request, response, user.getFirstName());
    }
}
