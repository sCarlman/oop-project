package edu.ctl.pinjobs.User;

import edu.ctl.pinjobs.profile.IProfile;

/**
 * Created by Albertsson on 15-05-05.
 */
public class UserModel {

    private static UserModel instance = null;
    public boolean isLoggedIn = false;
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

    public boolean getIsLoggedIn(){
        return isLoggedIn;
    }

    public void setLoggedIn(boolean b){
        this.isLoggedIn = b;
    }

    public void setProfile(IProfile p){
        this.profile = p;
    }

    public IProfile getProfile(){
        return profile;
    }
}
