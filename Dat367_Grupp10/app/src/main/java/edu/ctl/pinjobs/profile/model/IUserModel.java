package edu.ctl.pinjobs.profile.model;

import java.io.Serializable;

import edu.ctl.pinjobs.profile.model.IProfile;

/**
 * Created by Filips on 5/26/2015.
 */
public interface IUserModel extends Serializable {
    public void logIn(IProfile loginProfile);
    public void logOff();
    public boolean getIsLoggedIn();
    public IProfile getProfile();
}
