package edu.ctl.pinjobs.main;

import edu.ctl.pinjobs.profile.model.IProfile;

/**
 * Created by Albertsson on 15-05-05.
 */
public class UserModel {

    private static UserModel instance = null;
    private boolean isLoggedIn = false;
    private IProfile profile;

    private UserModel() {
        //Exists only to defeat instantiation
    }

    public static UserModel getInstance(){
        if(instance == null){
            instance = new UserModel();
        }
        return instance;
    }

    public void logIn(IProfile logInProfile){
        this.isLoggedIn = true;
        this.profile = logInProfile;
    }

    public void logOff(){
        this.isLoggedIn = false;
        this.profile = null;
    }

    public boolean getIsLoggedIn(){
        return isLoggedIn;
    }

    public IProfile getProfile(){
        return profile;
    }
}
