package com.jeuxolympiques.jo2024.model.user;

import org.junit.jupiter.api.Test;

import com.jeuxolympiques.jo2024.model.Role;
import com.jeuxolympiques.jo2024.model.user.User;

import static org.junit.jupiter.api.Assertions.*;

public class UserTests {

    @Test
    public void testGettersAndSetters() {
        User user = new User();

        // Utilisez des valeurs arbitraires pour les tests
        String email = "test@example.com";
        String password = "password";
        String firstName = "John";
        String lastName = "Doe";
        String phoneNumber = "123456789";
        String city = "Paris";

        user.setId(1L);
        assertEquals(1L, user.getId());

        user.setEmail(email);
        assertEquals(email, user.getEmail());

        user.setPassword(password);
        assertEquals(password, user.getPassword());

        user.setFirstName(firstName);
        assertEquals(firstName, user.getFirstName());

        user.setLastName(lastName);
        assertEquals(lastName, user.getLastName());

        user.setPhoneNumber(phoneNumber);
        assertEquals(phoneNumber, user.getPhoneNumber());

        user.setCity(city);
        assertEquals(city, user.getCity());

        // Vous pouvez tester les autres getters et setters de manière similaire
    }

    @Test
    public void testConstructorWithParameters() {
        String email = "test@example.com";
        String password = "password";
        String firstName = "John";
        String lastName = "Doe";
        String phoneNumber = "123456789";
        String city = "Paris";

        User user = new User(email, password, firstName, lastName, phoneNumber, city);

        // Vérifiez si les valeurs ont été correctement initialisées par le constructeur
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(phoneNumber, user.getPhoneNumber());
        assertEquals(city, user.getCity());
        assertEquals(Role.USER, user.getRole()); // Vérifiez également le rôle qui doit être USER par défaut
    }
}

