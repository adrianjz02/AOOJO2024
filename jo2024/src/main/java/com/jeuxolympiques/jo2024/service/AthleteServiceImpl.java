package com.jeuxolympiques.jo2024.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeuxolympiques.jo2024.model.Athlete;
import com.jeuxolympiques.jo2024.repository.AthleteRepository;

@Service
public class AthleteServiceImpl implements AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    @Override
    public Athlete getAthleteById(long id) {
        Optional<Athlete> athleteOptional = athleteRepository.findById(id);
        return athleteOptional.orElse(null);
    }

    @Override
    public List<Athlete> getAllAthletes() {
        return athleteRepository.findAll();
    }

    // Implémentez d'autres méthodes si nécessaire
}
