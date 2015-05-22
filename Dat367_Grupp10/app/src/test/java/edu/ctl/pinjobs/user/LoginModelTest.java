package edu.ctl.pinjobs.user;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import edu.ctl.pinjobs.advertisement.MockProfile;
import edu.ctl.pinjobs.profile.model.IProfile;
import edu.ctl.pinjobs.user.model.LoginModel;

/**
 * Created by filiplarsson on 15-05-21.
 */
public class LoginModelTest {

    @Test
    public void testDoesMailExistInUserDatabase(){

        List<IProfile> profiles = new ArrayList<>();
        IProfile testProfile = new MockProfile();
        IProfile testProfile2 = new MockProfile();
        profiles.add(testProfile);
        profiles.add(testProfile2);
        LoginModel testModel = new LoginModel();
        testModel.setEmail(testProfile.getEmail());
    }

}
