package fr.istic.vv;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DateTest {

    @Test
    public void testValidDateConstructor() {
        Date date = new Date(15, 3, 2024);
        assertEquals("15/03/2024", date.toString());
    }

    @Test
    public void testInvalidDateConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Date(30, 2, 2024);
        },"Date invalide car le 30 de Février n'existe pas");
    }



    @Test
    public void testValidDate() {
        assertTrue(Date.isValidDate(1, 1, 2024),"Date valide");
        assertTrue(Date.isValidDate(29, 2, 2024),"Date valide avec année bisextile");
        assertTrue(Date.isValidDate(31, 12, 2024),"Date valide"); 
    }

    @Test
    public void testInvalidMonth() {
        assertFalse(Date.isValidDate(1, 0, 2025),"Mois invalide car <1");
        assertFalse(Date.isValidDate(1, 13, 2025),"Mois invalide car >12");
    }

    @Test
    public void testInvalidDay() {
        assertFalse(Date.isValidDate(0, 2, 2023),"Jour invalide en fevrier car <1"); 
        assertFalse(Date.isValidDate(32, 1, 2024),"Jour invalide en janvier car >31");
    }

    @Test
    public void testNegativeYear() {
        assertFalse(Date.isValidDate(1, 1, -2024),"Année invalide car négative");
    }





    @Test
    public void testLeapYear() {
        assertTrue(Date.isLeapYear(2024),"Année bisextile");
        assertFalse(Date.isLeapYear(2023),"Année non bisextile");
        assertFalse(Date.isLeapYear(1900),"Année non bisextile");
        assertTrue(Date.isLeapYear(2000),"Année bisextile");
    }

    @Test
    public void testNextDate() {
        Date date = new Date(28, 2, 2024);
        assertEquals("29/02/2024", date.nextDate().toString(),"Année bisextile");
        date = new Date(31, 12, 2024);
        assertEquals("01/01/2025", date.nextDate().toString());
        date = new Date(28, 2, 2023);
        assertEquals("01/03/2023", date.nextDate().toString());
    }

    @Test
    public void testPreviousDate() {
        Date date = new Date(1, 3, 2024);
        assertEquals("29/02/2024", date.previousDate().toString(),"Année bisextile");
        date = new Date(1, 1, 2025);
        assertEquals("31/12/2024", date.previousDate().toString());
        date = new Date(2, 1, 2025);
        assertEquals("01/01/2025", date.previousDate().toString());
    }

    @Test
    public void testCompareTo() {
        Date date1 = new Date(1, 1, 2024);
        Date date2 = new Date(2, 1, 2024);
        Date date3 = new Date(1, 1, 2024);
        Date date4 = new Date(2, 1, 2023);
        Date date5 = new Date(2, 2, 2023);

        assertTrue(date1.compareTo(date2) < 0, "date1 devrait être antérieure à date2");
        assertTrue(date2.compareTo(date1) > 0, "date2 devrait être postérieure à date1");
        assertEquals(0, date1.compareTo(date3), "date1 devrait être égale à date3");
        assertTrue(date1.compareTo(date4) > 0, "date1 devrait être postérieure à date4");
        assertTrue(date5.compareTo(date4) > 0, "date5 devrait être postérieure à date4");

        
        assertThrows(NullPointerException.class, () -> {
            date1.compareTo(null);
        });
    }



    @Test
    public void testEqualsSameObject() {
        Date date1 = new Date(15, 3, 2024);
        assertTrue(date1.equals(date1), "Un même objet devrait être égale à lui-même.");
    }


    @Test
    public void testEqualsDifferentType() {
        Date date1 = new Date(15, 3, 2024);
        String notADate = "N'est pas une Date ";
        assertFalse(date1.equals(notADate), "Un objet Date ne doit pas être égal à un objet d'un type différent.");
    }

    @Test
    public void testEqualsIdenticalDates() {
        Date date1 = new Date(15, 3, 2024);
        Date date2 = new Date(15, 3, 2024);
        assertTrue(date1.equals(date2), "Deux dates avec le même jour, mois et année doivent être égales.");
    }

    @Test
    public void testEqualsDifferentDay() {
        Date date1 = new Date(15, 3, 2024);
        Date date2 = new Date(16, 3, 2024);
        assertFalse(date1.equals(date2), "Deux Date avec un jour différents ne doivent pas être égaux.");
    }

    @Test
    public void testEqualsDifferentMonth() {
        Date date1 = new Date(15, 3, 2024);
        Date date2 = new Date(15, 4, 2024);
        assertFalse(date1.equals(date2), "Deux Date avec un mois différents ne doivent pas être égaux.");
    }

    @Test
    public void testEqualsDifferentYear() {
        Date date1 = new Date(15, 3, 2024);
        Date date2 = new Date(15, 3, 2025);
        assertFalse(date1.equals(date2), "Deux Date avec une année différentes ne doivent pas être égaux.");
    }

    @Test
    public void testEqualsCompletelyDifferent() {
        Date date1 = new Date(15, 3, 2024);
        Date date2 = new Date(16, 4, 2025);
        assertFalse(date1.equals(date2), "Deux Date avec un jour, un mois et une année différents ne doivent pas être égaux.");
    }






    @Test
    public void testHashCodeConsistency() {
        Date date = new Date(15, 3, 2024);
        int initialHashCode = date.hashCode();
        assertEquals(initialHashCode, date.hashCode(), "hashCode devrait renvoyer la même valeur à chaque appel.");
    }

    @Test
    public void testHashCodeEqualObjects() {
        Date date1 = new Date(15, 3, 2024);
        Date date2 = new Date(15, 3, 2024);
        assertEquals(date1.hashCode(), date2.hashCode(), "Les objets égaux doivent avoir le même hashCode.");
    }

    @Test
    public void testHashCodeDifferentObjects() {
        Date date1 = new Date(15, 3, 2024);
        Date date2 = new Date(16, 3, 2024);
        Date date3 = new Date(15, 4, 2024);
        
        assertNotEquals(date1.hashCode(), date2.hashCode(), "Des objets différents devraient avoir des valeurs de hashCode différentes.");
        assertNotEquals(date1.hashCode(), date3.hashCode(), "Des objets différents devraient avoir des valeurs de hashCode différentes.");
    }
}
