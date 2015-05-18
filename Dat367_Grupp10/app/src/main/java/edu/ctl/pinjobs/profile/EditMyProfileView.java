package edu.ctl.pinjobs.profile;

import android.widget.EditText;

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

    public EditMyProfileView(IProfile myProfile, EditText firstName, EditText lastName, EditText phone,
                             EditText Address, EditText city){

        this.myProfile = myProfile;

        this.editFirstName = firstName;
        this.editLastName = lastName;
        this.editPhone = phone;
        this.editAddress = Address;
        this.editCity = city;

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

    //On click saveEditButton
    //The profile gets inputs from text fields.
    public void saveTextFieldsToProfile(IProfile profile){
        profile.setFirstName(editFirstName.getText().toString());
        profile.setLastName(editLastName.getText().toString());
        profile.setPhone(editPhone.getText().toString());
        profile.setAddress(editAddress.getText().toString() + "," + editCity.getText().toString());
    }
}
