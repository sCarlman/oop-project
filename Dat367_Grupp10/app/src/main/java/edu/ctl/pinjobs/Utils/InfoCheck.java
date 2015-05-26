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

    //Checks if a String contains signs like ! ? # € % etc
    public boolean isAnySignsInString(String s){
        char[] chars = s.toCharArray();
        for (char c : chars){
            if(Character.isLetterOrDigit(c)){
                return false;
            }
        }
        return true;
    }

    //Checks if a string contains a coma character
    public boolean containsComa(String s){
        char[] chars = s.toCharArray();
        boolean containsAnyComa = false;
        for (char c : chars){
            if(c == ','){
                containsAnyComa = true;
            }
        }
        return containsAnyComa;
    }
    //Checks if a string contains any letters
    public boolean containsLetters(String s){
        char[] chars = s.toCharArray();
        boolean containsAnyLetter = false;
        for (char c : chars){
            if(Character.isLetter(c)){
                containsAnyLetter = true;
            }
        }
        return containsAnyLetter;
    }

    public boolean containsOnlyLettersOrNumbers(String s){
        char[] chars = s.toCharArray();

        boolean containsOnlyLettersAndNumbers = false;
        for (char c : chars){
            if(Character.isLetterOrDigit(c) || Character.isWhitespace(c) || c == ',') {
                containsOnlyLettersAndNumbers = true;
            }else{
                containsOnlyLettersAndNumbers = false;
                break;

            }
        }
        return containsOnlyLettersAndNumbers;
    }

    //Checks if a string contains spaces
    public boolean containsSpaces(String password){
        char[] chars = password.toCharArray();
        boolean containsAnySpaces = false;

        for (char c : chars) {
            if(Character.isWhitespace(c)) {
                containsAnySpaces = true;
            }
        }
        return containsAnySpaces;
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
        return true;
    }
}
