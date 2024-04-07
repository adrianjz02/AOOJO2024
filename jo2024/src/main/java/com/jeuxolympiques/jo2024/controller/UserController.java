package com.jeuxolympiques.jo2024.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeuxolympiques.jo2024.handler.failureHandler.registrationFailureHandler.RegistrationFailureHandler;
import com.jeuxolympiques.jo2024.handler.successHandler.RegistrationSuccessHandler;
import com.jeuxolympiques.jo2024.model.User.User;
import com.jeuxolympiques.jo2024.service.userService.UserRegistrationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserRegistrationService userRegistrationService;
    private final RegistrationSuccessHandler registrationSuccessHandler;
    private final RegistrationFailureHandler registrationFailureHandler;

    @GetMapping("/registration")
    public String showRegistrationForm(@RequestParam(value = "error", required = false) String error, Model model) {
        log.info("Affichage du formulaire d'registration");
        if (error != null) {
            if (error.equalsIgnoreCase("email_already_exists")) {
                model.addAttribute("error", "Votre email est déjà utilisé, veuillez réessayer !");
            } else if (error.equalsIgnoreCase("bad_password_length")) {
                model.addAttribute("error", "Le mot de passe doit contenir au moins 3 caractères.");
            }
        }
        model.addAttribute("user", new User());
        return "userViews/registration";
    }
    
    @PostMapping("/registration")
    public String register(@ModelAttribute("user") User user, HttpServletRequest request, HttpServletResponse response) throws IOException, jakarta.servlet.ServletException{
        try {
            userRegistrationService.registerUser(user);
            // Utilisation du gestionnaire de succès pour l'inscription ici et non dans SecurityConfig
            registrationSuccessHandler.onRegistrationSuccess(request, response, user.getFirstName());
            return "redirect:/login?registrationSuccess=true";
        } catch (RuntimeException e) {
            registrationFailureHandler.onRegistrationFailure(request, response, e);
            return null;
        }
    }

    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "error", required = false) String error, Model model) {
        log.info("Affichage du formulaire de connexion");
        if (error != null) {
            if (error.equalsIgnoreCase("bad_credentials")) {
                model.addAttribute("error", "Email ou mot de passe incorrect");
            } else if (error.equalsIgnoreCase("disabled")) {
                model.addAttribute("error", "Compte utilisateur désactivé");
            } else if (error.equalsIgnoreCase("locked")) {
                model.addAttribute("error", "Compte utilisateur verrouillé");
            } else if (error.equalsIgnoreCase("expired")) {
                model.addAttribute("error", "Compte utilisateur expiré");
            } else {
                model.addAttribute("error", "Échec de la connexion");
            }
        }
        return "userViews/login";
    }
}