package edu.ctl.pinjobs.advertisement.model;

import java.io.Serializable;

import edu.ctl.pinjobs.utils.InfoCheck;
import edu.ctl.pinjobs.profile.model.IProfile;
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
        if(location != null && location.length()!=0 ) {
            this.location = location;
        }else {
            throw new WrongAdInputException("location");
        }
    }

    public void setTitle(String title)throws WrongAdInputException {
        InfoCheck infoCheck = new InfoCheck();
        if(title.length()<=30 && title.length()>0 && title !=null && infoCheck.isAlphabetic(title.replace(" ","")) ) {
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
        if(latitude<90 && latitude>= -90) {
            this.latitude = latitude;
        }else {
            throw new WrongAdInputException("location");
        }
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) throws WrongAdInputException {
        if(longitude<=180 && longitude>= -180) {
            this.longitude = longitude;
        }else {
            throw new WrongAdInputException("location");
        }
    }

    @Override
    public boolean equals(Object other){
        //equals if it has the same
        if(other ==null){
            return false;
        }
        if(this== other){
            return true;
        }
        if(this.getClass() != other.getClass()){
            return false;
        }
        Advertisement advertisement=(Advertisement)other;
        return advertiser.equals(advertisement.getAdvertiser()) && location.equals(advertisement.getLocation()) &&
                title.equals(advertisement.getTitle()) && day==advertisement.getDay() && month ==advertisement.getMonth()
                && year==advertisement.getYear() && category == advertisement.getCategory();
    }

}
