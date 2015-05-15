package edu.ctl.pinjobs.Advertisement;

import java.io.Serializable;

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
                         double longitude) {
        setAdvertiser(advertiser);
        setLocation(location);
        setTitle(title);
        setDescription(description);
        setCategory(category);
        setDay(day);
        setMonth(month);
        setYear(year);
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public void setAdvertiser(IProfile advertiser) {
        this.advertiser = advertiser;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
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

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

