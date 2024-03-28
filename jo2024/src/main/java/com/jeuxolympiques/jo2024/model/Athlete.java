package com.jeuxolympiques.jo2024.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Athlete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Nom is required")
    private String nom;

    @Column
    private String biographie;

    @Column
    private String réalisations;

    @Column
    private String compétences;

    @Column
    private String réseauxSociaux;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getBiographie() {
        return biographie;
    }

    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }

    public String getRéalisations() {
        return réalisations;
    }

    public void setRéalisations(String réalisations) {
        this.réalisations = réalisations;
    }

    public String getCompétences() {
        return compétences;
    }

    public void setCompétences(String compétences) {
        this.compétences = compétences;
    }

    public String getRéseauxSociaux() {
        return réseauxSociaux;
    }

    public void setRéseauxSociaux(String réseauxSociaux) {
        this.réseauxSociaux = réseauxSociaux;
    }

   
}
