package com.jeuxolympiques.jo2024.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HomeControllerTests {

    @Mock
    private Model model;

    @Mock
    private Authentication authentication;

    private HomeController homeController;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        homeController = new HomeController();
    }

    @Test
    public void testWelcomeWithAuthenticatedUser() {
        when(authentication.isAuthenticated()).thenReturn(true);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        homeController.welcome(model, authentication);

        verify(model).addAttribute("authenticated", true);
    }

    @Test
    public void testWelcomeWithNonAuthenticatedUser() {
        when(authentication.isAuthenticated()).thenReturn(false);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        homeController.welcome(model, authentication);

        verify(model).addAttribute("authenticated", false);
    }
}
