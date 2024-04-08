package com.jeuxolympiques.jo2024.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import com.jeuxolympiques.jo2024.handler.failureHandler.registrationFailureHandler.RegistrationFailureHandler;
import com.jeuxolympiques.jo2024.handler.successHandler.RegistrationSuccessHandler;
import com.jeuxolympiques.jo2024.model.User.User;
import com.jeuxolympiques.jo2024.service.userService.UserRegistrationService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {

    @Mock
    private UserRegistrationService userRegistrationService;

    @Mock
    private RegistrationSuccessHandler registrationSuccessHandler;

    @Mock
    private RegistrationFailureHandler registrationFailureHandler;

    @Mock
    private Model model;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private UserController userController;

    @Test
    public void testShowRegistrationForm() {
        String viewName = userController.showRegistrationForm(null, model);
        assert (viewName.equals("userViews/registration"));
    }

    @Test
    public void testRegister() throws Exception {

        User user = new User();
        user.setFirstName("John");

        String viewName = userController.register(user, request, response);

        assertEquals("redirect:/login?registrationSuccess=true", viewName);
        verify(userRegistrationService).registerUser(user);
        verify(registrationSuccessHandler).onRegistrationSuccess(request, response, user.getFirstName());
    }

    @Test
    public void testLoginForm() {
        String viewName = userController.loginForm(null, model);
        assert (viewName.equals("userViews/login"));
    }

    @Test
    public void testShowRegistrationForm_EmailAlreadyExists() {
        String viewName = userController.showRegistrationForm("email_already_exists", model);
        assertEquals("userViews/registration", viewName);
        verify(model).addAttribute("error", "Votre email est déjà utilisé, veuillez réessayer !");
    }

    @Test
    public void testShowRegistrationForm_BadPasswordLength() {
        String viewName = userController.showRegistrationForm("bad_password_length", model);
        assertEquals("userViews/registration", viewName);
        verify(model).addAttribute("error", "Le mot de passe doit contenir au moins 3 caractères.");
    }

    @Test
    public void testRegister_Exception() throws Exception {
        doThrow(new RuntimeException()).when(userRegistrationService).registerUser(any(User.class));
        String viewName = userController.register(new User(), request, response);
        assertNull(viewName);
        verify(registrationFailureHandler).onRegistrationFailure(eq(request), eq(response),
                any(RuntimeException.class));
    }

    @Test
    public void testLoginForm_BadCredentials() {
        String viewName = userController.loginForm("bad_credentials", model);
        assertEquals("userViews/login", viewName);
        verify(model).addAttribute("error", "Email ou mot de passe incorrect");
    }


    @Test
    public void testLoginForm_Disabled() {
        String viewName = userController.loginForm("disabled", model);
        assertEquals("userViews/login", viewName);
        verify(model).addAttribute("error", "Compte utilisateur désactivé");
    }

    @Test
    public void testLoginForm_Locked() {
        String viewName = userController.loginForm("locked", model);
        assertEquals("userViews/login", viewName);
        verify(model).addAttribute("error", "Compte utilisateur verrouillé");
    }

    @Test
    public void testLoginForm_Expired() {
        String viewName = userController.loginForm("expired", model);
        assertEquals("userViews/login", viewName);
        verify(model).addAttribute("error", "Compte utilisateur expiré");
    }

    @Test
    public void testLoginForm_DefaultError() {
        // Test du cas par défaut
        String viewName = userController.loginForm("any_other_error", model);
        assertEquals("userViews/login", viewName);
        verify(model).addAttribute("error", "Échec de la connexion");
    }

}
