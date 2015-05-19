package edu.ctl.pinjobs.Advertisement;

import java.io.Serializable;

import edu.ctl.pinjobs.Utils.InfoCheck;
import edu.ctl.pinjobs.profile.IProfile;
/**
 * Created by Albertsson on 15-04-01.
 */

public class Advertisement implements IAdvertisement, Serializable {

    private IProfile advertiser;
    private String location;
    private String title;
    private String description;
    private Category category;
    private int day;
    private int month;
    private int year;
    private double latitude;
    private double longitude;

    public Advertisement(IProfile advertiser, String title, String description, String location,
                         Category category, int day, int month, int year, double latitude,
                         double longitude) throws WrongAdInputException {
        setAdvertiser(advertiser);
        setLatitude(latitude);
        setLongitude(longitude);
        setLocation(location);
        setTitle(title);
        setDescription(description);
        setCategory(category);
        setDay(day);
        setMonth(month);
        setYear(year);
    }

    public void setAdvertiser(IProfile advertiser) {
        System.out.println(advertiser);
        this.advertiser = advertiser;
    }

    public void setLocation(String location) throws WrongAdInputException{
        if(location != null && location.length()!=0 && checkLocationFormat(location) ) {
            this.location = location;
        }else {
            throw new WrongAdInputException("location");
        }
    }

    public void setTitle(String title)throws WrongAdInputException {
        InfoCheck infoCheck = new InfoCheck();
        if(title.length()<=30 && title.length()>0 && title !=null && infoCheck.isAlphabetic(title) ) {
            this.title = title;
        }else {
            throw new WrongAdInputException("title");
        }
    }

    public void setDescription(String description) throws WrongAdInputException {
        if(title.length()<=300 && title.length()>0 && title !=null) {
            this.description = description;
        }else {
            throw new WrongAdInputException("description");
        }
    }

    public void setCategory(Category category){
            this.category = category;
    }

    public IProfile getAdvertiser() {
        return advertiser;
    }

    public String getLocation(){
        return location;
    }

    public Category getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) throws WrongAdInputException {
        if(latitude<180 && latitude>-180) {
            this.latitude = latitude;
        }else {
            throw new WrongAdInputException("location");
        }
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) throws WrongAdInputException {
        if(longitude<180 && longitude>-180) {
            this.longitude = longitude;
        }else {
            throw new WrongAdInputException("location");
        }
    }

    private boolean checkLocationFormat(String name) {
        //Chcks if first character is a letter and if the rest is either number or letter.
        //if one number has occurred a following letter returns false. else returns true.
        char[] chars = name.toCharArray();
        boolean firstIsAlphabetic = false;
        boolean numberExists = false;
        if(Character.isLetter(chars[0])){
            firstIsAlphabetic = true;
        }
        for (char c : chars) {
            if(Character.isLetter(c) && numberExists){
                System.out.println("SKA INTE KOMMA HIT");
                //returns false if number has occurred followed by a letter.
                return false;
            } else if(!(Character.isLetter(c))) {
                numberExists = true;
                System.out.println("is character is letter");
                if(!Character.isDigit(c)){
                    System.out.println("is character is digit");
                    //if character is neither letter or number return false.
                    return false;
                }
            }
        }
        //returns true if previous false returns haven't occurred and first character is a letter
        System.out.println( true && firstIsAlphabetic);
        return true && firstIsAlphabetic;

    }
}

