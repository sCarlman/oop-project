package edu.ctl.pinjobs.Services;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import edu.ctl.pinjobs.model.IProfile;
import edu.ctl.pinjobs.model.Profile;

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
    public static void saveProfile(IProfile p){
        ParseObject parseProfile = new ParseObject("Profile");
        setParseProfile(p, parseProfile);
        uploadToParse(parseProfile);
    }

    /**
     * Creates variables in returnParseObject named after the variables in Profile
     * and assigns them to the corresponding values of p.
     * @param p Profile to get the values from.
     * @param returnParseObject ParseObject to modify.
     */
    public static void setParseProfile(IProfile p, ParseObject returnParseObject) {
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

    /**
     * fetches and returns a list of parseObjects containing all the data of all the
     * profiles registered to parse.
     */
    public static List<Profile> fetchParseProfiles(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Profile");
        try {
            return copyToProfiles(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
            //TODO: Handle error.
            return null;
        }
    }

    /**
     * Transfers data from a fetched list of ParseObjects to a list of Profiles.
     * @param profileList List of ParseObject, fetched from parse database.
     * @return List of Profiles copied from fetched list of ParseObjects from parse database.
     */
    private static List<Profile> copyToProfiles(List<ParseObject> profileList) {
        List<Profile> fetchedProfiles = new ArrayList<Profile>();
        for (ParseObject parseProfile: profileList) {
            fetchedProfiles.add(new Profile(parseProfile.getString("FirstName"),
                    parseProfile.getString("LastName"),
                    parseProfile.getString("Email"),
                    parseProfile.getString("Phone"),
                    parseProfile.getParseGeoPoint("PreferredLocation")));
        }
        return fetchedProfiles;
    }

    /**
     * Gets fetched profiles.
     * @return list of all profiles at parse database.
     */
    public static List<Profile> getFetchedProfiles() {
        return fetchParseProfiles();
    }

    /**
     * Fetches Profile containing given email
     * @param email the email-address belonging to the Profile to be fetched
     */
    public static Profile fetchParseProfile(String email) {
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

    /**
     * Transfers all data from a ParseObject representing a Profile to a Profile
     * @param parseProfile The fetched ParseObject to be copied to Profile
     */
    private static Profile copyToProfile(ParseObject parseProfile) {
        Profile fetchedProfile = new Profile(parseProfile.getString("FirstName"),
                parseProfile.getString("LastName"),
                parseProfile.getString("Email"),
                parseProfile.getString("Phone"),
                parseProfile.getParseGeoPoint("PreferredLocation"));
        return fetchedProfile;
    }

    /**
     * Gets fetched profile from parse database that contains given email-address
     * @param email the email belonging to the Profile to be fetched
     * @return fetched profile with matching email-address
     */
    public static Profile getFetchedProfile(String email){
        return fetchParseProfile(email);
    }

}
