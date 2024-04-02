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

    private String nameCountry;

    private String flag; // Ajout du drapeau

    private String capital; // Ajout de la capitale

    private Long population; // Ajout de la population

    private String relevantPoint; // Ajout des faits intéressants

    public Country() {
    }

    public Country(String nameCountry, String flag, String capital, Long population, String relevantPoint) {
        this.nameCountry = nameCountry;
        this.flag = flag;
        this.capital = capital;
        this.population = population;
        this.relevantPoint = relevantPoint;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getnameCountry() {
        return nameCountry;
    }

    public void setnameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
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

    public String getrelevantPoint() {
        return relevantPoint;
    }

    public void setrelevantPoint(String relevantPoint) {
        this.relevantPoint = relevantPoint;
    }
}
