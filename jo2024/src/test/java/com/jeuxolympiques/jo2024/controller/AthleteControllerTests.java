package com.jeuxolympiques.jo2024.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import com.jeuxolympiques.jo2024.model.Athlete;
import com.jeuxolympiques.jo2024.model.Country;
import com.jeuxolympiques.jo2024.model.user.User;
import com.jeuxolympiques.jo2024.persistence.AthleteRepository;
import com.jeuxolympiques.jo2024.persistence.CountryRepository;
import com.jeuxolympiques.jo2024.persistence.UserRepository;
import com.jeuxolympiques.jo2024.service.athleteService.AthleteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AthleteControllerTests {

    @Mock
    private AthleteRepository athleteRepository;

    @Mock
    private AthleteService athleteService;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AthleteController athleteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAthletes() {
        List<Athlete> athletes = new ArrayList<>();
        when(athleteRepository.findAll()).thenReturn(athletes);

        Model model = mock(Model.class);

        String viewName = athleteController.getAllAthletes(model);

        assertEquals("athleteViews/athletes", viewName);
        verify(model).addAttribute("athletes", athletes);
    }

    

    @Test
    void testAddAthlete() {
        Athlete athlete = new Athlete();
        String viewName = athleteController.addAthlete(athlete, mock(Model.class));

        assertEquals("redirect:/athletes", viewName);
        verify(athleteRepository).save(athlete);
    }

    @Test
    void testGetAthleteProfile() {
        Athlete athlete = new Athlete();
        athlete.setId(1L);
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("test@test.com");
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new User()));
        when(athleteRepository.findById(1L)).thenReturn(Optional.of(athlete));
        Model model = mock(Model.class);

        String viewName = athleteController.getAthleteProfile(1L, model, authentication);

        assertEquals("athleteViews/athlete-profile", viewName);
        verify(model).addAttribute("athlete", athlete);
    }

    

    @Test
    void testDeleteAthlete() {
        String viewName = athleteController.deleteAthlete(1L);

        assertEquals("redirect:/athletes", viewName);
        verify(athleteRepository).deleteById(1L);
    }

    
}
