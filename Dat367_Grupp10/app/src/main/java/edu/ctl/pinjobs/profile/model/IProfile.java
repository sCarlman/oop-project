package edu.ctl.pinjobs.profile.model;


import java.io.Serializable;

/**
 * Created by Isaac on 2015-04-27.
 */
public interface IProfile extends Serializable {

    public void setFirstName(String firstName) throws WrongInputExeption;
    public void setLastName(String lastName) throws WrongInputExeption ;
    public void setPassword(String password) throws WrongInputExeption;
    public void setEmail(String email) throws WrongInputExeption;
    public void setPhone(String phone) throws WrongInputExeption;
    public void setAddress(String preferredLocation) throws WrongInputExeption;

    public String getFirstName();
    public String getLastName();
    public String getPassword();
    public String getEmail();
    public String getPhone();
    public String getAddress();

}
