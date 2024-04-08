package com.jeuxolympiques.jo2024.service.userService;

import com.jeuxolympiques.jo2024.exception.EmailAlreadyExistsException;
import com.jeuxolympiques.jo2024.exception.PasswordLengthException;
import com.jeuxolympiques.jo2024.model.user.User;
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

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Votre email est déjà utilisé, veuillez réessayer !");
        }

        if (user.getPassword().length() < 3) {
            throw new PasswordLengthException("Le mot de passe doit contenir au moins 3 caractères.");
        }

        String passwordHashed = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHashed);
        userRepository.save(user);
    }
}
