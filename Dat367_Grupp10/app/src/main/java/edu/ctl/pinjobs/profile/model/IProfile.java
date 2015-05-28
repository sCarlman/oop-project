package edu.ctl.pinjobs.profile.model;


import java.io.Serializable;

/**
 * Created by Isaac on 2015-04-27.
 */
public interface IProfile extends Serializable {

    public void setFirstName(String firstName) throws WrongInputException;
    public void setLastName(String lastName) throws WrongInputException;
    public void setPassword(String password) throws WrongInputException;
    public void setEmail(String email) throws WrongInputException;
    public void setPhone(String phone) throws WrongInputException;
    public void setAddress(String preferredLocation) throws WrongInputException;

    public String getFirstName();
    public String getLastName();
    public String getPassword();
    public String getEmail();
    public String getPhone();
    public String getAddress();

}
