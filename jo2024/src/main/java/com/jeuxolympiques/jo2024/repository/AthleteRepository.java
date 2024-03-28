package com.jeuxolympiques.jo2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeuxolympiques.jo2024.model.Athlete;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire
}
