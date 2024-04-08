package com.jeuxolympiques.jo2024.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.security.Principal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.jeuxolympiques.jo2024.model.Athlete;
import com.jeuxolympiques.jo2024.model.user.User;
import com.jeuxolympiques.jo2024.persistence.AthleteRepository;
import com.jeuxolympiques.jo2024.persistence.UserRepository;

public class UserControllerTests {

    @Mock
    private AthleteRepository athleteRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Model model;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowFavoriteAthletes() {
        Principal principal = () -> "user@example.com";
        User user = new User();
        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));

        String viewName = userController.showFavoriteAthletes(model, principal);

        // Asserts
        // Vérifiez que le modèle contient l'utilisateur
        verify(model).addAttribute("user", user);
        // Vérifiez que le nom de la vue retournée est "favoriteAthletes"
        assertEquals("favoriteAthletes", viewName);
    }

    @Test
    public void testAddFavorite() {
        Principal principal = () -> "user@example.com";
        User user = new User();
        Athlete athlete = new Athlete();

        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        when(athleteRepository.findById(anyLong())).thenReturn(Optional.of(athlete));

        String viewName = userController.addFavorite(1L, principal);

        // Asserts
        // Vérifiez que l'athlète a été ajouté aux favoris de l'utilisateur
        assertTrue(user.getFavoriteAthletes().contains(athlete));
        // Vérifiez que le changement a été enregistré
        verify(userRepository).save(user);
        // Vérifiez que la redirection a été effectuée
        assertEquals("redirect:/users/favoriteAthletes", viewName);
    }

    @Test
    public void testRemoveFavorite() {
        Principal principal = () -> "user@example.com";
        User user = new User();
        Athlete athlete = new Athlete();

        user.getFavoriteAthletes().add(athlete);

        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        when(athleteRepository.findById(anyLong())).thenReturn(Optional.of(athlete));

        String viewName = userController.removeFavorite(1L, principal);

        // Asserts
        // Vérifiez que l'athlète a été retiré des favoris de l'utilisateur
        assertFalse(user.getFavoriteAthletes().contains(athlete));
        // Vérifiez que le changement a été enregistré
        verify(userRepository).save(user);
        // Vérifiez que la redirection a été effectuée
        assertEquals("redirect:/users/favoriteAthletes", viewName);
    }
}
