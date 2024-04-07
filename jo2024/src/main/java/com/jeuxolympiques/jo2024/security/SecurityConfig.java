package com.jeuxolympiques.jo2024.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jeuxolympiques.jo2024.handler.failureHandler.authenticationFailureHandler.LoginFailureHandler;
import com.jeuxolympiques.jo2024.handler.successHandler.LoginSuccessHandler;
import com.jeuxolympiques.jo2024.handler.successHandler.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        public static final String[] PUBLIC_URLS = {
                "/accueil", "/inscription", "/login"
        };
    
        public static final String[] PRIVATE_URLS = {
                "/countries", "/countries/**", "/athletes", "/athletes/**"
        };

        @Bean
        public LoginSuccessHandler loginSuccessHandler() {
                return new LoginSuccessHandler();
        }

        @Bean
        public LogoutSuccessHandler logoutSuccessHandler() {
                return new LogoutSuccessHandler();
        }

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
                                                .successHandler(loginSuccessHandler())
                                                .permitAll()
                                        )
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessHandler(logoutSuccessHandler())
                                                .permitAll()
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
}
