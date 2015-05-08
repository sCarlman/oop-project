package edu.ctl.pinjobs.User;

import edu.ctl.pinjobs.profile.IProfile;

/**
 * Created by Albertsson on 15-05-05.
 */
public class UserModel {

    public boolean isLoggedIn = false;
    private IProfile profile;

    public UserModel(){}

    public boolean getIsLoggedIn(){
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean b){
        this.isLoggedIn = b;
    }

    public void setProfile(IProfile p){
        this.profile = p;
    }
}
