package com.jeuxolympiques.jo2024.service.UserService;

import com.jeuxolympiques.jo2024.model.User.User;
import com.jeuxolympiques.jo2024.model.User.UserDetailsImpl;
import com.jeuxolympiques.jo2024.persistence.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserAuthenticationService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Chargement de l'utilisateur pour la connexion ...");
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> {
                log.error("Aucun utilisateur ne correspond à cet email : {}", email);
                return new UsernameNotFoundException("Aucun utilisateur ne correspond à cet email : " + email);
            });
        return new UserDetailsImpl(user);
    }
}
