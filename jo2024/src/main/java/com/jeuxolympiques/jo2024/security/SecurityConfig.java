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

import com.jeuxolympiques.jo2024.Handler.AuthenticationFailureHandler.InscriptionFailureHandler;
import com.jeuxolympiques.jo2024.Handler.AuthenticationFailureHandler.LoginFailureHandler;

import org.springframework.security.core.Authentication;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

        public static final String[] PUBLIC_URLS = {
                "/accueil", "/inscription", "/login"
        };
    
        public static final String[] PRIVATE_URLS = {
                "/countries", "/athletes"
        };

        public static final String LOGIN_SUCCESS_URL = "/accueil?loginSuccess=true";
        public static final String LOGOUT_SUCCESS_URL = "/accueil?logoutSuccess=true";


        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                return http
                                .csrf(AbstractHttpConfigurer::disable) // Désactive le CSRF
                                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                                                .requestMatchers(PRIVATE_URLS).authenticated()
                                                .requestMatchers(PUBLIC_URLS).permitAll()
                                                .anyRequest().permitAll())
                                .formLogin(login -> login
                                                .loginPage("/login")
                                                .loginProcessingUrl("/login")
                                                .failureHandler(new LoginFailureHandler())
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
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public HttpStatusReturningLogoutSuccessHandler logoutSuccessHandler() {
                return new HttpStatusReturningLogoutSuccessHandler() {
                        @Override
                        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
                                log.info("La méthode onLogoutSuccess est appelée lors de la déconnexion.");
                                log.info("L'utilisateur : {} a bien été déconnecté !",
                                                (authentication != null ? authentication.getName()
                                                                : "Utilisateur inconnu"));

                                response.sendRedirect(LOGOUT_SUCCESS_URL);
                        }
                };
        }
}
