package edu.ctl.pinjobs.model;

import edu.ctl.pinjobs.profile.Profile;

/**
 * Created by filiplarsson on 15-04-19.
 */
public class LoggedIn {
    public static boolean inLoggad = false;
    public static Profile valdProfil;
    public static void loggaIn(){
        inLoggad = true;
    }
    public static boolean isInLoggad(){
        return inLoggad;
    }
    public static void chosenProfile(Profile profile){
        valdProfil = profile;
    }
}
