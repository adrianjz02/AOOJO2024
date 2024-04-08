package com.jeuxolympiques.jo2024.controller;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import com.jeuxolympiques.jo2024.model.Country;
import com.jeuxolympiques.jo2024.persistence.CountryRepository;
import com.jeuxolympiques.jo2024.service.countryService.CountryService;

@ExtendWith(MockitoExtension.class)
public class CountryControllerTests {

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CountryService countryService;

    @Mock
    private Model model;

    @InjectMocks
    private CountryController countryController;

    @Test
    public void testGetAllCountries() {
        List<Country> countries = Arrays.asList(new Country(), new Country());
        when(countryRepository.findAll()).thenReturn(countries);

        String viewName = countryController.getAllCountries(model);

        verify(model).addAttribute("countries", countries);
        assert (viewName.equals("countryViews/countries"));
    }

    @Test
    public void testShowAddCountryForm() {
        String viewName = countryController.showAddCountryForm(model);

        assert (viewName.equals("countryViews/countriesAdd"));
    }

    @Test
    public void testAddCountry() {
        Country country = new Country();
        String viewName = countryController.addCountry(country);

        verify(countryRepository).save(country);
        assert (viewName.equals("redirect:/countries"));
    }

    @Test
    public void testShowUpdateCountryForm() {
        Long id = 1L;
        Country country = new Country();
        when(countryService.getCountryById(id)).thenReturn(country);

        String viewName = countryController.showUpdateCountryForm(id, model);

        verify(model).addAttribute("country", country);
        assert (viewName.equals("countryViews/countriesUpdate"));
    }

    @Test
    public void testUpdateCountry() {
        Long id = 1L;
        Country country = new Country();
        String viewName = countryController.updateCountry(id, country);

        country.setId(id);
        verify(countryService).updateCountry(country);
        assert (viewName.equals("redirect:/countries"));
    }

    @Test
    public void testDeleteCountry() {
        Long id = 1L;
        String viewName = countryController.deleteCountry(id);

        verify(countryService).deleteCountry(id);
        assert (viewName.equals("redirect:/countries"));
    }

    @Test
    public void testShowUpdateCountryForm_CountryNotFound() {
        Long id = 1L;
        when(countryService.getCountryById(id)).thenReturn(null);

        String viewName = countryController.showUpdateCountryForm(id, model);

        assert(viewName.equals("country-404"));
    }
}
