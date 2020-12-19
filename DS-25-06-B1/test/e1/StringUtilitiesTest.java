package e1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilitiesTest {

    @Test
    void isValidMix() {
        assertTrue(StringUtilities.isValidMix("Bye", "World", "ByeWorld"));
        assertTrue(StringUtilities.isValidMix("Bye", "World", "WorldBye"));
        assertTrue(StringUtilities.isValidMix("Bye", "World", "BWyoerld"));
        assertTrue(StringUtilities.isValidMix("Bye", "World", "ByWorled"));

        assertFalse(StringUtilities.isValidMix("Bye", "World", "eyBdlroW"));
        assertFalse(StringUtilities.isValidMix("Bye", "World", "ByeWorlds"));
        assertFalse(StringUtilities.isValidMix("Bye", "World", "ByeWordl"));
        assertFalse(StringUtilities.isValidMix("Bye", "World", "HelloWorld"));
        assertFalse(StringUtilities.isValidMix("Bye", "World", "ByWorl"));
        assertFalse(StringUtilities.isValidMix("Bye", "World", "BByyeeWWoorrlldd"));
    }

    @Test
    void generateRandomValidMix() {
        assertTrue(StringUtilities.isValidMix("Bye", "World", StringUtilities.generateRandomValidMix("Bye", "World")));
        assertTrue(StringUtilities.isValidMix("Bye", "World", StringUtilities.generateRandomValidMix("Bye", "World")));
        assertTrue(StringUtilities.isValidMix("Bye", "World", StringUtilities.generateRandomValidMix("Bye", "World")));
        assertTrue(StringUtilities.isValidMix("Bye", "World", StringUtilities.generateRandomValidMix("Bye", "World")));
        assertTrue(StringUtilities.isValidMix("Bye", "World", StringUtilities.generateRandomValidMix("Bye", "World")));

        // Given a more or less long String, the result should be normally not equal
        assertNotEquals(StringUtilities.generateRandomValidMix("Bye", "World"), StringUtilities.generateRandomValidMix("Bye", "World"));
    }
}