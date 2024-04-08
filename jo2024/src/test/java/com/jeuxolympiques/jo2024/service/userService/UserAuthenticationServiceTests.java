package com.jeuxolympiques.jo2024.service.userService;

import com.jeuxolympiques.jo2024.model.User.User;
import com.jeuxolympiques.jo2024.model.User.UserDetailsImpl;
import com.jeuxolympiques.jo2024.persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserAuthenticationServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserAuthenticationService userAuthenticationService;

    @Test
    public void testLoadUserByUsername_UserFound() {
        // Créer un utilisateur fictif
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        // Configurer le UserRepository pour retourner cet utilisateur lors de l'appel à
        // findByEmail
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        // Appeler la méthode loadUserByUsername avec l'e-mail de l'utilisateur
        UserDetails userDetails = userAuthenticationService.loadUserByUsername("test@example.com");

        // Vérifier que UserDetailsImpl correspondant a été retourné
        assertNotNull(userDetails);
        assertTrue(userDetails instanceof UserDetailsImpl);
        assertEquals(user.getEmail(), userDetails.getUsername());
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        // Configurer le UserRepository pour ne pas retourner d'utilisateur lors de
        // l'appel à findByEmail
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        // Appeler la méthode loadUserByUsername avec un e-mail inexistant
        // Vérifier qu'une exception UsernameNotFoundException est lancée
        assertThrows(UsernameNotFoundException.class,
                () -> userAuthenticationService.loadUserByUsername("nonexistent@example.com"));
    }
}
