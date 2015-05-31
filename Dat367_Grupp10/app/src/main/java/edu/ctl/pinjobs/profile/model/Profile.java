package edu.ctl.pinjobs.profile.model;


import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.ctl.pinjobs.utils.InfoCheck;

/**
 * Created by Isaac on 2015-04-01.
 * 
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

    private String splitStringCity;
    private String splitStringAddress;
    private InfoCheck infoCheck = new InfoCheck();

    public Profile(String firstName, String lastName, String password, String email, String phone,
                           String address)throws WrongInputException {
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setEmail(email);
        setPhone(phone);
        setAddress(address);
    }


    public void setFirstName(String firstName) throws WrongInputException {
        if(firstName != null &&!infoCheck.isEmpty(firstName) && infoCheck.isAlphabetic(firstName)){
            this.firstName = firstName;
        }else{
            throw new WrongInputException("FirstName");
        }
    }

    public void setLastName(String lastName) throws WrongInputException {
        if(lastName != null && !infoCheck.isEmpty(lastName) && infoCheck.isAlphabetic(lastName) && lastName.length()<100){
            this.lastName = lastName;
        }else{
            throw new WrongInputException("LastName");
        }
    }

    public void setPassword(String password) throws WrongInputException {
        if(password != null && !infoCheck.isEmpty(password) && password.length() >= 2 &&
                password.length()<=20 && !infoCheck.containsSpaces(password)){
            this.password = password;
        }else{
            throw new WrongInputException("Password");
        }
    }

    public void setEmail(String email) throws WrongInputException {
        if(email != null && !infoCheck.isEmpty(email) && isEmailCorrect(email) && !infoCheck.containsSpaces(email)){
            this.email = email;
        }else{
            throw new WrongInputException("Email");
        }
    }

    public void setPhone(String phone) throws WrongInputException {
        if(phone != null && !infoCheck.isEmpty(phone) && isNumeric(phone) && isPhone(phone)){
            this.phone = phone;
        }else{
            throw new WrongInputException("Phone");
        }
    }

    public void setAddress(String preferredLocation) throws WrongInputException {

        if(preferredLocation == null){
            throw new WrongInputException("Location+City");
        }
        if(!infoCheck.containsComa(preferredLocation)){
            throw new WrongInputException("Location+City");
        }
        try{
            splitStringAddress = preferredLocation.split(",")[0];
        }catch(ArrayIndexOutOfBoundsException e){
            throw new WrongInputException("Location");
        }
        try{
            splitStringCity = preferredLocation.split(",")[1];
        }catch(ArrayIndexOutOfBoundsException e){
            throw new WrongInputException("City");
        }

        if(splitStringAddress != null && !infoCheck.isEmpty(splitStringAddress) &&
                infoCheck.containsLetters(splitStringAddress)){
            if(splitStringCity != null && !infoCheck.isEmpty(splitStringCity) && infoCheck.isAlphabetic(splitStringCity)){
                this.address = splitStringAddress + "," + splitStringCity;
            }else{
                throw new WrongInputException("City");
            }
        }else{
            throw new WrongInputException("Location");
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


    //Checks if phonenumber is correct and it must start with 07xxx...
    private boolean isPhone(String phone){
        char[] chars = phone.toCharArray();
        if(!phone.substring(0,2).equals("07")){
            return false;
        }

        int numberOfDigits = 0;
        //counts chars in phone string
        for (char c : chars) {
            numberOfDigits = numberOfDigits +1;

        }
        //checks if there are total 10 digits
        if(numberOfDigits==10){
            return true;
        }else{
            return false;
        }
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

