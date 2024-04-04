package com.jeuxolympiques.jo2024.Handler.RegistrationFailureHandler;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface CustomRegistrationFailureHandler {
    void onRegistrationFailure(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException;
}

