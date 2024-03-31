package com.jeuxolympiques.jo2024.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j

public class ControllerTest {

    @GetMapping("/accueil")
    public String welcome() {
        log.info("Bienvenue dans la page d'accueil");
        return "accueil";
    }
}
