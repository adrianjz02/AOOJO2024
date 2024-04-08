package com.jeuxolympiques.jo2024.controller;

import com.jeuxolympiques.jo2024.model.Athlete;
import com.jeuxolympiques.jo2024.persistence.AthleteRepository;
import com.jeuxolympiques.jo2024.service.athleteService.AthleteService;

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
@RequestMapping("/athletes")
@Slf4j
public class AthleteController {

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired AthleteService athleteService;

    @GetMapping
    public String getAllAthletes(Model model) {
        log.info("Affichage de la liste des athlètes");
        List<Athlete> athletes = athleteRepository.findAll();
        model.addAttribute("athletes", athletes);
        return "athleteViews/athletes";
    }

    @GetMapping("/add")
    public String showAddAthleteForm(Model model) {
        log.info("Affichage du formulaire d'ajout d'athlète");
        model.addAttribute("athlete", new Athlete());
        return "athleteViews/athletesAdd";
    }

    @PostMapping("/add")
    public String addAthlete(@ModelAttribute Athlete athlete) {
        log.info("Ajout d'un nouvel athlète : {}", athlete);
        athleteRepository.save(athlete);
        return "redirect:/athletes";
    }

    @GetMapping("/{id}")
    public String getAthleteProfile(@PathVariable Long id, Model model) {
        log.info("Affichage du profil de l'athlète avec l'ID : {}", id);
        Athlete athlete = athleteRepository.findById(id).orElse(null);
        if (athlete == null) {
            return "athlete-404";
        }
        model.addAttribute("athlete", athlete);
        return "athleteViews/athlete-profile";
    }

    @GetMapping("/update/{id}")
    public String showUpdateAthleteForm(@PathVariable Long id, Model model) {
        log.info("Affichage de la modification de l'athlète avec l'ID : {}", id);
        Athlete athlete = athleteService.getAthleteById(id);
        log.debug("Athlete = ", athlete);
        if (athlete == null) {
            log.error("Athlète non trouvé avec l'ID : {}", id);
            return "athlete-404";
        }
        model.addAttribute("athlete", athlete);
        return "athleteViews/athletesUpdate";
    }

    @PostMapping("/update/{id}")
    public String updateAthlete(@PathVariable Long id, @ModelAttribute Athlete athlete) {
        log.info("Mise à jour de l'athlète avec l'ID : {}", id);
        athlete.setId(id);
        athleteService.updateAthlete(athlete);
        return "redirect:/athletes";
    }

    @GetMapping("/delete/{id}")
    public String deleteAthlete(@PathVariable Long id) {
        log.info("Suppression de l'athlète avec l'ID : {}", id);
        athleteRepository.deleteById(id);
        return "redirect:/athletes";
    }

    
}
