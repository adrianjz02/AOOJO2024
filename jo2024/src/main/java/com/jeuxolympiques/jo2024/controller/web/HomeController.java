package com.jeuxolympiques.jo2024.controller.web;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j

public class HomeController {

    @GetMapping("/accueil")
    public String welcome(Model model, Authentication authentication) {
        log.info("Bienvenue dans la page d'accueil");
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("authenticated", true);
            // Ajoutez d'autres informations utilisateur au besoin
        } else {
            model.addAttribute("authenticated", false);
        }
        return "accueil";
    }

}
