package com.jeuxolympiques.jo2024.controller.web;

import com.jeuxolympiques.jo2024.model.Athlete;
import com.jeuxolympiques.jo2024.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/athletes")
public class AthleteController {
    @Autowired
    private AthleteRepository athleteRepository;

    @GetMapping
    public String getAllAthletes(Model model) {
        List<Athlete> athletes = athleteRepository.findAll();
        model.addAttribute("athletes", athletes);
        return "athletes";
    }
}
