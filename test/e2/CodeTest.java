package e2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeTest {
    public static char[][] keypad1 = {{'1'}};

    public static char[][] keypad2
            = {{'1', '2'},
               {'3', '4'}};

    public static char[][] keypad3
            = {{'1', '2', '3'},
               {'4', '5', '6'},
               {'7', '8', '9'}};

    public static char[][] keypad4
            = {{'1', '2', '3'},
               {'4', '5', '6'},
               {'7', '8', '9'},
               {'0', 'A', 'B'}};

    public static char[][] keypad5
            = {{'1', '4', '7', '0', 'C'},
               {'2', '5', '8', 'A', 'D'},
               {'3', '6', '9', 'B', 'E'}};

    public static char[][] keypad6
            = {{'1', '2', '3', '4', '5'}};

    public static char[][] keypad7
            = {{'1'},
               {'2'},
               {'3'}};

    public static String[] input0 = {"RD", "DRUU", "LLD", "D"};
    public static String[] input1 = {"ULL", "RRDDD", "LURDL", "UUUUD"};
    public static String[] input2 = {"URLLLRD", "LLRRDDUULR", "LLUUDDRR", "LRULRU", "LDRULRDU"};

    @Test
    void isKeypadValidTest() {
        char[][] wrongDuplicates
                = {{'1', '2', '3'},
                {'4', '5', '6'},
                {'7', '8', '1'}};

        char[][] wrongNotSequence
                = {{'1', '2', '4'},
                {'3', '5', '6'},
                {'8', '9', '7'},
                {'0', 'A', 'B'}};

        char[][] wrongNull
                = {{'1', '2', '4'},
                null,
                {'8', '9', '7'},
                {'0', 'A', 'B'}};

        char[][] wrongNoValidChar
                = {{'1', '2', '3'},
                {'4', 'f', '6'},
                {'7', '8', '1'}};

        assertFalse(Code.isKeypadValid(wrongDuplicates));
        assertFalse(Code.isKeypadValid(wrongNotSequence));
        assertFalse(Code.isKeypadValid(wrongNull));
        assertFalse(Code.isKeypadValid(wrongNoValidChar));
        assertFalse(Code.isKeypadValid(null));

        assertTrue(Code.isKeypadValid(keypad1));
        assertTrue(Code.isKeypadValid(keypad2));
        assertTrue(Code.isKeypadValid(keypad3));
        assertTrue(Code.isKeypadValid(keypad4));
        assertTrue(Code.isKeypadValid(keypad5));
        assertTrue(Code.isKeypadValid(keypad6));
        assertTrue(Code.isKeypadValid(keypad7));
    }

    @Test
    void areMovementsValidTest() {
        String[] wrongLower = {"ULDD", "LRU", "dDd"};
        String[] wrongNumber = {"ULDD", "L1U", "DDD"};
        String[] wrongEmpty = {"", "L1U", "DDD"};
        String[] wrongNull = {"DUU", null, "DDD"};

        assertFalse(Code.areMovementsValid(wrongLower));
        assertFalse(Code.areMovementsValid(wrongNumber));
        assertFalse(Code.areMovementsValid(wrongEmpty));
        assertFalse(Code.areMovementsValid(wrongNull));
        assertFalse(Code.areMovementsValid(null));

        assertTrue(Code.areMovementsValid(input0));
        assertTrue(Code.areMovementsValid(input1));
        assertTrue(Code.areMovementsValid(input2));
    }

    @Test
    void obtainCodeTest() {
        assertEquals("5347", Code.obtainCode(keypad3, input0));

        assertEquals("1111", Code.obtainCode(keypad1, input1));
        assertEquals("1433", Code.obtainCode(keypad2, input1));
        assertEquals("1985", Code.obtainCode(keypad3, input1));
        assertEquals("1BA5", Code.obtainCode(keypad4, input1));
        assertEquals("1965", Code.obtainCode(keypad5, input1));
        assertEquals("1322", Code.obtainCode(keypad6, input1));
        assertEquals("1332", Code.obtainCode(keypad7, input1));

        assertEquals("11111", Code.obtainCode(keypad1, input2));
        assertEquals("42422", Code.obtainCode(keypad2, input2));
        assertEquals("53933", Code.obtainCode(keypad3, input2));
        assertEquals("56933", Code.obtainCode(keypad4, input2));
        assertEquals("57977", Code.obtainCode(keypad5, input2));
        assertEquals("23333", Code.obtainCode(keypad6, input2));
        assertEquals("21311", Code.obtainCode(keypad7, input2));
    }
}