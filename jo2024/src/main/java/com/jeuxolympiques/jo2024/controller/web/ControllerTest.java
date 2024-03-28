package com.jeuxolympiques.jo2024.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerTest {

    @GetMapping("/accueil")
    public String welcome() {
        return "accueil";
    }
}
