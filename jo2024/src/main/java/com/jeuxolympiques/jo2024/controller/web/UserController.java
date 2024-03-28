package com.jeuxolympiques.jo2024.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/inscription")
    public String showRegistrationForm() {
        return "inscription"; // Cela renvoie le nom de la vue, Spring résoudra la vue avec ce nom.
    }

    @GetMapping("/connexion")
    public String showLoginForm() {
        return "connexion"; // Cela renvoie le nom de la vue, Spring résoudra la vue avec ce nom.
    }

}
