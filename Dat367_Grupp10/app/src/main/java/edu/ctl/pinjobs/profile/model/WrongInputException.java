package edu.ctl.pinjobs.profile.model;

/**
 * Created by Albertsson on 15-05-15.
 */
public class WrongInputException extends Exception {

    private String error;

    public WrongInputException(String error){
        this.error = error;
    }

    public String getError() {
        return error;
    }

}
