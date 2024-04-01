package com.jeuxolympiques.jo2024.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String flag; // Ajout du drapeau

    private String capital; // Ajout de la capitale

    private Long population; // Ajout de la population

    private String interestingFacts; // Ajout des faits intéressants

    public Country() {
    }

    public Country(String name, String flag, String capital, Long population, String interestingFacts) {
        this.name = name;
        this.flag = flag;
        this.capital = capital;
        this.population = population;
        this.interestingFacts = interestingFacts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public String getInterestingFacts() {
        return interestingFacts;
    }

    public void setInterestingFacts(String interestingFacts) {
        this.interestingFacts = interestingFacts;
    }
}
