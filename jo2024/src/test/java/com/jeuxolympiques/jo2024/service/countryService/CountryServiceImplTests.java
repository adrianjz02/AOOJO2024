package com.jeuxolympiques.jo2024.service.countryService;

import com.jeuxolympiques.jo2024.model.Country;
import com.jeuxolympiques.jo2024.persistence.CountryRepository;
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
public class CountryServiceImplTests {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryServiceImpl countryService;

    @Test
    public void testGetCountryById() {
        Country expectedCountry = new Country();
        expectedCountry.setId(1L);

        when(countryRepository.findById(1L)).thenReturn(Optional.of(expectedCountry));

        Country result = countryService.getCountryById(1L);

        assertEquals(expectedCountry, result);
    }

    @Test
    public void testGetAllCountries() {
        List<Country> expectedCountries = new ArrayList<>();
        expectedCountries.add(new Country());
        expectedCountries.add(new Country());

        when(countryRepository.findAll()).thenReturn(expectedCountries);

        List<Country> result = countryService.getAllCountries();

        assertEquals(expectedCountries, result);
    }

    @Test
    public void testSaveCountry() {
        Country countryToSave = new Country();
        countryToSave.setId(1L);

        when(countryRepository.save(countryToSave)).thenReturn(countryToSave);

        Country result = countryService.saveCountry(countryToSave);

        assertEquals(countryToSave, result);
    }

    @Test
    public void testUpdateCountry() {
        Country existingCountry = new Country();
        existingCountry.setId(1L);
        existingCountry.setNameCountry("France");

        Country updatedCountry = new Country();
        updatedCountry.setId(1L);
        updatedCountry.setNameCountry("Updated France");

        when(countryRepository.findById(1L)).thenReturn(Optional.of(existingCountry));
        when(countryRepository.save(existingCountry)).thenReturn(updatedCountry);

        Country result = countryService.updateCountry(updatedCountry);

        assertEquals(updatedCountry, result);
    }

    @Test
    public void testDeleteCountry() {
        countryService.deleteCountry(1L);

        verify(countryRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateCountry_CountryNotFound() {
        // Préparation des données de test
        Country country = new Country();
        country.setId(1L);
        country.setNameCountry("France");

        // Configurer le comportement simulé de countryRepository.findById()
        when(countryRepository.findById(country.getId())).thenReturn(Optional.empty());

        // Exécuter la méthode à tester et vérifier si elle lance l'exception attendue
        assertThrows(RuntimeException.class, () -> countryService.updateCountry(country));
    }
}
