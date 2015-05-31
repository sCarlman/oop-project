package edu.ctl.pinjobs.advertisement.model;


import java.io.Serializable;

import edu.ctl.pinjobs.profile.model.IProfile;

/**
 * Created by Isaac on 2015-04-27.
 */
public interface IAdvertisement extends Serializable {

    public void setAdvertiser(IProfile advertiser);
    public void setLocation(String location) throws WrongAdInputException;
    public void setTitle(String title) throws WrongAdInputException;
    public void setDescription (String description)throws WrongAdInputException;
    public void setCategory(Category category);
    public void setDay(int day)throws WrongAdInputException;
    public void setMonth(int month)throws WrongAdInputException;
    public void setYear(int year)throws WrongAdInputException;
    public void setLongitude(double longitude)throws WrongAdInputException;
    public void setLatitude(double latitude)throws WrongAdInputException;

    public IProfile getAdvertiser();
    public String getLocation();
    public String getTitle();
    public String getDescription();
    public Category getCategory();
    public int getDay();
    public int getMonth();
    public int getYear();
    public double getLatitude();
    public double getLongitude();

    public String getMonthString();
    public String getDayString();

}
