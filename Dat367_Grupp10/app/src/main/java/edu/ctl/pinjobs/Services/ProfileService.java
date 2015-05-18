package edu.ctl.pinjobs.Services;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import edu.ctl.pinjobs.profile.IProfile;
import edu.ctl.pinjobs.profile.Profile;

/**
 * Created by Isaac on 2015-04-23.
 */
public class ProfileService implements IProfileService{


    public void saveProfile(IProfile profile){
        ParseObject parseProfile = new ParseObject("Profile");
        setParseProfile(profile, parseProfile);
        uploadToParse(parseProfile);
    }

    private void setParseProfile(IProfile p, ParseObject returnParseObject) {
        returnParseObject.put("FirstName", p.getFirstName());
        returnParseObject.put("LastName", p.getLastName());
        returnParseObject.put("Password", p.getPassword());
        returnParseObject.put("Email", p.getEmail());
        returnParseObject.put("Phone", p.getPhone());
        returnParseObject.put("PreferredLocation", p.getAddress());
    }

    private void uploadToParse(ParseObject parseProfile){
        parseProfile.saveInBackground();
    }

    public List<IProfile> fetchAllProfiles(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Profile");
        try {
            return copyToProfiles(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
            //TODO: Handle error.
            return null;
        }
    }

    private List<IProfile> copyToProfiles(List<ParseObject> profileList) {
        List<IProfile> fetchedProfiles = new ArrayList<IProfile>();
        for (ParseObject parseProfile: profileList) {
            fetchedProfiles.add(new Profile(parseProfile.getString("FirstName"),
                    parseProfile.getString("LastName"),
                    parseProfile.getString("Password"),
                    parseProfile.getString("Email"),
                    parseProfile.getString("Phone"),
                    parseProfile.getString("PreferredLocation")));
        }
        return fetchedProfiles;
    }

    public IProfile fetchProfile(String email) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Profile");
        query.whereEqualTo("Email", email);
        try {
            return copyToProfile(query.getFirst());
        } catch (ParseException e) {
            e.printStackTrace();
            //TODO: Handle error.
            return null;
        }
    }

    private IProfile copyToProfile(ParseObject parseProfile) {
        Profile fetchedProfile = new Profile(parseProfile.getString("FirstName"),
                parseProfile.getString("LastName"),
                parseProfile.getString("Password"),
                parseProfile.getString("Email"),
                parseProfile.getString("Phone"),
                parseProfile.getString("PreferredLocation"));
        return fetchedProfile;
    }

    public void updateProfile(final IProfile profile){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Profile");
        query.whereEqualTo("Email", profile.getEmail());
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                parseObject.put("FirstName", profile.getFirstName());
                parseObject.put("LastName", profile.getLastName());
                parseObject.put("Email", profile.getEmail());
                parseObject.put("Phone", profile.getPhone());
                parseObject.put("FirstName", profile.getFirstName());
                parseObject.put("FirstName", profile.getFirstName());

            }
        });

    }

}
