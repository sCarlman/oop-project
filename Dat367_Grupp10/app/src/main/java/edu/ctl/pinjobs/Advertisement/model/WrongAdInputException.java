package edu.ctl.pinjobs.advertisement.model;

/**
 * Created by Filips on 5/18/2015.
 */
public class WrongAdInputException extends Exception {
    private String source;
    public WrongAdInputException(String source){this.source = source;}
    public String getName(){
        return source;
    }
}
