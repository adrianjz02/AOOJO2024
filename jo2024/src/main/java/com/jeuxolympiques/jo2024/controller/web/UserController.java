package com.jeuxolympiques.jo2024.controller.web;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.jeuxolympiques.jo2024.model.User;
import com.jeuxolympiques.jo2024.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private AuthenticationManager authenticationManager;    

    @GetMapping("/inscription")
    public String showRegistrationForm() {
        log.info("Affichage du formulaire d'inscription");
        return "inscription"; // Cela renvoie le nom de la vue, Spring résoudra la vue avec ce nom.
    }

    @PostMapping("/inscription")
    public String registration(@ModelAttribute User user) {
        // Traitez les données du formulaire ici
        userService.saveUser(user);
        log.info("Inscription de l'utilisateur : {}", user.getName(), "Redirection vers la page d'accueil");
        
        // Redirigez l'utilisateur vers une page de confirmation ou d'accueil
        return "accueil";
    }

    @GetMapping("/connexion")
    public String showLoginForm() {
        log.info("Affichage du formulaire de connexion");
        return "connexion"; // Cela renvoie le nom de la vue, Spring résoudra la vue avec ce nom.
    }

     
    @PostMapping("/connexion")
    public String connexion(@ModelAttribute User user) {
        log.info("Tentative de connexion pour l'utilisateur : {}", user.getEmail());

        try {
            // Authentifier l'utilisateur
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );
            
            // Mettre à jour le contexte de sécurité
            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.info("Connexion réussie pour l'utilisateur : {}", user.getEmail());
            
            // Rediriger l'utilisateur vers la page de succès
            return "success";
        } catch (AuthenticationException e) {
            // L'authentification a échoué
            log.error("Échec de la connexion pour l'utilisateur : {}", user.getEmail(), e);
            
            // Rediriger l'utilisateur vers la page de connexion avec un message d'erreur
            return "connexion?error";
        }
    }
     

}