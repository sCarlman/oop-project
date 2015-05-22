package edu.ctl.pinjobs.profile.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.filips.dat367_grupp10.R;

import edu.ctl.pinjobs.eventbus.EventBus;
import edu.ctl.pinjobs.controller.CreateProfileActivity;
import edu.ctl.pinjobs.profile.model.Profile;
import edu.ctl.pinjobs.profile.model.WrongInputExeption;

/**
 * Created by Isaac on 2015-05-06.
 *
 * View made for create profile
 * More discription *!*!*!*!*!*!
 */
public class CreateProfileView {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private EditText passwordEditText;
    private EditText locationEditText;
    private EditText cityEditText;

    private Button createProfileButton;

    private boolean cancel = false;
    private View focusView = null;

    public CreateProfileView(Activity createProfileActivity, View.OnClickListener v) {

        this.firstNameEditText = (EditText)createProfileActivity.findViewById(R.id.myProfileFirstNameEditText);
        this.lastNameEditText = (EditText)createProfileActivity.findViewById(R.id.myProfileLastNameEditText);
        this.emailEditText = (EditText)createProfileActivity.findViewById(R.id.emailEditText);
        this.phoneEditText = (EditText)createProfileActivity.findViewById(R.id.myProfilePhoneEditText);
        this.passwordEditText = (EditText)createProfileActivity.findViewById(R.id.passwordEditText);
        this.locationEditText = (EditText)createProfileActivity.findViewById(R.id.myProfileAddressEditText);
        this.cityEditText = (EditText)createProfileActivity.findViewById(R.id.myProfileCityEditText);
        this.createProfileButton = (Button)createProfileActivity.findViewById(R.id.createProfileButton);
        this.createProfileButton.setOnClickListener(v);
    }

    public boolean attemptCreateProfile(){

        //reset cancel and focusView
        this.cancel = false;
        this.focusView = null;

        //resets colors for EditText and sets all textfields error to null
        resetTextFields(firstNameEditText);
        resetTextFields(lastNameEditText);
        resetTextFields(emailEditText);
        resetTextFields(phoneEditText);
        resetTextFields(passwordEditText);
        resetTextFields(locationEditText);
        resetTextFields(cityEditText);

        //Check if textFields is empty
        //and if then set error
        checkFields(firstNameEditText, "Förnamn ej ifyllt");
        checkFields(lastNameEditText, "Efternamn ej ifyllt");
        checkFields(emailEditText, "E-mail ej ifyllt");
        checkFields(phoneEditText, "Telefonnummer ej ifyllt");
        checkFields(passwordEditText, "Lösenord ej ifyllt");
        checkFields(locationEditText, "Gatuadress ej ifyllt");
        checkFields(cityEditText, "Stad ej ifyllt");


        if (cancel) {
            // There was an error; don't attempt createProfile and focus the first
            // form field with an error.
            focusView.requestFocus();
        }return cancel;
    }

    //Make check if textfields is empty
    private void checkFields(EditText e, String error){
        if(TextUtils.isEmpty(e.getText().toString().trim())){
            e.setError(error);
            e.setHintTextColor(Color.RED);

            this.cancel = true;
            this.focusView = e;
        }
    }

    //Resets colors to black and error to null
    public void resetTextFields(EditText e){
        e.setTextColor(Color.BLACK);
        e.setHintTextColor(Color.BLACK);
        e.setError(null);
    }

    public void firstnameExceptionCought(){
        firstNameEditText.setError("Förnamn ej Gilltligt");
        firstNameEditText.requestFocus();
    }

    public void lastnameExceptionCought(){
        lastNameEditText.setError("Efternamn ej Gilltligt");
        lastNameEditText.requestFocus();
    }

    public void passwordExceptionCought(){
        passwordEditText.setError("Lösenord ej Gilltligt");
        passwordEditText.requestFocus();
    }

    public void emailExceptionCought(){
        emailEditText.setError("Email ej Gilltligt");
        emailEditText.requestFocus();
    }

    public void phoneExceptionCought(){
        phoneEditText.setError("Telefon ej Gilltligt");
        phoneEditText.requestFocus();
    }

    //Skall delas upp i två
    public void locationExceptionCought(){
        cityEditText.setError("Stad ej Gilltligt");
        cityEditText.requestFocus();
        locationEditText.setError("Adress ej Gilltligt");
        locationEditText.requestFocus();
    }

    public String getTextFromFirstNameEditText() {
        return firstNameEditText.getText().toString().trim();
    }

    public String getTextFromLastNameEditText() {
        return lastNameEditText.getText().toString().trim();
    }

    public String getTextFromEmailEditText() {
        return emailEditText.getText().toString().trim();
    }

    public String getTextFromPhoneEditText() {
        return phoneEditText.getText().toString().trim();
    }

    public String getTextFromPasswordEditText() {
        return passwordEditText.getText().toString().trim();
    }

    public String getTextFromLocationEditText() {
        return locationEditText.getText().toString().trim();
    }

    public String getTextFromCityEditText() {
        return cityEditText.getText().toString().trim();
    }
}
