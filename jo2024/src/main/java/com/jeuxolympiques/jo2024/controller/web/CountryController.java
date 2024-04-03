package com.jeuxolympiques.jo2024.controller.web;

import com.jeuxolympiques.jo2024.model.Country;
import com.jeuxolympiques.jo2024.persistence.CountryRepository;

import com.jeuxolympiques.jo2024.persistence.CountryRepository;
import com.jeuxolympiques.jo2024.service.CountryService;
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
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryService countryService;

    @GetMapping
    public String getAllCountries(Model model) {
        List<Country> countries = countryRepository.findAll();
        model.addAttribute("countries", countries);
        return "countries";
    }

    @GetMapping("/add")
    public String showAddCountryForm(Model model) {
        model.addAttribute("country", new Country());
        return "countryadd";
    }

    @PostMapping("/add")
    public String addCountry(@ModelAttribute Country country) {
        countryRepository.save(country);
        return "redirect:/countries";
    }

    @GetMapping("/delete/{id}")
    public String deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return "redirect:/countries";
    }
}
