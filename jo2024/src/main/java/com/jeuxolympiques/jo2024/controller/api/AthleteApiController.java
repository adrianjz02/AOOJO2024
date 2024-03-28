package com.jeuxolympiques.jo2024.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeuxolympiques.jo2024.model.Athlete;
import com.jeuxolympiques.jo2024.service.AthleteService;

@RestController
@RequestMapping("/athletes")
public class AthleteApiController {

    @Autowired
    private AthleteService athleteService;

    @GetMapping("/{id}")
    public ResponseEntity<Athlete> getAthleteById(@PathVariable long id) {
        Athlete athlete = athleteService.getAthleteById(id);
        if (athlete != null) {
            return new ResponseEntity<>(athlete, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Athlete>> getAllAthletes() {
        List<Athlete> athletes = athleteService.getAllAthletes();
        return new ResponseEntity<>(athletes, HttpStatus.OK);
    }

    // Autres méthodes nécessaires
}
