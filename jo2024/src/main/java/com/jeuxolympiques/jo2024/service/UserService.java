package com.jeuxolympiques.jo2024.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.jeuxolympiques.jo2024.model.User;
import com.jeuxolympiques.jo2024.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public void saveUser(User user) {
        log.info("Processus d'enregistrement de l'utilisateur : {} ...", user);

        if (!user.getEmail().contains("@") || !user.getEmail().contains(".")) {
            log.error("Votre email est invalide, veuillez réessayer !", user.getEmail());
        }
        Optional<User> userOptional = this.userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            log.error("Votre email est déjà utilisé, veuillez réessayer !", user.getEmail());
        }
        String passwordHashed = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHashed);
        this.userRepository.save(user);
        log.info("Utilisateur enregistré avec succès : {}", user);
    }
    
    
    //Cherche un user dans la bdd en fonction du mail donné et il va comparer les mdp
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Tentative de chargement de l'utilisateur avec l'email : {}", email);
        User user = this.userRepository.findByEmail(email)
            .orElseThrow(() -> {
                log.error("Aucun utilisateur ne correspond à cet email : {}", email);
                return new UsernameNotFoundException("Aucun utilisateur ne correspond à cet email : " + email);
            });
        return user;
    }
    
}
