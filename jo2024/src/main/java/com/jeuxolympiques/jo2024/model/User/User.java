package com.jeuxolympiques.jo2024.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.Entity;


import com.jeuxolympiques.jo2024.model.Athlete;
import com.jeuxolympiques.jo2024.model.Role;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phoneNumber;

    @Column
    private String city;

    @ManyToMany
    @JoinTable(
        name = "user_favorite_athletes",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "athlete_id")
    )
    private Set<Athlete> favoriteAthletes = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;

    // Constructeur pour créer un utilisateur avec le rôle USER par défaut
    public User(String email, String password, String firstName, String lastName, String phoneNumber, String city) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.role = Role.USER;
        this.favoriteAthletes = new HashSet<>(); // Initialiser le champ favoriteAthletes
    }

    
}