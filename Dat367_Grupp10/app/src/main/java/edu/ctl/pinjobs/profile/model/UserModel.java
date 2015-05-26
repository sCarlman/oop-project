package edu.ctl.pinjobs.profile.model;

/**
 * Created by Albertsson on 15-05-05.
 */
public class UserModel implements IUserModel {

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

    @Override
    public void logIn(IProfile logInProfile){
        this.isLoggedIn = true;
        this.profile = logInProfile;
        System.out.println(profile.getFirstName()+ "IS logged in");
    }

    @Override
    public void logOff(){
        this.isLoggedIn = false;
        this.profile = null;
    }

    @Override
    public boolean getIsLoggedIn(){
        return isLoggedIn;
    }

    @Override
    public IProfile getProfile(){
        return profile;
    }
}
