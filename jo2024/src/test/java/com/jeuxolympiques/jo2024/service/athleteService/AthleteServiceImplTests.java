package com.jeuxolympiques.jo2024.service.athleteService;

import com.jeuxolympiques.jo2024.model.Athlete;
import com.jeuxolympiques.jo2024.persistence.AthleteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AthleteServiceImplTests {

    @Mock
    private AthleteRepository athleteRepository;

    @InjectMocks
    private AthleteServiceImpl athleteService;

    @Test
    public void testGetAthleteById() {
        Athlete expectedAthlete = new Athlete();
        expectedAthlete.setId(1L);

        when(athleteRepository.findById(1L)).thenReturn(Optional.of(expectedAthlete));

        Athlete result = athleteService.getAthleteById(1L);

        assertEquals(expectedAthlete, result);
    }

    @Test
    public void testGetAllAthletes() {
        List<Athlete> expectedAthletes = new ArrayList<>();
        expectedAthletes.add(new Athlete());
        expectedAthletes.add(new Athlete());

        when(athleteRepository.findAll()).thenReturn(expectedAthletes);

        List<Athlete> result = athleteService.getAllAthletes();

        assertEquals(expectedAthletes, result);
    }

    @Test
    public void testSaveAthlete() {
        Athlete athleteToSave = new Athlete();
        athleteToSave.setId(1L);

        when(athleteRepository.save(athleteToSave)).thenReturn(athleteToSave);

        Athlete result = athleteService.saveAthlete(athleteToSave);

        assertEquals(athleteToSave, result);
    }

    @Test
    public void testUpdateAthlete() {
        Athlete existingAthlete = new Athlete();
        existingAthlete.setId(1L);
        existingAthlete.setFirstName("John");

        Athlete updatedAthlete = new Athlete();
        updatedAthlete.setId(1L);
        updatedAthlete.setFirstName("Updated John");

        when(athleteRepository.findById(1L)).thenReturn(Optional.of(existingAthlete));
        when(athleteRepository.save(existingAthlete)).thenReturn(updatedAthlete);

        Athlete result = athleteService.updateAthlete(updatedAthlete);

        assertEquals(updatedAthlete, result);
    }

    @Test
    public void testDeleteAthlete() {
        athleteService.deleteAthlete(1L);

        verify(athleteRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateAthlete_AthleteNotFound() {
        // Préparation des données de test
        Athlete athlete = new Athlete();
        athlete.setId(1L);
        athlete.setFirstName("John");

        // Configurer le comportement simulé de athleteRepository.findById()
        when(athleteRepository.findById(athlete.getId())).thenReturn(Optional.empty());

        // Exécuter la méthode à tester et vérifier si elle lance l'exception attendue
        assertThrows(RuntimeException.class, () -> athleteService.updateAthlete(athlete));
    }
}
