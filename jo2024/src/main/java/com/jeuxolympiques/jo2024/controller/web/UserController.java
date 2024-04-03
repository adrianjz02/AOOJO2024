package com.jeuxolympiques.jo2024.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.jeuxolympiques.jo2024.model.User.User;
import com.jeuxolympiques.jo2024.service.UserService.UserRegistrationService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserRegistrationService userRegistrationService;

    @GetMapping("/inscription")
    public String registrationForm() {
        log.info("Affichage du formulaire d'inscription");
        return "inscription";
    }
    

    // @PostMapping("/inscription")
    // public String registerUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
    //     try {
    //         userRegistrationService.registerUser(user);
    //         log.info("L'utilisateur : {} a bien été inscrit !", user.getName());
    //         return "redirect:/accueil";
    //     } catch (EmailAlreadyExistsException e) {
    //         redirectAttributes.addFlashAttribute("errorMessage", "Cet email est déjà utilisé. Veuillez en choisir un autre.");
    //         return "redirect:/inscription";
    //     } catch (PasswordLengthException e) {
    //         redirectAttributes.addFlashAttribute("errorMessage", "Le mot de passe doit contenir au moins 6 caractères.");
    //         return "redirect:/inscription";
    //     }
    // }
    @PostMapping("/inscription")
    public String registerUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        
            userRegistrationService.registerUser(user);
            log.info("L'utilisateur : {} a bien été inscrit !", user.getName());
            return "redirect:/accueil";
    
    }

    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "error", required = false) String error, Model model) {
        log.info("Affichage du formulaire de connexion");
        if (error != null) {
            log.error(" ...");
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
            log.error("Veuillez re-essayer !");
        }
        return "login";
    }

}