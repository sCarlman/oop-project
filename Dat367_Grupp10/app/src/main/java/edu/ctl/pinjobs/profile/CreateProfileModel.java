package edu.ctl.pinjobs.profile;

import edu.ctl.pinjobs.Services.IProfileService;
import edu.ctl.pinjobs.Services.ProfileService;

/**
 * Created by Isaac on 2015-05-07.
 */
public class CreateProfileModel implements ICreateProfileModel {

    private IProfileService pService = new ProfileService();
    private IProfile newProfile;

    @Override
    public void CreateProfile(String firstName, String lastName, String password, String email,
                              String phone, String address) {
        newProfile = new Profile(firstName, lastName, password, email, phone, address);
        saveNewProfile(newProfile);
    }

    private void saveNewProfile(IProfile newProfile) {
        pService.saveProfile(newProfile);
    }
}
