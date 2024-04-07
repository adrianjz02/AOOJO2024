package com.jeuxolympiques.jo2024.controller;

import com.jeuxolympiques.jo2024.model.Country;
import com.jeuxolympiques.jo2024.persistence.CountryRepository;
import com.jeuxolympiques.jo2024.service.countryService.CountryService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/countries")
@Slf4j
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryService countryService;

    @GetMapping
    public String getAllCountries(Model model) {
        log.info("Affichage de la liste des pays");
        List<Country> countries = countryRepository.findAll();
        model.addAttribute("countries", countries);
        return "countryViews/countries";
    }

    @GetMapping("/add")
    public String showAddCountryForm(Model model) {
        log.info("Affichage du formulaire d'ajout de pays");
        model.addAttribute("country", new Country());
        return "countryViews/countriesAdd";
    }

    @PostMapping("/add")
    public String addCountry(@ModelAttribute Country country) {
        log.info("Ajout d'un nouveau pays : {}", country);
        countryRepository.save(country);
        return "redirect:/countries";
    }

    @GetMapping("/update/{id}")
    public String showUpdateCountryForm(@PathVariable Long id, Model model) {
        log.info("Affichage de la modification du pays avec l'ID : {}", id);
        Country country = countryService.getCountryById(id);
        if (country == null) {
            log.error("Pays non trouvé avec l'ID : {}", id);
            return "country-404";
        }
        model.addAttribute("country", country);
        return "countryViews/countriesUpdate";
    }

    @PostMapping("/update/{id}")
    public String updateCountry(@PathVariable Long id, @ModelAttribute Country country) {
        log.info("Mise à jour du pays avec l'ID : {}", id);
        country.setId(id);
        countryService.updateCountry(country);
        return "redirect:/countries";
    }

    @GetMapping("/delete/{id}")
    public String deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return "redirect:/countries";
    }
}
