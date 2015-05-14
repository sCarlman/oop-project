package com.test;
import org.junit.Test;

import edu.ctl.pinjobs.model.InfoCheck;

import static org.junit.Assert.*;

/**
 * Created by filiplarsson on 15-05-14.
 */
public class InfoCheckTest {



    @Test
    public void testIsAlphabetic() throws Exception {

        String text =  "ExampleString";
        Boolean check = InfoCheck.isAlphabetic(text);
        assertEquals(check, true);

    }

    @Test
    public void testIsEmailCorrect() throws Exception {

        String testEmail = "pinjobs@test.com";
        Boolean check = InfoCheck.isEmailCorrect(testEmail);
        assertEquals(check, true);

    }

    @Test
    public void testIsNumeric() throws Exception {

        String testPhone = "0708963452";
        Boolean check = InfoCheck.isNumeric(testPhone);
        assertEquals(check, true);

    }

}
