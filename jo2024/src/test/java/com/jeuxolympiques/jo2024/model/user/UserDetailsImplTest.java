package com.jeuxolympiques.jo2024.model.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import com.jeuxolympiques.jo2024.model.Role;

import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

public class UserDetailsImplTest {

    @Test
    public void testGetAuthorities() {
        // Créez un utilisateur avec un rôle spécifique
        User user = new User();
        user.setRole(Role.ADMIN);

        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        // Vérifiez si la méthode getAuthorities() renvoie le rôle attendu
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        boolean authorityFound = false;
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                authorityFound = true;
                break;
            }
        }
        assertTrue(authorityFound);
    }

    @Test
    public void testGetPassword() {
        // Créez un utilisateur avec un mot de passe spécifique
        String password = "password";
        User user = new User();
        user.setPassword(password);

        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        // Vérifiez si la méthode getPassword() renvoie le mot de passe attendu
        assertEquals(password, userDetails.getPassword());
    }

    @Test
    public void testGetUsername() {
        // Créez un utilisateur avec un email spécifique
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);

        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        // Vérifiez si la méthode getUsername() renvoie l'email attendu
        assertEquals(email, userDetails.getUsername());
    }

    @Test
    public void testIsAccountNonExpired() {
        // Créez un utilisateur
        User user = new User();
        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        // Vérifiez si la méthode isAccountNonExpired() renvoie true
        assertTrue(userDetails.isAccountNonExpired());
    }

    @Test
    public void testIsAccountNonLocked() {
        // Créez un utilisateur
        User user = new User();
        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        // Vérifiez si la méthode isAccountNonLocked() renvoie true
        assertTrue(userDetails.isAccountNonLocked());
    }

    @Test
    public void testIsCredentialsNonExpired() {
        // Créez un utilisateur
        User user = new User();
        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        // Vérifiez si la méthode isCredentialsNonExpired() renvoie true
        assertTrue(userDetails.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        // Créez un utilisateur
        User user = new User();
        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        // Vérifiez si la méthode isEnabled() renvoie true
        assertTrue(userDetails.isEnabled());
    }

    @Test
    public void testConstructorWithParameters() {
        Long id = 1L;
        String email = "test@example.com";
        String password = "password";
        String firstName = "John";
        String lastName = "Doe";
        String phoneNumber = "123456789";
        String city = "Paris";
        Role role = Role.ADMIN;

        User user = new User(id, email, password, firstName, lastName, phoneNumber, city, null, role);

        // Vérifiez si les valeurs ont été correctement initialisées par le constructeur
        assertEquals(id, user.getId());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(phoneNumber, user.getPhoneNumber());
        assertEquals(city, user.getCity());
        assertEquals(role, user.getRole());
    }
}
