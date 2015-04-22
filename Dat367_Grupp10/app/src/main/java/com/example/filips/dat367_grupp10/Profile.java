package com.example.filips.dat367_grupp10;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Isaac on 2015-04-01.
 * A profile is needed to post ads
 *
 */
public class Profile {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LatLng preferredLocation;
    private String password;
    private static List<Profile> ProfileList;

    public Profile(String firstName, String lastName, String email, String phone,
                   LatLng preferredLocation) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phone = phone;
        this.preferredLocation = preferredLocation;


    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LatLng getPreferredLocation() {
        return preferredLocation;
    }

    public String getPassword() { return password; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPreferredLocation(LatLng preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public void setPassword(String password) { this.password = password; }
}
