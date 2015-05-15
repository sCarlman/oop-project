package edu.ctl.pinjobs.profile;


import java.io.Serializable;

/**
 * Created by Isaac on 2015-04-27.
 */
public interface IProfile extends Serializable {

    public void setFirstName(String firstName);
    public void setLastName(String lastName);
    public void setPassword(String password);
    public void setEmail(String email);
    public void setPhone(String phone);
    public void setAddress(String preferredLocation);

    public String getFirstName();
    public String getLastName();
    public String getPassword();
    public String getEmail();
    public String getPhone();
    public String getAddress();

}
