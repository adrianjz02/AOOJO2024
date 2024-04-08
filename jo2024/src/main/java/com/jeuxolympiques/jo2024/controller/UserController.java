package com.jeuxolympiques.jo2024.controller;

import com.jeuxolympiques.jo2024.model.Athlete;
import com.jeuxolympiques.jo2024.model.user.User;
import com.jeuxolympiques.jo2024.persistence.AthleteRepository;
import com.jeuxolympiques.jo2024.persistence.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Set;


import java.security.Principal;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserController {

    private final AthleteRepository athleteRepository;
    private final UserRepository userRepository;

    // Injection des dépendances par constructeur

    @GetMapping("/favoriteAthletes")
    public String showFavoriteAthletes(Model model, Principal principal) {
        log.info("Affichage des athlètes favoris");
        String userEmail = principal.getName();
        User user = userRepository.findByEmail(userEmail).orElse(null);
        model.addAttribute("user", user);
        return "userViews/favoriteAthletes";
    }


    @PostMapping("/{athleteId}/addFavorite")
    public String addFavorite(@PathVariable Long athleteId, Principal principal) {
        log.info("Ajout d'un athlète favori");
        User user = userRepository.findByEmail(principal.getName()).orElseThrow();
        Athlete athlete = athleteRepository.findById(athleteId).orElseThrow();

        user.getFavoriteAthletes().add(athlete);
        userRepository.save(user);

        return "redirect:/users/favoriteAthletes";
    }

    @PostMapping("/{athleteId}/removeFavorite")
    public String removeFavorite(@PathVariable Long athleteId, Principal principal) {
        log.info("Suppression d'un athlète favori");
        User user = userRepository.findByEmail(principal.getName()).orElseThrow();
        Athlete athlete = athleteRepository.findById(athleteId).orElseThrow();

        user.getFavoriteAthletes().remove(athlete);
        userRepository.save(user);
        return "redirect:/users/favoriteAthletes";
    }
}
