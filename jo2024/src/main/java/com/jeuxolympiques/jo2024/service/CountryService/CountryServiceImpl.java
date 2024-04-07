package com.jeuxolympiques.jo2024.service.CountryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeuxolympiques.jo2024.model.Country;
import com.jeuxolympiques.jo2024.persistence.CountryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country getCountryById(long id) {
        log.info("Récupération du pays avec l'ID : {}", id);
        return countryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Country> getAllCountries() {
        log.info("Récupération de tous les pays");
        return countryRepository.findAll();
    }

    @Override
    public Country saveCountry(Country country) {
        log.info("Processus d'enregistrement d'un nouveau pays : {}", country);
        return countryRepository.save(country);
    }

    @Override
    public Country updateCountry(Country country) {
        log.info("Mise à jour en cours du pays {} ...", country.getNameCountry());
        Country existingCountry = countryRepository.findById(country.getId())
                .orElseThrow(() -> new RuntimeException("Country not found"));

        existingCountry.setNameCountry(country.getNameCountry());
        existingCountry.setFlag(country.getFlag());
        existingCountry.setCapital(country.getCapital());
        existingCountry.setPopulation(country.getPopulation());
        existingCountry.setRelevantPoint(country.getRelevantPoint());

        return countryRepository.save(existingCountry);
    }

    @Override
    public void deleteCountry(Long id) {
        log.info("Suppression du pays avec l'ID : {}", id);
        countryRepository.deleteById(id);
    }
}
