package com.example.filips.dat367_grupp10;

import com.google.android.gms.maps.model.LatLng;
import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by Isaac on 2015-04-01.
 * A profile is needed to post ads
 *
 */
@ParseClassName("Profile")
public class Profile extends ParseUser{

    public Profile(){
        super();
    }

    public void setProfile(String userName, String password, String firstName, String lastName, String email, String phone,
                   LatLng preferredLocation) {
            setUsername(userName);
            setPassword(password);
            setFirstName(firstName);
            setLastName(lastName);
            setEmail(email);
            setPhone(phone);
            setPreferredLocation(new ParseGeoPoint(preferredLocation.latitude,preferredLocation.longitude));
    }

    @Override
    public void setUsername(String username){
        super.setUsername(username);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    public void setFirstName(String firstName) {
        put("FirstName", firstName);
    }

    public void setLastName(String lastName) {
        put("LastName", lastName);
    }
    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    public void setPhone(String phone) {
        put("Phone", phone);
    }

    public void setPreferredLocation(ParseGeoPoint preferredLocation) {
        put("PreferredLocation", preferredLocation);
    }

    public String getFirstName() {
        return getString("FirstName");
    }

    public String getLastName() {
        return getString("LastName");
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    public String getPhone() {
        return getString("Phone");
    }

    public ParseGeoPoint getPreferredLocation() {
        return getParseGeoPoint("preferredLocation");
    }
}
