package com.test;
import org.junit.Test;

import edu.ctl.pinjobs.Utils.InfoCheck;

import static org.junit.Assert.*;

/**
 * Created by filiplarsson on 15-05-14.
 */
public class InfoCheckTest {

    InfoCheck infoCheck = new InfoCheck();

    @Test
    public void testIsAlphabetic() throws Exception {

        String text =  "ExampleString";
        Boolean check = infoCheck.isAlphabetic(text);
        assertEquals(check, true);

    }
/*
    @Test
    public void testIsEmailCorrect() throws Exception {

        String testEmail = "pinjobs@test.com";
        //Boolean check = infoCheck.isEmailCorrect(testEmail);
       // assertEquals(check, true);

    }

    @Test
    public void testIsNumeric() throws Exception {

        String testPhone = "0708963452";
        Boolean check = infoCheck.isNumeric(testPhone);
        assertEquals(check, true);

    }
*/
}
