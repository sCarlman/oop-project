package com.example.filips.dat367_grupp10;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * ProfileService handles the communication or exchanging of data concerning
 * Profiles between the program and the database at Parse.com
 * Created by Isaac on 2015-04-23.
 */
public class ProfileService {

    /**
     * Creates a new parseObject as a profile
     * @param p the profile one wish to save to parse
     */
    public static void saveProfile(Profile p){
        ParseObject parseProfile = new ParseObject("Profile");
        setParseProfile(p,parseProfile);
        uploadToParse(parseProfile);
    }

    /**
     * Creates variables in returnParseObject named after the variables in Profile
     * and assigns them to the corresponding values of p.
     * @param p Profile to get the values from.
     * @param returnParseObject ParseObject to modify.
     */
    public static void setParseProfile(Profile p, ParseObject returnParseObject) {
        returnParseObject.put("FirstName", p.getFirstName());
        returnParseObject.put("LastName", p.getLastName());
        returnParseObject.put("Email", p.getEmail());
        returnParseObject.put("Phone", p.getPhone());
        returnParseObject.put("PreferredLocation", p.getAddress());
    }

    /**
     * Uploads a parseObject to parse
     * @param parseProfile the parseObject to be uploaded
     */
    private static void uploadToParse(ParseObject parseProfile){
        parseProfile.saveInBackground();
    }

    public static void fetchParseProfiles(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> profileList, ParseException e) {
                if (e == null) {
                    copyToProfiles(profileList);
                } else {
                    System.out.print(e.getMessage());
                }
            }
        });
    }

    private static void copyToProfiles(List<ParseObject> profileList) {
        
    }

    public static Profile fetchProfile(String email) {
        return null;
    }
}
