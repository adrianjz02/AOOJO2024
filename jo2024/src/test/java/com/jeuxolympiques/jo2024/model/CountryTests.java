package com.jeuxolympiques.jo2024.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CountryTests {

    @Test
    public void testToString() {
        Country country = new Country("France", "flag.png", "Paris", 67000000L, "Eiffel Tower");
        country.setId(1L);

        String expectedToString = "Country{id=1, nameCountry='France', flag='flag.png', capital='Paris', population=67000000, relevantPoint='Eiffel Tower'}";
        assertEquals(expectedToString, country.toString());
    }

    @Test
    public void testAlternateConstructorWithParameters() {
        Long id = 1L;
        String nameCountry = "France";
        String flag = "flag.png";
        String capital = "Paris";
        Long population = 67000000L;
        String relevantPoint = "Eiffel Tower";

        Country country = new Country(id, nameCountry, flag, capital, population, relevantPoint);

        // Vérifiez que les valeurs ont été correctement initialisées
        assertEquals(id, country.getId());
        assertEquals(nameCountry, country.getNameCountry());
        assertEquals(flag, country.getFlag());
        assertEquals(capital, country.getCapital());
        assertEquals(population, country.getPopulation());
        assertEquals(relevantPoint, country.getRelevantPoint());
    }

    @Test
    public void testGettersAndSetters() {
        Country country = new Country();

        country.setId(1L);
        assertEquals(1L, country.getId());

        country.setNameCountry("France");
        assertEquals("France", country.getNameCountry());

        country.setFlag("flag.png");
        assertEquals("flag.png", country.getFlag());

        country.setCapital("Paris");
        assertEquals("Paris", country.getCapital());

        country.setPopulation(67000000L);
        assertEquals(67000000L, country.getPopulation());

        country.setRelevantPoint("Eiffel Tower");
        assertEquals("Eiffel Tower", country.getRelevantPoint());
    }
}
