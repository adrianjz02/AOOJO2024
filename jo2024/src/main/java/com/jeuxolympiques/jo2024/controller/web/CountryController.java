package com.jeuxolympiques.jo2024.controller.web;

import com.jeuxolympiques.jo2024.model.Country;
import com.jeuxolympiques.jo2024.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping
    public String getAllCountries(Model model) {
        List<Country> countries = countryRepository.findAll();
        model.addAttribute("countries", countries);
        return "countries";
    }
}
