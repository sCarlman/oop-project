package edu.ctl.pinjobs.profile;

import edu.ctl.pinjobs.profile.model.IProfile;
import edu.ctl.pinjobs.profile.model.WrongInputExeption;

/**
 * Created by Filips on 5/20/2015.
 */
public class MockProfile implements IProfile{
    @Override
    public void setFirstName(String firstName) throws WrongInputExeption {

    }

    @Override
    public void setLastName(String lastName) throws WrongInputExeption {

    }

    @Override
    public void setPassword(String password) throws WrongInputExeption {

    }

    @Override
    public void setEmail(String email) throws WrongInputExeption {

    }

    @Override
    public void setPhone(String phone) throws WrongInputExeption {

    }

    @Override
    public void setAddress(String preferredLocation) throws WrongInputExeption {

    }

    @Override
    public String getFirstName() {
        return "Filip";
    }

    @Override
    public String getLastName() {
        return "Seholm";
    }

    @Override
    public String getPassword() {
        return "hej123";
    }

    @Override
    public String getEmail() {
        return "filip.slottner@gmail.com";
    }

    @Override
    public String getPhone() {
        return "03021773774";
    }

    @Override
    public String getAddress() {
        return "Rosenvägen2a";
    }
}
