package com.jeuxolympiques.jo2024.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/*
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

        @SuppressWarnings({ "deprecation", "removal" })
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                return
                        http
                        .csrf(AbstractHttpConfigurer::disable)
                        .logout(logout -> logout
                        .logoutUrl("/logout") // Spécifiez l'URL de déconnexion
                        .logoutSuccessUrl("/login?logout") // Rediriger vers la page de connexion après la déconnexion
                        .invalidateHttpSession(true) // Invalider la session HTTP existante
                        .deleteCookies("JSESSIONID") // Supprimer les cookies de session
                        .permitAll() // Page de déconnexion accessible à tous
                    
                        )
                        .authorizeRequests(authorizeRequests -> authorizeRequests
                                .anyRequest().permitAll()
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
                daoAuthenticationProvider.setUserDetailsService(userDetailsService);
                daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
                return daoAuthenticationProvider;
        }

        // Ajout de la méthode de déconnexion
        @Bean
        public HttpStatusReturningLogoutSuccessHandler logoutSuccessHandler() {
                return new HttpStatusReturningLogoutSuccessHandler() {
                        @Override
                        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
                                log.info("La méthode onLogoutSuccess est appelée lors de la déconnexion.");

                        if (authentication != null) {
                                // Déconnexion réussie
                                log.info("Déconnexion réussie pour l'utilisateur : {}", authentication.getName());
                        } else {
                                // Déconnexion échouée
                                log.error("Échec de la déconnexion : Impossible de récupérer l'authentification de l'utilisateur");
                        }
                        super.onLogoutSuccess(request, response, authentication);
                        }
                };
        }
} */
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
        .csrf(AbstractHttpConfigurer::disable) // Désactive le CSRF
            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/").authenticated()
                .anyRequest().permitAll()
            )
            .formLogin(login -> login
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/accueil?loginSuccess=true") 
                .permitAll())
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler()) // Ajoutez cette ligne
                .permitAll())
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

        
        
        // @Bean
        // public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        //         log.info("Processus d'authentification de l'utilisateur ...");
        //         DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        //         daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        //         daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
        //         return daoAuthenticationProvider;
        // }

        @Bean
        public HttpStatusReturningLogoutSuccessHandler logoutSuccessHandler() {
        return new HttpStatusReturningLogoutSuccessHandler() {
        @Override
        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
            log.info("La méthode onLogoutSuccess est appelée lors de la déconnexion.");
            log.info("Déconnexion réussie pour l'utilisateur : {}", (authentication != null ? authentication.getName() : "Utilisateur inconnu"));

            response.sendRedirect("/accueil?logoutSuccess=true");
        }
    };
        }
}
