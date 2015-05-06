package edu.ctl.pinjobs.model;



/**
 * Created by Isaac on 2015-04-01.
 * A profile is needed to post ads
 *
 */
public class Profile implements IProfile{

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    public Profile(String firstName, String lastName, String email, String phone,
                           String address) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhone(phone);
        setAddress(address);
    }

    public static void test(){

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

    public void setAddress(String preferredLocation) {
        this.address = preferredLocation;
    }

    public String getFirstName() {
        return firstName;
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

    public String getAddress() {
        return address;
    }
}

