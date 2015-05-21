package edu.ctl.pinjobs.profile.model;

/**
 * Created by Albertsson on 15-05-15.
 */
public class WrongInputExeption extends Exception {

    private String error;

    public WrongInputExeption(String error){
        this.error = error;
    }

    public String getError() {
        return error;
    }

}
