package com.jeuxolympiques.jo2024.service;

import java.util.List;

import com.jeuxolympiques.jo2024.model.Athlete;

public interface AthleteService {
    Athlete getAthleteById(long id);

    List<Athlete> getAllAthletes();
    // Autres méthodes nécessaires
}
