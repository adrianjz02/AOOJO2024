package com.jeuxolympiques.jo2024.controller;

import com.jeuxolympiques.jo2024.model.Athlete;
import com.jeuxolympiques.jo2024.model.Country;
import com.jeuxolympiques.jo2024.model.user.User;
import com.jeuxolympiques.jo2024.persistence.AthleteRepository;
import com.jeuxolympiques.jo2024.persistence.CountryRepository;
import com.jeuxolympiques.jo2024.persistence.UserRepository;
import com.jeuxolympiques.jo2024.service.athleteService.AthleteService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

    @Autowired
    AthleteService athleteService;

    @Autowired
    private CountryRepository countryRepository;

    private UserRepository userRepository;

    @Autowired
    public AthleteController(AthleteRepository athleteRepository, UserRepository userRepository) {
        this.athleteRepository = athleteRepository;
        this.userRepository = userRepository;
    }

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
        model.addAttribute("countries", countryRepository.findAll());
        return "athleteViews/athletesAdd";
    }

    @PostMapping("/add")
    public String addAthlete(@ModelAttribute Athlete athlete, Model model) {
        log.info("Ajout d'un nouvel athlète : {}", athlete);
        athleteRepository.save(athlete);
        return "redirect:/athletes";
    }

    @GetMapping("/{id}")
    public String getAthleteProfile(@PathVariable Long id, Model model, Authentication authentication) {
        log.info("Affichage du profil de l'athlète avec l'ID : {}", id);
        Athlete athlete = athleteRepository.findById(id).orElse(null);
        boolean isFavorite = false;
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        isFavorite = user.getFavoriteAthletes().contains(athlete);

        if (athlete == null) {
            return "athlete-404";
        }
        model.addAttribute("athlete", athlete);
        model.addAttribute("isFavorite", isFavorite);
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
        model.addAttribute("countries", countryRepository.findAll());
        return "athleteViews/athletesUpdate";
    }

    @PostMapping("/update/{id}")
    public String updateAthlete(@PathVariable Long id, @ModelAttribute Athlete athlete, Model model) {
        log.info("Mise à jour de l'athlète avec l'ID : {}", id);

        // Récupérer l'objet Country sélectionné dans le formulaire
        Country selectedCountry = countryRepository.findById(athlete.getCountry().getId()).orElse(null);

        // Associer l'objet Country sélectionné à l'objet Athlete
        athlete.setCountry(selectedCountry);

        // Mettre à jour l'objet Athlete dans la base de données
        athlete.setId(id);
        athleteService.updateAthlete(athlete);

        // Ajouter la liste des pays au modèle pour la vue de mise à jour
        model.addAttribute("countries", countryRepository.findAll());

        return "redirect:/athletes";
    }

    @GetMapping("/delete/{id}")
    public String deleteAthlete(@PathVariable Long id) {
        log.info("Suppression de l'athlète avec l'ID : {}", id);
        athleteRepository.deleteById(id);
        return "redirect:/athletes";
    }

}
