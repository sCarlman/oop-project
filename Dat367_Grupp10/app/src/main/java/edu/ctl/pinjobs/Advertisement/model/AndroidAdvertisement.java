package edu.ctl.pinjobs.advertisement.model;

import android.os.Parcel;
import android.os.Parcelable;

import edu.ctl.pinjobs.profile.model.IProfile;

/**
 * Created by filiplarsson on 15-05-12.
 */
public class AndroidAdvertisement implements Parcelable{

    private IAdvertisement ad;
    private IProfile advertiser;
    private Category category;

    //Constructor for AndroidAdvertisement
    public AndroidAdvertisement(IAdvertisement ad) {
        this.ad = ad;
        this.advertiser = ad.getAdvertiser();
        this.category = ad.getCategory();
    }

    public IAdvertisement getAd(){
        return this.ad;
    }


    /*
    * Methods below are implemented to make the AndroidAdvertisement class
    * implement Parcelable
    */
    protected AndroidAdvertisement(Parcel in) {
        ad = (Advertisement) in.readValue(Advertisement.class.getClassLoader());
        advertiser = (IProfile) in.readValue(IProfile.class.getClassLoader());
        category = (Category) in.readValue(Category.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(ad);
        dest.writeValue(advertiser);
        dest.writeValue(category);
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

