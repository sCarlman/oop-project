package edu.ctl.pinjobs.profile;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.ctl.pinjobs.eventbus.EventBus;

/**
 * Created by Isaac on 2015-05-06.
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

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String address;
    private String city;

    private CreateProfileActivity activity;
    private boolean cancel = false;
    private View focusView = null;

    private Profile newProfile;

    CreateProfileView(EditText firstNameEditText, EditText lastNameEditText,
                      EditText emailEditText, EditText phoneEditText, EditText passwordEditText,
                      EditText locationEditText, Button createProfileButton, Context v,
                      EditText city) {

        this.firstNameEditText = firstNameEditText;
        this.lastNameEditText = lastNameEditText;
        this.emailEditText = emailEditText;
        this.phoneEditText = phoneEditText;
        this.passwordEditText = passwordEditText;
        this.locationEditText = locationEditText;
        this.cityEditText = city;
        this.createProfileButton = createProfileButton;
        this.createProfileButton.setOnClickListener((View.OnClickListener)v);
        this.activity = (CreateProfileActivity)v;
    }

    public void createProfileButtonClicked() {
        this.address = address + "," + city;
        try{
            newProfile = new Profile(firstName, lastName, password, email, phone, address);
            activity.finish();
            profileCreated(activity);
            EventBus.INSTANCE.publish(EventBus.Event.SAVE_PROFILE, newProfile);
        }catch (WrongInputExeption e){
            if(e.getError().equals("FirstName")){
                firstNameEditText.setError("Förnamn ej Gilltligt");
                firstNameEditText.requestFocus();
            }
            if(e.getError().equals("LastName")){
                lastNameEditText.setError("Efternamn ej Gilltligt");
                lastNameEditText.requestFocus();
            }
            if(e.getError().equals("Password")){
                passwordEditText.setError("Lösenord ej Gilltligt");
                passwordEditText.requestFocus();
            }
            if(e.getError().equals("Email")){
                emailEditText.setError("Email ej Gilltligt");
                emailEditText.requestFocus();
            }
            if(e.getError().equals("Phone")){
                phoneEditText.setError("Telefon ej Gilltligt");
                phoneEditText.requestFocus();
            }
            //SKALL DELAS UPP I ADRESS OCH STAD !*!*!*!*!*!*!
            if(e.getError().equals("Location")){
                cityEditText.setError("Stad ej Gilltligt");
                cityEditText.requestFocus();
                locationEditText.setError("Adress ej Gilltligt");
                locationEditText.requestFocus();
            }
        }
    }

    public void profileCreated(Context c) {
        Toast.makeText(c, "Profile created!", Toast.LENGTH_LONG).show();
    }

    public void attemptCreateProfile(){

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

        firstName = firstNameEditText.getText().toString().trim();
        lastName = lastNameEditText.getText().toString().trim();
        email = emailEditText.getText().toString().trim();
        phone = phoneEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();
        address = locationEditText.getText().toString().trim();
        city = cityEditText.getText().toString().trim();

        checkFields(firstName, firstNameEditText, "Förnamn ej ifyllt");
        checkFields(lastName, lastNameEditText, "Efternamn ej ifyllt");
        checkFields(email, emailEditText, "E-mail ej ifyllt");
        checkFields(phone, phoneEditText, "Telefonnummer ej ifyllt");
        checkFields(password, passwordEditText, "Lösenord ej ifyllt");
        checkFields(address, locationEditText, "Gatuadress ej ifyllt");
        checkFields(city, cityEditText, "Stad ej ifyllt");


        if (cancel) {
            // There was an error; don't attempt createProfile and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // perform the createProfile attempt.
            createProfileButtonClicked();
        }
    }

    //Make check if textfields is empty
    private void checkFields(String s, EditText e, String error){
        if(TextUtils.isEmpty(s)){
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
}
