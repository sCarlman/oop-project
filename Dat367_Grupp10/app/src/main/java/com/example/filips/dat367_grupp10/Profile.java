package com.example.filips.dat367_grupp10;

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
    private String preferredLocation;

    public Profile(String firstName, String lastName, String email, String phone,
                   String preferredLocation) {
        if (InfoCheck.isAlphabetic(firstName)) {
            this.firstName = firstName;
        }else {
            //TODO: throw some exception.
        }
        if (InfoCheck.isAlphabetic(lastName)) {
            this.lastName = lastName;
        }else {
            //TODO: throw some exception.
        }
        if (InfoCheck.isEmailCorrect(email)) {
            this.email = email;
        }else {
            //TODO: throw some exception.
        }
        if (InfoCheck.isNumeric(phone)) {
            this.phone = phone;
        }else {
            //TODO: throw some exception.
        }
        //TODO check preferredLocation??
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

    public String getPreferredLocation() {
        return preferredLocation;
    }

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

    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }
}
