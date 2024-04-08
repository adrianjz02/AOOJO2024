package com.jeuxolympiques.jo2024.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AthleteTests {

    @Test
    public void testToString() {
        Athlete athlete = new Athlete("John", "Doe");
        athlete.setId(1L);
        athlete.setPhoto("photo.jpg");
        athlete.setSport("Swimming");
        athlete.setBiography("Biography");
        athlete.setAchievements("Achievements");
        athlete.setSpecialSkills("SpecialSkills");
        athlete.setSocialMediaLinks("SocialMediaLinks");

        String expectedToString = "Athlete{id=1, photo='photo.jpg', firstName='John', lastName='Doe', sport='Swimming', biography='Biography', achievements='Achievements', specialSkills='SpecialSkills', socialMediaLinks='SocialMediaLinks'}";
        assertEquals(expectedToString, athlete.toString());
    }

    @Test
    public void testGettersAndSetters() {
        Athlete athlete = new Athlete();

        athlete.setId(1L);
        assertEquals(1L, athlete.getId());

        athlete.setPhoto("photo.jpg");
        assertEquals("photo.jpg", athlete.getPhoto());

        athlete.setFirstName("John");
        assertEquals("John", athlete.getFirstName());

        athlete.setLastName("Doe");
        assertEquals("Doe", athlete.getLastName());

        athlete.setSport("Swimming");
        assertEquals("Swimming", athlete.getSport());

        athlete.setBiography("Biography");
        assertEquals("Biography", athlete.getBiography());

        athlete.setAchievements("Achievements");
        assertEquals("Achievements", athlete.getAchievements());

        athlete.setSpecialSkills("SpecialSkills");
        assertEquals("SpecialSkills", athlete.getSpecialSkills());

        athlete.setSocialMediaLinks("SocialMediaLinks");
        assertEquals("SocialMediaLinks", athlete.getSocialMediaLinks());
    }
}

