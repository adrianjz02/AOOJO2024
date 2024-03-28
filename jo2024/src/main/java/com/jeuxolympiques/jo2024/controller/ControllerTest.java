package com.jeuxolympiques.jo2024.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerTest {

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
}
