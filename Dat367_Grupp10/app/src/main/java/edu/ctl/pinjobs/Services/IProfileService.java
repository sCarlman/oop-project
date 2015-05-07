package edu.ctl.pinjobs.Services;

import java.util.List;

import edu.ctl.pinjobs.profile.IProfile;

/**
 * Created by Isaac on 2015-05-06.
 */
public interface IProfileService {

    public void saveProfile(IProfile profile);

    public List<IProfile> fetchAllProfiles();
    public IProfile fetchProfile(String email);
}
