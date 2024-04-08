package com.jeuxolympiques.jo2024.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String photo; // Ajout de la photo

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Lob
    private String sport;

    @Lob
    private String biography;

    @Lob
    private String achievements;

    @Lob
    private String specialSkills;

    @Column(length = 100)
    private String socialMediaLinks;

    @ManyToOne
    @JoinColumn // Utilisez le nom par défaut pour la jointure
    private Country country;

    public Athlete() {
    }

    public Athlete(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "id=" + id +
                ", photo='" + photo + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sport='" + sport + '\'' +
                ", biography='" + biography + '\'' +
                ", achievements='" + achievements + '\'' +
                ", specialSkills='" + specialSkills + '\'' +
                ", socialMediaLinks='" + socialMediaLinks + '\'' +
                '}';
    }

}