package com.jeuxolympiques.jo2024.service.UserService;

import com.jeuxolympiques.jo2024.Exception.PasswordLengthException;
import com.jeuxolympiques.jo2024.model.User.User;
import com.jeuxolympiques.jo2024.persistence.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserRegistrationService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        log.info("Processus d'enregistrement de l'utilisateur : {} ...", user);

        if (user.getPassword().length() < 3) {
            log.error("Le mot de passe doit contenir au moins 3 caractères.");
        }

        if (!user.getEmail().contains("@") || !user.getEmail().contains(".")) {
            log.error("Votre email est invalide, veuillez réessayer !", user.getEmail());
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            log.error("Votre email est déjà utilisé, veuillez réessayer !", user.getEmail());
        }
        String passwordHashed = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHashed);
        userRepository.save(user);
    }
}
