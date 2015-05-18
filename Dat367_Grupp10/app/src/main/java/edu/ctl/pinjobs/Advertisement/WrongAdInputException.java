package edu.ctl.pinjobs.Advertisement;

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
