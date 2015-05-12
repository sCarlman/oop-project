package edu.ctl.pinjobs.Advertisement;

import android.os.Parcel;
import android.os.Parcelable;

import edu.ctl.pinjobs.profile.IProfile;

/**
 * Created by filiplarsson on 15-05-12.
 */
public class AndroidAdvertisement implements Parcelable {

    private IProfile advertiser;
    private String location;
    private String title;
    private String description;
    private Category category;
    private int day;
    private int month;
    private int year;

    public AndroidAdvertisement(Advertisement ad) {
        setAdvertiser(ad.getAdvertiser());
        setLocation(ad.getLocation());
        setTitle(ad.getTitle());
        setDescription(ad.getDescription());
        setCategory(ad.getCategory());
        //setDay(day);
        //setMonth(month);
        //setYear(year);
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


    protected AndroidAdvertisement(Parcel in) {
        advertiser = (IProfile) in.readValue(IProfile.class.getClassLoader());
        location = in.readString();
        title = in.readString();
        description = in.readString();
        category = (Category) in.readValue(Category.class.getClassLoader());
        day = in.readInt();
        month = in.readInt();
        year = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(advertiser);
        dest.writeString(location);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeValue(category);
        dest.writeInt(day);
        dest.writeInt(month);
        dest.writeInt(year);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<AndroidAdvertisement> CREATOR = new Parcelable.Creator<AndroidAdvertisement>() {
        @Override
        public AndroidAdvertisement createFromParcel(Parcel in) {
            return new AndroidAdvertisement(in);
        }

        @Override
        public AndroidAdvertisement[] newArray(int size) {
            return new AndroidAdvertisement[size];
        }
    };
}
