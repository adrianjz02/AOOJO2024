package com.jeuxolympiques.jo2024.controller.web;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;

import com.jeuxolympiques.jo2024.model.User;
import com.jeuxolympiques.jo2024.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class UserController {
    private UserService userService;
    private AuthenticationManager authenticationManager;    

    @GetMapping("/inscription")
    public String showRegistrationForm() {
        log.info("Affichage du formulaire d'inscription");
        return "inscription";
    }

    @PostMapping("/inscription")
    public String registration(@ModelAttribute User user) {
        userService.saveUser(user);
        log.info("L'utilisateur : {}", user.getName(), "a bien été inscrit !!");
        return "redirect:/accueil";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        log.info("Affichage du formulaire de connexion");
        return "login";
    }

    // @PostMapping("/login")
    // public String login(@ModelAttribute User user, Model model) {
    //     log.info("Tentative de connexion pour l'utilisateur : {}", user.getEmail());

    //     try {
    //         Authentication authentication = authenticationManager.authenticate(
    //             new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
    //         );
            
    //         SecurityContextHolder.getContext().setAuthentication(authentication);
    //         model.addAttribute("loginSuccess", true);        
    //         return "redirect:/accueil"; // Redirection vers la page d'accueil
    //     } catch (AuthenticationException e) {
    //         log.error("Échec de la connexion pour l'utilisateur : {}", user.getEmail(), e);
            
    //         return "redirect:/login?error";
    //     }
    // }
}