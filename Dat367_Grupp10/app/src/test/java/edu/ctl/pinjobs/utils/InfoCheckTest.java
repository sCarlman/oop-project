package edu.ctl.pinjobs.utils;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Albertsson on 15-05-22.
 */
public class InfoCheckTest {

    InfoCheck infoCheck;

    @Before
    public void before(){
        infoCheck = new InfoCheck();
    }

    @Test
    public void testIfStringIsEmpty(){

        assertTrue(infoCheck.isEmpty(""));

        assertTrue(infoCheck.isEmpty(" "));

        assertTrue(infoCheck.isEmpty("        "));

        assertFalse(infoCheck.isEmpty(" C"));

        assertFalse(infoCheck.isEmpty("3"));

        assertTrue(infoCheck.isEmpty(null));
    }

    @Test
    public void testIsAlphabetic(){
    assertTrue(infoCheck.isAlphabetic("ABCDEFGHIJK"));
        assertTrue(infoCheck.isAlphabetic("abcdefghijk"));
        assertTrue(infoCheck.isAlphabetic("jasbAKJSFnasfjAJSNF"));
        assertTrue(infoCheck.isAlphabetic("hafkj  akjsbf   sdfkjsdb "));
        assertFalse(infoCheck.isAlphabetic(""));
        assertFalse(infoCheck.isAlphabetic(null));
        assertFalse(infoCheck.isAlphabetic("2347698"));
        assertFalse(infoCheck.isAlphabetic("asdasd24325fsd435"));
        assertFalse(infoCheck.isAlphabetic(")(#€&%????!?!"));
        assertFalse(infoCheck.isAlphabetic("kjdsf!!!lksdnaf(&%"));
    }

    @Test
    public void testContainsComa(){
        assertTrue(infoCheck.containsComa(","));
        assertTrue(infoCheck.containsComa("asf56gsdg,"));
        assertTrue(infoCheck.containsComa(",asf11"));
        assertTrue(infoCheck.containsComa("a2345dasf,sdfsdf"));
        assertTrue(infoCheck.containsComa(",,,,,,,"));
        assertFalse(infoCheck.containsComa(null));
        assertFalse(infoCheck.containsComa(""));
        assertFalse(infoCheck.containsComa("(/#&€=asfjkb98435"));
    }

    @Test
    public void testContainsLetters(){
        assertTrue(infoCheck.containsLetters("assaASDsd"));
        assertTrue(infoCheck.containsLetters("ASkfk324578"));
        assertTrue(infoCheck.containsLetters("adk(&&&€/"));
        assertTrue(infoCheck.containsLetters("    s   "));
        assertFalse(infoCheck.containsLetters(""));
        assertFalse(infoCheck.containsLetters(null));
        assertFalse(infoCheck.containsLetters("2349826#(/#€"));
    }

    @Test
    public void testContainsOnlyLettersNumbersWhitespaceOrComa(){
        assertTrue(infoCheck.containsOnlyLettersNumbersWhitespaceOrComa("asdsdf"));
        assertTrue(infoCheck.containsOnlyLettersNumbersWhitespaceOrComa("2342345"));
        assertTrue(infoCheck.containsOnlyLettersNumbersWhitespaceOrComa("     "));
        assertTrue(infoCheck.containsOnlyLettersNumbersWhitespaceOrComa(",,,,,"));
        assertTrue(infoCheck.containsOnlyLettersNumbersWhitespaceOrComa("asd324,,, 234fd, sdf"));
        assertFalse(infoCheck.containsOnlyLettersNumbersWhitespaceOrComa(null));
        assertFalse(infoCheck.containsOnlyLettersNumbersWhitespaceOrComa(""));
        assertFalse(infoCheck.containsOnlyLettersNumbersWhitespaceOrComa("asd!!???=?=)"));
    }

    @Test
    public void testContainsSpaces(){
        assertTrue(infoCheck.containsSpaces(" "));
        assertTrue(infoCheck.containsSpaces("     "));
        assertTrue(infoCheck.containsSpaces("asdasd  asdd"));
        assertFalse(infoCheck.containsSpaces(null));
        assertFalse(infoCheck.containsSpaces(""));
        assertFalse(infoCheck.containsSpaces("osf8734???"));
    }
}
