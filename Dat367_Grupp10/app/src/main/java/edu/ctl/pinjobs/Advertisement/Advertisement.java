package edu.ctl.pinjobs.Advertisement;


import android.os.Parcel;
import android.os.Parcelable;

import edu.ctl.pinjobs.profile.IProfile;
import java.io.Serializable;
/**
 * Created by Albertsson on 15-04-01.
 */

public class Advertisement implements IAdvertisement, Parcelable {

    private IProfile advertiser;
    private String location;
    private String title;
    private String description;
    private Category category;
    private int day;
    private int month;
    private int year;

    public Advertisement(IProfile advertiser, String title, String description, String location,
                         Category category, int day, int month, int year) {
        setAdvertiser(advertiser);
        setLocation(location);
        setTitle(title);
        setDescription(description);
        setCategory(category);
        setDay(day);
        setMonth(month);
        setYear(year);
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

    protected Advertisement(Parcel in) {
        advertiser = (IProfile) in.readValue(IProfile.class.getClassLoader());
        location = in.readString();
        title = in.readString();
        description = in.readString();
        category = (Category) in.readValue(Category.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(advertiser);
        dest.writeString(location);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeValue(category);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Advertisement> CREATOR = new Parcelable.Creator<Advertisement>() {
        @Override
        public Advertisement createFromParcel(Parcel in) {
            return new Advertisement(in);
        }

        @Override
        public Advertisement[] newArray(int size) {
            return new Advertisement[size];
        }
    };

}
