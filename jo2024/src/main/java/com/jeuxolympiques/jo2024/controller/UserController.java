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
        String userEmail = principal.getName(); // Récupérer l'e-mail de l'utilisateur connecté
        User user = userRepository.findByEmail(userEmail).orElse(null);
        model.addAttribute("user", user);
        return "favoriteAthletes"; // Retourner le nom de la vue pour afficher les athlètes favoris
    }


    @PostMapping("/{id}/favorite")
    public String addToFavorites(@PathVariable Long id, Principal principal) {
        String userEmail = principal.getName(); // Récupérer l'e-mail de l'utilisateur connecté
        User user = userRepository.findByEmail(userEmail).orElse(null);
        Athlete athlete = athleteRepository.findById(id).orElse(null);
        if (user != null && athlete != null) {
            user.getFavoriteAthletes().add(athlete);
            userRepository.save(user);
        }
        return "redirect:/athletes/" + id; // Rediriger vers la page de détail de l'athlète
    }


    @PostMapping("/{id}/removeFavorite")
public String removeFavoriteAthlete(@PathVariable Long id, Principal principal) {
    String userEmail = principal.getName();
    log.info("Removing athlete with id {} from favorites for user with email: {}", id, userEmail);

    User user = userRepository.findByEmail(userEmail).orElse(null);
    Athlete athlete = athleteRepository.findById(id).orElse(null);
    
    if (user != null && athlete != null) {
        log.info("User and athlete found, removing athlete from favorites.");
        user.getFavoriteAthletes().remove(athlete);
        userRepository.save(user);
    } else {
        log.warn("User or athlete not found. User: {}, Athlete: {}", user, athlete);
    }
    
    log.info("Redirecting to the favoriteAthletes page.");
    return "redirect:/users/favoriteAthletes";
}



}