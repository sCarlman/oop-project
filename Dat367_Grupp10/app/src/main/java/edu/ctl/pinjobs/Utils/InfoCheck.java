package edu.ctl.pinjobs.utils;

import java.io.Serializable;

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

    //Checks if a string is empty or only contains " ".
    public boolean isEmpty(String s){
        if(s != null){
            char[] c = s.toCharArray();
            for(char c1: c){
                 if(c1 != ' '){
                     return false;
                 }
            }
            return true;
        }
        return false;
    }
}
