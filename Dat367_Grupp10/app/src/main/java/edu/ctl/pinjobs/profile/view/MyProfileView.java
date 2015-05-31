package edu.ctl.pinjobs.profile.view;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.filips.dat367_grupp10.R;

import edu.ctl.pinjobs.profile.model.IProfile;

/**
 * Created by Albertsson on 15-05-14.
 */
public class MyProfileView {

    private TextView profileName;
    private TextView profileMail;
    private TextView profilePhone;
    private TextView profileAddress;
    private TextView profileCity;

    private Button showMyAds;

    public MyProfileView(Activity myProfileActivity, IProfile myProfile, View.OnClickListener activity){

        this.profileName = (TextView)myProfileActivity.findViewById(R.id.myProfileProfileNameTextView);
        this.profileMail = (TextView)myProfileActivity.findViewById(R.id.myProfileProfileMailTextView);
        this.profilePhone = (TextView)myProfileActivity.findViewById(R.id.myProfileProfilePhoneTextView);
        this.profileAddress = (TextView)myProfileActivity.findViewById(R.id.myProfileProfileAdressTextView);
        this.profileCity = (TextView)myProfileActivity.findViewById(R.id.myProfileProfileCityTextView);

        this.showMyAds = (Button)myProfileActivity.findViewById(R.id.myProfileMyAdsButton);

        showMyAds.setOnClickListener(activity);

        setProfileInfoOnCreate(myProfile);
    }

    public void setProfileInfoOnCreate(IProfile profile){
        profileName.setText(profile.getFirstName() + " " + profile.getLastName());
        profileMail.setText(profile.getEmail());
        profilePhone.setText(profile.getPhone());
        // Splits the address string and puts streetaddres on one field
        //and city on another
        profileAddress.setText((profile.getAddress().split(",")[0]));
        profileCity.setText((profile.getAddress().split(",")[1]));
    }
}
