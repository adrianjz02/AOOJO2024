package com.jeuxolympiques.jo2024.controller;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import com.jeuxolympiques.jo2024.model.Athlete;
import com.jeuxolympiques.jo2024.persistence.AthleteRepository;
import com.jeuxolympiques.jo2024.service.athleteService.AthleteService;

@ExtendWith(MockitoExtension.class)
public class AthleteControllerTests {

    @Mock
    private AthleteRepository athleteRepository;

    @Mock
    private AthleteService athleteService;

    @Mock
    private Model model;

    @InjectMocks
    private AthleteController athleteController;

    @Test
    public void testGetAllAthletes() {
        List<Athlete> athletes = Arrays.asList(new Athlete(), new Athlete());
        when(athleteRepository.findAll()).thenReturn(athletes);

        String viewName = athleteController.getAllAthletes(model);

        verify(model).addAttribute("athletes", athletes);
        assert (viewName.equals("athleteViews/athletes"));
    }

    @Test
    public void testShowAddAthleteForm() {
        String viewName = athleteController.showAddAthleteForm(model);

        assert (viewName.equals("athleteViews/athletesAdd"));
    }

    @Test
    public void testAddAthlete() {
        Athlete athlete = new Athlete();
        String viewName = athleteController.addAthlete(athlete);

        verify(athleteRepository).save(athlete);
        assert(viewName.equals("redirect:/athletes"));
    }

    @Test
    public void testGetAthleteProfile() {
        Long id = 1L;
        Athlete athlete = new Athlete();
        when(athleteRepository.findById(id)).thenReturn(Optional.of(athlete));

        String viewName = athleteController.getAthleteProfile(id, model);

        verify(model).addAttribute("athlete", athlete);
        assert(viewName.equals("athleteViews/athlete-profile"));
    }

    @Test
    public void testGetAthleteProfile_AthleteNotFound() {
        Long id = 1L;
        when(athleteRepository.findById(id)).thenReturn(Optional.empty());

        String viewName = athleteController.getAthleteProfile(id, model);

        assert(viewName.equals("athlete-404"));
    }

    @Test
    public void testShowUpdateAthleteForm_AthleteNotFound() {
        Long id = 1L;
        when(athleteService.getAthleteById(id)).thenReturn(null);

        String viewName = athleteController.showUpdateAthleteForm(id, model);

        assert(viewName.equals("athlete-404"));
    }

    @Test
    public void testShowUpdateAthleteForm() {
        Long id = 1L;
        Athlete athlete = new Athlete();
        when(athleteService.getAthleteById(id)).thenReturn(athlete);

        String viewName = athleteController.showUpdateAthleteForm(id, model);

        verify(model).addAttribute("athlete", athlete);
        assert(viewName.equals("athleteViews/athletesUpdate"));
    }

    @Test
    public void testUpdateAthlete() {
        Long id = 1L;
        Athlete athlete = new Athlete();
        String viewName = athleteController.updateAthlete(id, athlete);

        athlete.setId(id);
        verify(athleteService).updateAthlete(athlete);
        assert(viewName.equals("redirect:/athletes"));
    }

    @Test
    public void testDeleteAthlete() {
        Long id = 1L;
        String viewName = athleteController.deleteAthlete(id);

        verify(athleteRepository).deleteById(id);
        assert(viewName.equals("redirect:/athletes"));
    }
}
