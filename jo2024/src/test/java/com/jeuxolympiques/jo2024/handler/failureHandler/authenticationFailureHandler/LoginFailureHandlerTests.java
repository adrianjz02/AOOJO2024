package com.jeuxolympiques.jo2024.handler.failureHandler.authenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LoginFailureHandlerTests {

    private LoginFailureHandler loginFailureHandler;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        loginFailureHandler = new LoginFailureHandler();
    }

    @Test
    public void testOnAuthenticationFailure_BadCredentialsException() throws IOException, ServletException {
        AuthenticationException exception = new BadCredentialsException("Bad credentials");

        loginFailureHandler.onAuthenticationFailure(request, response, exception);

        verify(response).sendRedirect(anyString());
    }

    @Test
    public void testOnAuthenticationFailure_DisabledException() throws IOException, ServletException {
        AuthenticationException exception = new DisabledException("User is disabled");

        loginFailureHandler.onAuthenticationFailure(request, response, exception);

        verify(response).sendRedirect(anyString());
    }

    @Test
    public void testOnAuthenticationFailure_LockedException() throws IOException, ServletException {
        AuthenticationException exception = new LockedException("User account is locked");

        loginFailureHandler.onAuthenticationFailure(request, response, exception);

        verify(response).sendRedirect(anyString());
    }

    @Test
    public void testOnAuthenticationFailure_AccountExpiredException() throws IOException, ServletException {
        AuthenticationException exception = new AccountExpiredException("User account has expired");

        loginFailureHandler.onAuthenticationFailure(request, response, exception);

        verify(response).sendRedirect(anyString());
    }

}
