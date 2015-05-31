package edu.ctl.pinjobs.profile.service;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import edu.ctl.pinjobs.profile.model.IProfile;
import edu.ctl.pinjobs.profile.model.Profile;
import edu.ctl.pinjobs.profile.model.WrongInputException;

/**
 * Created by Isaac on 2015-04-23.
 * This Service class handles all available usability of Parse database concerning Profile
 * in this application.
 */
public class ProfileService implements IProfileService {

    public void saveProfile(IProfile profile){
        ParseObject parseProfile = new ParseObject("Profile");
        setParseProfile(profile, parseProfile);
        uploadToParse(parseProfile);
    }

    //Parse cannot save own made classes like Profile, so we have to copy its content into
    //a ParseObject.
    private void setParseProfile(IProfile p, ParseObject returnParseObject) {
        returnParseObject.put("FirstName", p.getFirstName());
        returnParseObject.put("LastName", p.getLastName());
        returnParseObject.put("Password", p.getPassword());
        returnParseObject.put("Email", p.getEmail());
        returnParseObject.put("Phone", p.getPhone());
        returnParseObject.put("PreferredLocation", p.getAddress());
    }

    //saves to Parse
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

    //The classes saved to Parse are ParseObjects, therefore they have to be made into Profiles again
    //when retrieved.
    private List<IProfile> copyToProfiles(List<ParseObject> profileList) {
        List<IProfile> fetchedProfiles = new ArrayList<IProfile>();
        for (ParseObject parseProfile: profileList) {
            try{
                fetchedProfiles.add(new Profile(parseProfile.getString("FirstName"),
                        parseProfile.getString("LastName"),
                        parseProfile.getString("Password"),
                        parseProfile.getString("Email"),
                        parseProfile.getString("Phone"),
                        parseProfile.getString("PreferredLocation")));
            }catch (WrongInputException e){
                e.printStackTrace();
            }

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
            return null;
        }
    }

    //makes a ProfileParseObject to Profile
    private IProfile copyToProfile(ParseObject parseProfile) {
        try{
            Profile fetchedProfile = new Profile(parseProfile.getString("FirstName"),
                    parseProfile.getString("LastName"),
                    parseProfile.getString("Password"),
                    parseProfile.getString("Email"),
                    parseProfile.getString("Phone"),
                    parseProfile.getString("PreferredLocation"));

            return fetchedProfile;
        }catch (WrongInputException e){
            e.printStackTrace();
        }
        return null;
    }

    //Modifies basic information of a Profile
    public void updateProfile(final IProfile profile){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Profile");
        //the email is the only thing that is unique for Profiles
        query.whereEqualTo("Email", profile.getEmail());
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                parseObject.put("FirstName", profile.getFirstName());
                parseObject.put("LastName", profile.getLastName());
                parseObject.put("Email", profile.getEmail());
                parseObject.put("Phone", profile.getPhone());
                parseObject.put("PreferredLocation", profile.getAddress());
                uploadToParse(parseObject);
            }
        });

    }

}
