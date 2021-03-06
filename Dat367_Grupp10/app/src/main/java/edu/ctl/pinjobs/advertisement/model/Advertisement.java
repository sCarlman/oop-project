package edu.ctl.pinjobs.advertisement.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

    private InfoCheck infoCheck = new InfoCheck();

    //advertisement constructor
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
        setYear(year);
        setMonth(month);
        setDay(day);


    }

    public void setAdvertiser(IProfile advertiser) {
        this.advertiser = advertiser;
    }

    public void setLocation(String location) throws WrongAdInputException{

        if(location != null && !infoCheck.isEmpty(location) && infoCheck.containsLetters(location)
                && infoCheck.containsOnlyLettersNumbersWhitespaceOrComa(location)) {
            this.location = location;
        }else {
            throw new WrongAdInputException("location");
        }
    }

    public void setTitle(String title)throws WrongAdInputException {

        if(title != null && title.length()<=30 && !infoCheck.isEmpty(title) &&  infoCheck.isAlphabetic(title.replace(" ", "")) ) {
            this.title = title;
        }else {
            throw new WrongAdInputException("title");
        }
    }

    public void setDescription(String description) throws WrongAdInputException {

        if(description!=null && description.length()<=300 && !infoCheck.isEmpty(description) && infoCheck.containsLetters(description)) {
            this.description = description;
        }else{
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

    public void setDay(int day) throws WrongAdInputException {
        if(checkDay(day, getMonth(), getYear())) {
            this.day = day;
        }else {
            throw new WrongAdInputException("date");
        }
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) throws WrongAdInputException{
        if(checkMonth(month, getYear())) {
            this.month = month;
        }else {
            throw new WrongAdInputException("date");
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) throws WrongAdInputException {
        if (checkYear(year)) {
            this.year = year;
        }else {
            throw new WrongAdInputException("date");
        }
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) throws WrongAdInputException {
        if(latitude<=90 && latitude>= -90) {
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

    private boolean checkYear(int year){

        Calendar today = new GregorianCalendar();
        int thisYear = today.get(Calendar.YEAR);

        if(year>=thisYear){
            return true;
        }else{
            return false;
        }
    }

    private boolean checkMonth(int month, int year){

        Calendar today = new GregorianCalendar();
        int thisMonth = today.get(Calendar.MONTH);
        int thisYear = today.get(Calendar.YEAR);
        if(month>=thisMonth || year>thisYear){
            return true;
        }else{
            return false;
        }
    }

    private boolean checkDay(int day, int month, int year){

        Calendar today = new GregorianCalendar();
        int thisDay = today.get(Calendar.DAY_OF_MONTH);
        int thisMonth = today.get(Calendar.MONTH);
        int thisYear = today.get(Calendar.YEAR);
        if(day>=thisDay || (month>thisMonth && year>=thisYear) || year>thisYear){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean equals(Object other){
        //equals if it has the same
        if(other == null){
            return false;
        }
        if(this == other){
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


    public String getDayString(){
        String sDay;
        if(day < 10){
            sDay = "0" + Integer.toString(day);
        }else{
            sDay = Integer.toString(day);
        } return sDay;
    }


    public String getMonthString(){
        String sMonth;
        if(month < 10){
            sMonth = "0" + Integer.toString(month);
        }else{
            sMonth = Integer.toString(month);
        } return sMonth;
    }

}

