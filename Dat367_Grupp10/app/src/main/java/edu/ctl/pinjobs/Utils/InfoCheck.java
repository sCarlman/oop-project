package edu.ctl.pinjobs.utils;

import java.io.Serializable;

/**
 *
 * Created by Isaac on 2015-04-01.
 * help class for checking valid inputs
 */
public class InfoCheck implements Serializable {

    public boolean isAlphabetic(String name) {
        if(name == null || isEmpty(name)){
            return false;
        }
        String trimmedName = name.replaceAll("\\s+", "");
        char[] chars = trimmedName.toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }


    //Checks if a string contains a coma character
    public boolean containsComa(String s){
        if(s == null){
            return false;
        }
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
        if(s == null || isEmpty(s)){
            return false;
        }
        char[] chars = s.toCharArray();
        boolean containsAnyLetter = false;
        for (char c : chars){
            if(Character.isLetter(c)){
                containsAnyLetter = true;
            }
        }
        return containsAnyLetter;
    }

    public boolean containsOnlyLettersNumbersWhitespaceOrComa(String s){
        //uses "" instead of isEmpty since method should say true if containing spaces and not if string is empty
        if(s == null || s.equals("")){
            return false;
        }
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
        //uses "" instead of isEmpty since method should say true if containing spaces and not if string is empty
        if(password==null || password.equals("")){
            return false;
        }
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
