package com.jeuxolympiques.jo2024.handler.successHandler;

import java.io.IOException;

import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class RegistrationSuccessHandler {

    public void onRegistrationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            String username
    ) throws IOException {
        
        log.info("L'utilisateur : {} a bien été inscrit !", username);
    }
}