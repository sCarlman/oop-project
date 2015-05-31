package edu.ctl.pinjobs.profile.view;

import android.app.Activity;
import android.widget.EditText;

import com.example.filips.dat367_grupp10.R;

import edu.ctl.pinjobs.profile.model.IProfile;


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

    public EditMyProfileView(IProfile myProfile, Activity editMyProfileActivity){

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

    //get methods so the controller can recive texts from textFields
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

    //sets error on textField if exception cought from Profile
    public void setInputError(String error){
        switch (error){
            case "firstname": editFirstName.setError("Endast bokstäver: A-Ö");
                editFirstName.requestFocus();
                break;
            case "lastname": editLastName.setError("Endast bokstäver: A-Ö");
                editLastName.requestFocus();
                break;
            case "phone": editPhone.setError("Format: 07xxxxxxxx");
                editPhone.requestFocus();
                break;
            case "address": editAddress.setError("Ej giltig adress");
                editAddress.requestFocus();
                break;
            case "city": editCity.setError("Endast bokstäver: A-Ö");
                editCity.requestFocus();
                break;
        }
    }
}
