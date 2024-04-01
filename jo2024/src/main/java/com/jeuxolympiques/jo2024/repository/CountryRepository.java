package com.jeuxolympiques.jo2024.repository;

import com.jeuxolympiques.jo2024.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
