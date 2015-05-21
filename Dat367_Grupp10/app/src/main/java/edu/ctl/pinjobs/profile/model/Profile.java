package edu.ctl.pinjobs.profile.model;


import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.ctl.pinjobs.utils.InfoCheck;

/**
 * Created by Isaac on 2015-04-01.
 * A profile is needed to post ads
 *
 */
public class Profile implements IProfile, Serializable {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phone;
    private String address;
    InfoCheck infoCheck = new InfoCheck();

    public Profile(String firstName, String lastName, String password, String email, String phone,
                           String address)throws WrongInputExeption {
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setEmail(email);
        setPhone(phone);
        setAddress(address);

    }


    public void setFirstName(String firstName) throws WrongInputExeption{
        if(firstName != null && !firstName.isEmpty() && infoCheck.isAlphabetic(firstName)){
            this.firstName = firstName;
        }else{
            throw new WrongInputExeption("FirstName");
        }
    }

    public void setLastName(String lastName) throws WrongInputExeption{
        if(lastName != null && !lastName.isEmpty() && infoCheck.isAlphabetic(firstName)){
            this.lastName = lastName;
        }else{
            throw new WrongInputExeption("LastName");
        }
    }

    public void setPassword(String password) throws WrongInputExeption{
        if(password != null && !password.isEmpty() && password.length() >= 2){
            this.password = password;
        }else{
            throw new WrongInputExeption("Password");
        }
    }

    public void setEmail(String email) throws WrongInputExeption{
        if(email != null && !email.isEmpty() && isEmailCorrect(email)){
            this.email = email;
        }else{
            throw new WrongInputExeption("Email");
        }
    }

    public void setPhone(String phone) throws WrongInputExeption{
        if(phone != null && !phone.isEmpty() && isNumeric(phone)){
            this.phone = phone;
        }else{
            throw new WrongInputExeption("Phone");
        }
    }

    public void setAddress(String preferredLocation) throws WrongInputExeption{
        if(preferredLocation != null && !preferredLocation.isEmpty()){
            this.address = preferredLocation;
        }else{
            throw new WrongInputExeption("Location");
        }

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
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

    private boolean isEmailCorrect(String email) {
        if (email != null || email != "") {
            Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
            Matcher m = p.matcher(email);
            boolean emailValid = m.matches();
            return emailValid;
        }else {
            return false;
        }
    }

    private boolean isNumeric(String phone) {
        char[] chars = phone.toCharArray();
        //method requires symbols like "-" to be consumed
        for (char c : chars) {
            if(!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other){
        if(other ==null){
            return false;
        }
        if(this== other){
            return true;
        }
        if(this.getClass() != other.getClass()){
            return false;
        }
        Profile profile=(Profile)other;
        return firstName.equals(profile.getFirstName()) && lastName.equals(profile.getLastName()) &&
                email.equals(profile.getEmail());
    }
}

