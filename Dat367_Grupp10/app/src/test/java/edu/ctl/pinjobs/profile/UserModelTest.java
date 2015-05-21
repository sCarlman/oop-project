package edu.ctl.pinjobs.profile;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.Assert.*;

import edu.ctl.pinjobs.advertisement.MockProfile;
import edu.ctl.pinjobs.controller.UserModel;

/**
 * Created by filiplarsson on 15-05-21.
 */
public class UserModelTest {
    @Test
    public void testLogIn(){
        UserModel testUM = UserModel.getInstance();
        IProfile testProfile = new MockProfile();
        testUM.logIn(testProfile);
        Boolean loggedInCheck = testUM.getIsLoggedIn();
        assert loggedInCheck == true;
    }

    @Test
    public void testLogOff(){
        UserModel testUM = UserModel.getInstance();
        IProfile testProfile = new MockProfile();
        testUM.logIn(testProfile);
        testUM.logOff();
        Boolean loggedInCheck = testUM.getIsLoggedIn();
        assert loggedInCheck == false;
    }

}
