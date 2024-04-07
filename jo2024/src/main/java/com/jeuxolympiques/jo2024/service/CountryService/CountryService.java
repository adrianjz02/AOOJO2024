package com.jeuxolympiques.jo2024.service.countryService;

import com.jeuxolympiques.jo2024.model.Country;
import java.util.List;

public interface CountryService {

    Country getCountryById(long id);
    
    List<Country> getAllCountries();
    
    Country saveCountry(Country country);
    
    Country updateCountry(Country country);
    
    void deleteCountry(Long id);
}