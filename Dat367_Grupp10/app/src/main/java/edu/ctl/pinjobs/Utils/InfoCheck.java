package edu.ctl.pinjobs.Utils;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Created by Isaac on 2015-04-01.
 * help class for checking valid inputs
 */
public class InfoCheck implements Serializable {

    public boolean isAlphabetic(String name) {
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

}
