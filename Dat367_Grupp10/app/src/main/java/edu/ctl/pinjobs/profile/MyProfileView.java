package edu.ctl.pinjobs.profile;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Albertsson on 15-05-14.
 */
public class MyProfileView {

    private TextView name;
    private TextView profileName;
    private TextView mail;
    private TextView profileMail;
    private TextView phone;
    private TextView profilePhone;
    private TextView address;
    private TextView profileAddress;
    private TextView city;
    private TextView profileCity;

    private Button showMyAds;

    private IProfile myProfile;

    public MyProfileView(TextView name, TextView profileName, TextView mail, TextView profileMail,
                         TextView phone, TextView profilePhone, TextView address, TextView profileAddress,
                         TextView city, TextView profileCity, Button showMyAds, IProfile myProfile,
                         View.OnClickListener activity){

        this.name = name;
        this.profileName = profileName;
        this.mail = mail;
        this.profileMail = profileMail;
        this.phone = phone;
        this.profilePhone = profilePhone;
        this.address = address;
        this.profileAddress = profileAddress;
        this.city = city;
        this.profileCity = profileCity;
        this.showMyAds = showMyAds;
        this.myProfile = myProfile;
        showMyAds.setOnClickListener(activity);
        setProfileInfoOnCreate();

    }

    public void setProfileInfoOnCreate(){
        profileName.setText(myProfile.getFirstName() + " " + myProfile.getLastName());
        profileMail.setText(myProfile.getEmail());
        profilePhone.setText(myProfile.getPhone());
        profileAddress.setText((myProfile.getAddress()));
    }
}
