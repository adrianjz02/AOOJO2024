package com.jeuxolympiques.jo2024.service.userService;

import com.jeuxolympiques.jo2024.exception.EmailAlreadyExistsException;
import com.jeuxolympiques.jo2024.exception.PasswordLengthException;
import com.jeuxolympiques.jo2024.model.user.User;
import com.jeuxolympiques.jo2024.persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserRegistrationServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserRegistrationService userRegistrationService;

    @Test
    public void testRegisterUser_EmailAlreadyExists() {
        // Configurer le UserRepository pour retourner un utilisateur existant avec le
        // même e-mail
        User existingUser = new User();
        existingUser.setEmail("test@example.com");
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(existingUser));

        // Créer un nouvel utilisateur avec le même e-mail
        User newUser = new User();
        newUser.setEmail("test@example.com");

        // Vérifier que l'appel à registerUser lance une exception
        // EmailAlreadyExistsException
        assertThrows(EmailAlreadyExistsException.class, () -> userRegistrationService.registerUser(newUser));

        // Vérifier que le userRepository.findByEmail a été appelé
        verify(userRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    public void testRegisterUser_PasswordTooShort() {
        // Créer un nouvel utilisateur avec un mot de passe trop court
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("12"); // Mot de passe trop court

        // Configurer le UserRepository pour ne pas retourner d'utilisateur existant
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

        // Vérifier que l'appel à registerUser lance une exception
        // PasswordLengthException
        assertThrows(PasswordLengthException.class, () -> userRegistrationService.registerUser(user));

        // Vérifier que le userRepository.findByEmail n'a pas été appelé
        verify(userRepository, never()).save(user);
    }

    @Test
    public void testRegisterUser_PasswordHashed() {
        // Créer un nouvel utilisateur avec un mot de passe valide
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password123");

        // Configurer le UserRepository pour ne pas retourner d'utilisateur existant
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());

        // Configurer le PasswordEncoder pour renvoyer une chaîne de hachage fixe
        String hashedPassword = "hashedPassword123";
        when(passwordEncoder.encode("password123")).thenReturn(hashedPassword);

        // Appeler la méthode registerUser
        userRegistrationService.registerUser(user);

        // Vérifier que le mot de passe de l'utilisateur a été correctement hashé
        assertEquals(hashedPassword, user.getPassword());

        // Vérifier que la méthode save a été appelée une fois avec l'utilisateur
        // correct
        verify(userRepository, times(1)).save(user);
    }

}
