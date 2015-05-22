package edu.ctl.pinjobs.profile.view;

import android.widget.EditText;

import com.example.filips.dat367_grupp10.R;

import edu.ctl.pinjobs.eventbus.EventBus;
import edu.ctl.pinjobs.controller.EditMyProfileActivity;
import edu.ctl.pinjobs.profile.model.IProfile;
import edu.ctl.pinjobs.profile.model.WrongInputExeption;

/**
 * Created by Albertsson on 15-05-18.
 */
public class EditMyProfileView {

    private EditText editFirstName;
    private EditText editLastName;
    private EditText editPhone;
    private EditText editAddress;
    private EditText editCity;

    private IProfile myProfile;

    public EditMyProfileView(IProfile myProfile, EditMyProfileActivity editMyProfileActivity){

        this.myProfile = myProfile;

        this.editFirstName = (EditText)editMyProfileActivity.findViewById(R.id.myProfileFirstNameEditText);
        this.editLastName = (EditText)editMyProfileActivity.findViewById(R.id.myProfileLastNameEditText);
        this.editPhone = (EditText)editMyProfileActivity.findViewById(R.id.myProfilePhoneEditText);
        this.editAddress = (EditText)editMyProfileActivity.findViewById(R.id.myProfileAddressEditText);
        this.editCity = (EditText)editMyProfileActivity.findViewById(R.id.myProfileCityEditText);

        insertProfileInfoOnEditPage();
    }

    //Inserts profileInfo to Edit page
    public void insertProfileInfoOnEditPage(){
        editFirstName.setText(myProfile.getFirstName());
        editLastName.setText(myProfile.getLastName());
        editPhone.setText(myProfile.getPhone());
        editAddress.setText(myProfile.getAddress().split(",")[0]);
        editCity.setText(myProfile.getAddress().split(",")[1]);
    }

    public String getTextFromEditFirstName() {
        return editFirstName.getText().toString().trim();
    }

    public String getTextFromEditLastName() {
        return editLastName.getText().toString().trim();
    }

    public String getTextFromEditPhone() {
        return editPhone.getText().toString().trim();
    }

    public String getTextFromEditAddress() {
        return editAddress.getText().toString().trim();
    }

    public String getTextFromEditCity() {
        return editCity.getText().toString().trim();
    }

    public void setErrorEditFirstName(String s) {
        editFirstName.setError(s);
    }

    public void setErrorEditLastName(String s) {
        editLastName.setError(s);
    }

    public void setErrorEditPhone(String s) {
        editPhone.setError(s);
    }

    public void setErrorEditAddress(String s) {
        editAddress.setError(s);
    }

    public void setErrorEditCity(String s) {
        editCity.setError(s);
    }
}
