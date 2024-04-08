package com.jeuxolympiques.jo2024.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameCountry;

    @Lob
    private String flag;

    private String capital;

    private Long population;

    private String relevantPoint;

    // Constructeur avec paramètres
    public Country(String nameCountry, String flag, String capital, Long population, String relevantPoint) {
        this.nameCountry = nameCountry;
        this.flag = flag;
        this.capital = capital;
        this.population = population;
        this.relevantPoint = relevantPoint;
    }

    // Constructeur par défaut
    public Country() {
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", nameCountry='" + nameCountry + '\'' +
                ", flag='" + flag + '\'' +
                ", capital='" + capital + '\'' +
                ", population=" + population +
                ", relevantPoint='" + relevantPoint + '\'' +
                '}';
    }

    
}
