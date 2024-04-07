package com.jeuxolympiques.jo2024.service.athleteService;

import com.jeuxolympiques.jo2024.model.Athlete;
import com.jeuxolympiques.jo2024.persistence.AthleteRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AthleteServiceImpl implements AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    @Override
    public Athlete getAthleteById(long id) {
        log.info("Récupération de l'athlète avec l'ID : {}", id);
        return athleteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Athlete> getAllAthletes() {
        log.info("Récupération de tous les athlètes");
        return athleteRepository.findAll();
    }

    @Override
    public Athlete saveAthlete(Athlete athlete) {
        log.info("Processus d'enregistrement d'un nouvel athlète : {}", athlete);
        return athleteRepository.save(athlete);
    }

    @Override
    public Athlete updateAthlete(Athlete athlete) {
        log.info("Mise à jour en cours de l'athlète {} ...", athlete.getFirstName());
        Athlete existingAthlete = athleteRepository.findById(athlete.getId())
                .orElseThrow(() -> new RuntimeException("Athlete not found"));

        existingAthlete.setFirstName(athlete.getFirstName());
        existingAthlete.setLastName(athlete.getLastName());
        existingAthlete.setSport(athlete.getSport());
        existingAthlete.setBiography(athlete.getBiography());
        existingAthlete.setAchievements(athlete.getAchievements());
        existingAthlete.setSpecialSkills(athlete.getSpecialSkills());
        existingAthlete.setSocialMediaLinks(athlete.getSocialMediaLinks());

        return athleteRepository.save(existingAthlete);
    }

    @Override
    public void deleteAthlete(Long id) {
        log.info("Suppression de l'athlète avec l'ID : {}", id);
        athleteRepository.deleteById(id);
    }
}
