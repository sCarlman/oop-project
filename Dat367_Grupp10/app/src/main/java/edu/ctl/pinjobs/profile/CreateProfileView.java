package edu.ctl.pinjobs.profile;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.ctl.pinjobs.Services.EventBus;

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

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String address;

    private Profile newProfile;

    CreateProfileView(EditText firstNameEditText, EditText lastNameEditText,
                      EditText emailEditText, EditText phoneEditText, EditText passwordEditText,
                      EditText locationEditText, Button createProfileButton, View.OnClickListener v) {
        this.firstNameEditText = firstNameEditText;
        this.lastNameEditText = lastNameEditText;
        this.emailEditText = emailEditText;
        this.phoneEditText = phoneEditText;
        this.passwordEditText = passwordEditText;
        this.locationEditText = locationEditText;
        createProfileButton.setOnClickListener(v);
    }

    public void createProfileButtonClicked() {
        firstName = firstNameEditText.getText().toString().trim();
        lastName = lastNameEditText.getText().toString().trim();
        email = emailEditText.getText().toString().trim();
        phone = phoneEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();
        address = locationEditText.getText().toString().trim();
        newProfile = new Profile(firstName, lastName, password, email, phone, address);
        EventBus.INSTANCE.publish(EventBus.Event.SAVE_PROFILE, newProfile);
    }

    public void profileCreated(Context c) {
        Toast.makeText(c, "Profile created!", Toast.LENGTH_LONG).show();
    }
}
