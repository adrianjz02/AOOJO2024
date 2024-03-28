package com.jeuxolympiques.jo2024.model;

import java.util.List;
import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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

    @ElementCollection
    @CollectionTable(name = "realisations")
    private List<String> réalisations;

    @ElementCollection
    @CollectionTable(name = "competences")
    private List<String> compétences;

    @ElementCollection
    @CollectionTable(name = "reseaux_sociaux")
    @MapKeyColumn(name = "reseau")
    @Column(name = "lien")
    private Map<String, String> réseauxSociaux;

    // Getters and Setters

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

    public List<String> getRéalisations() {
        return réalisations;
    }

    public void setRéalisations(List<String> réalisations) {
        this.réalisations = réalisations;
    }

    public List<String> getCompétences() {
        return compétences;
    }

    public void setCompétences(List<String> compétences) {
        this.compétences = compétences;
    }

    public Map<String, String> getRéseauxSociaux() {
        return réseauxSociaux;
    }

    public void setRéseauxSociaux(Map<String, String> réseauxSociaux) {
        this.réseauxSociaux = réseauxSociaux;
    }
}
