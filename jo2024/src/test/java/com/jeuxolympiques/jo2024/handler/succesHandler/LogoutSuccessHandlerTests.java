package com.jeuxolympiques.jo2024.handler.succesHandler;

import java.util.Collection;
import java.util.Arrays;

import com.jeuxolympiques.jo2024.handler.successHandler.LogoutSuccessHandler;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LogoutSuccessHandlerTests {

    @InjectMocks
    private LogoutSuccessHandler logoutSuccessHandler;

    @Test
    public void testOnLogoutSuccess() throws IOException {
        // Créer une collection d'autorités (rôles) pour l'utilisateur
        Collection<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));

        // Créer un objet UserDetails simulé avec les autorités
        UserDetails userDetails = new User("testuser", "password", authorities);

        // Créer un objet Authentication simulé
        Authentication authentication = new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                userDetails, null);

        // Créer des objets de requête et de réponse simulés
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Appeler la méthode onLogoutSuccess
        logoutSuccessHandler.onLogoutSuccess(request, response, authentication);

        // Vérifier que la redirection a été effectuée avec le bon URL
        assertEquals("/accueil?logoutSuccess=true", response.getRedirectedUrl());
    }

     @Test
    public void testOnLogoutSuccessWithNullAuthentication() throws IOException {
        // Créer des objets de requête et de réponse simulés
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Définir l'objet Authentication à null dans SecurityContextHolder
        SecurityContextHolder.clearContext();

        // Appeler la méthode onLogoutSuccess
        logoutSuccessHandler.onLogoutSuccess(request, response, null);

        // Vérifier que la redirection a été effectuée avec le bon URL
        assertEquals("/accueil?logoutSuccess=true", response.getRedirectedUrl());
    }

}
