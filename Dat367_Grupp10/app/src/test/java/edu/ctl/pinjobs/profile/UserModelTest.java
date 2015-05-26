package edu.ctl.pinjobs.profile;

import org.junit.Test;

import edu.ctl.pinjobs.advertisement.MockProfile;
import edu.ctl.pinjobs.profile.model.UserModel;
import edu.ctl.pinjobs.profile.model.IProfile;

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
