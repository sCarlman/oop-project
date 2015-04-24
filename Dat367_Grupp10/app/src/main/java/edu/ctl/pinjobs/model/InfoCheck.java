package edu.ctl.pinjobs.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Created by Isaac on 2015-04-01.
 * help class for checking valid inputs
 */
public class InfoCheck {

    public static boolean isAlphabetic(String name) {
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmailCorrect(String email) {
        if (email != null || email != "") {
            Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
            Matcher m = p.matcher(email);
            boolean emailValid = m.matches();
            return emailValid;
        }else {
            return false;
        }
    }

    public static boolean isNumeric(String phone) {
        char[] chars = phone.toCharArray();
        //method requires symbols like "-" to be consumed
        for (char c : chars) {
            if(!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
