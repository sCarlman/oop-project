package edu.ctl.pinjobs.User;

/**
 * Created by Albertsson on 15-05-05.
 */
public class UserModel {

    public boolean isLoggedIn = false;

    public UserModel(){}

    public boolean getIsLoggedIn(){
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean b){
        this.isLoggedIn = b;
    }
}
