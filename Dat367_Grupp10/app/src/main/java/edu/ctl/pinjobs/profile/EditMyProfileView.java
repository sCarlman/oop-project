package edu.ctl.pinjobs.profile;

import android.widget.EditText;

import edu.ctl.pinjobs.eventbus.EventBus;

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

        try{
            profile.setFirstName(editFirstName.getText().toString());
        }catch (WrongInputExeption e){
            editFirstName.setError("Ogiltligt Namn!");
        }
        try{
            profile.setLastName(editLastName.getText().toString());
        }catch (WrongInputExeption e){
            editLastName.setError("Ogiltligt Efternamn!");
        }
        try{
            profile.setPhone(editPhone.getText().toString());
        }catch (WrongInputExeption e){
            editPhone.setError("Ogiltligt Tele!");
        }
        try{
            profile.setAddress(editAddress.getText().toString() + "," + editCity.getText().toString());
        }catch (WrongInputExeption e){
            editAddress.setError("Ogiltligt!");
            editCity.setError("Ogiltligt!");
        }

        EventBus.INSTANCE.publish(EventBus.Event.UPDATE_PROFILE, profile);


    }
}