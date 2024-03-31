package com.jeuxolympiques.jo2024.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

        @SuppressWarnings("deprecation")
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                return
                        http
                                .csrf(AbstractHttpConfigurer::disable)
                                
                                .authorizeRequests(requests -> requests
                                        .requestMatchers("/success").authenticated() // Spécifiez le chemin d'accès vers la page de succès
                                        .anyRequest().permitAll() // Autoriser l'accès à toutes les autres URL
                                )
                        .build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
        
        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception { 
                return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
                log.info("Processus d'authentification de l'utilisateur ...");
                DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
                daoAuthenticationProvider.setUserDetailsService(userDetailsService );
                daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
                return daoAuthenticationProvider;
        }
    
}
