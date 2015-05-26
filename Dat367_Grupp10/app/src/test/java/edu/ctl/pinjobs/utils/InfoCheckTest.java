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
        assertTrue(infoCheck.containsOnlyLettersOrNumbers("kjasbf,,,   n823864"));
    }
}
