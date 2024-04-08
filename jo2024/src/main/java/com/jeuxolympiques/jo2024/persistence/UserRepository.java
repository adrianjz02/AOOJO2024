package com.jeuxolympiques.jo2024.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeuxolympiques.jo2024.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional <User> findByEmail(String email);
}