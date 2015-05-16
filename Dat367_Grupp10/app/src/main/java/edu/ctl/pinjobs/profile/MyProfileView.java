package edu.ctl.pinjobs.profile;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private Button saveChangesButton;

    private IProfile myProfile;

    private EditText editFirstName;
    private EditText editLastName;
    private EditText editPhone;
    private EditText editAddress;
    private EditText editCity;

    public MyProfileView(TextView name, TextView profileName, TextView mail, TextView profileMail,
                         TextView phone, TextView profilePhone, TextView address, TextView profileAddress,
                         TextView city, TextView profileCity, Button showMyAds, IProfile myProfile, View.OnClickListener activity,
                         EditText editFirstName, EditText editLastName, EditText editPhone, EditText editAddress,
                         EditText editCity, Button saveChangesButton){

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

        this.editFirstName = editFirstName;
        this.editLastName = editLastName;
        this.editPhone = editPhone;
        this.editAddress = editAddress;
        this.editCity = editCity;
        this.saveChangesButton = saveChangesButton;

        setVisibilityForProfilePage();

        setProfileInfoOnCreate();

    }

    public void setProfileInfoOnCreate(){
        profileName.setText(myProfile.getFirstName() + " " + myProfile.getLastName());
        profileMail.setText(myProfile.getEmail());
        profilePhone.setText(myProfile.getPhone());
        profileAddress.setText((myProfile.getAddress().split(",")[0]));
        profileCity.setText((myProfile.getAddress().split(",")[1]));
    }

    public void setupEditProfilePage(){
        insertProfileInfoOnEditPage();
        setVisibilityForEditPage();
    }

    public void setVisibilityForProfilePage(){
        //Visible items
        name.setVisibility(View.VISIBLE);
        profileName.setVisibility(View.VISIBLE);
        mail.setVisibility(View.VISIBLE);
        profileMail.setVisibility(View.VISIBLE);
        phone.setVisibility(View.VISIBLE);
        profilePhone.setVisibility(View.VISIBLE);
        address.setVisibility(View.VISIBLE);
        profileAddress.setVisibility(View.VISIBLE);
        city.setVisibility(View.VISIBLE);
        profileCity.setVisibility(View.VISIBLE);
        showMyAds.setVisibility(View.VISIBLE);

        //Non visible items
        editFirstName.setVisibility(View.GONE);
        editLastName.setVisibility(View.GONE);
        editPhone.setVisibility(View.GONE);
        editAddress.setVisibility(View.GONE);
        editCity.setVisibility(View.GONE);
        saveChangesButton.setVisibility(View.GONE);
    }

    public void setVisibilityForEditPage(){
        //Non Visible items
        name.setVisibility(View.GONE);
        profileName.setVisibility(View.GONE);
        mail.setVisibility(View.GONE);
        profileMail.setVisibility(View.GONE);
        phone.setVisibility(View.GONE);
        profilePhone.setVisibility(View.GONE);
        address.setVisibility(View.GONE);
        profileAddress.setVisibility(View.GONE);
        city.setVisibility(View.GONE);
        profileCity.setVisibility(View.GONE);
        showMyAds.setVisibility(View.GONE);

        //Visible items
        editFirstName.setVisibility(View.VISIBLE);
        editLastName.setVisibility(View.VISIBLE);
        editPhone.setVisibility(View.VISIBLE);
        editAddress.setVisibility(View.VISIBLE);
        editCity.setVisibility(View.VISIBLE);
        saveChangesButton.setVisibility(View.VISIBLE);
    }

    public void insertProfileInfoOnEditPage(){
        editFirstName.setText(myProfile.getFirstName());
        editLastName.setText(myProfile.getLastName());
        editPhone.setText(myProfile.getPhone());
        editAddress.setText(myProfile.getAddress().split(",")[0]);
        editCity.setText(myProfile.getAddress().split(",")[1]);
    }


}
