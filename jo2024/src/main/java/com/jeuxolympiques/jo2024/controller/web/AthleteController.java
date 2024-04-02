package com.jeuxolympiques.jo2024.controller.web;

import com.jeuxolympiques.jo2024.model.Athlete;
import com.jeuxolympiques.jo2024.repository.AthleteRepository;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/athletes")
public class AthleteController {

    @Autowired
    private AthleteRepository athleteRepository;

    @GetMapping
    public String getAllAthletes(Model model) {
        List<Athlete> athletes = athleteRepository.findAll();
        model.addAttribute("athletes", athletes);
        return "athletes";
    }

    @GetMapping("/add")
    public String showAddAthleteForm(Model model) {
        model.addAttribute("athlete", new Athlete());
        return "athletesadd";
    }

    @PostMapping("/add")
    public String addAthlete(@ModelAttribute Athlete athlete) {
        athleteRepository.save(athlete);
        return "redirect:/athletes";
    }

    @GetMapping("/{id}")
    public String getAthleteProfile(@PathVariable Long id, Model model) {
        Athlete athlete = athleteRepository.findById(id).orElse(null);
        if (athlete == null) {
            return "athlete-404";
        }
        model.addAttribute("athlete", athlete);
        return "athlete-profile";
    }

    @GetMapping("/delete/{id}")
    public String deleteAthlete(@PathVariable Long id) {
        athleteRepository.deleteById(id);
        return "redirect:/athletes";
    }
}
