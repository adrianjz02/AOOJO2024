package com.jeuxolympiques.jo2024.service;

import com.jeuxolympiques.jo2024.model.Athlete;
import com.jeuxolympiques.jo2024.persistence.AthleteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AthleteServiceImpl implements AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    @Override
    public Athlete getAthleteById(long id) {
        return athleteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Athlete> getAllAthletes() {
        return athleteRepository.findAll();
    }

    @Override
    public Athlete saveAthlete(Athlete athlete) {
        return athleteRepository.save(athlete);
    }

    // Autres méthodes nécessaires
}
