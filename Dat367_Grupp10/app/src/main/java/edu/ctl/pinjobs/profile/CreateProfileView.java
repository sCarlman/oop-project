package edu.ctl.pinjobs.profile;


import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Isaac on 2015-05-06.
 */
public class CreateProfileView {
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private EditText passwordEditText;
    private EditText addressEditText;
    private Button createProfileButton;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String address;

    CreateProfileView(EditText firstNameEditText, EditText lastNameEditText,
                      EditText emailEditText, EditText phoneEditText, EditText passwordEditText,
                      EditText addressEditText, Button createProfileButton) {
        this.firstNameEditText = firstNameEditText;
        this.lastNameEditText = lastNameEditText;
        this.emailEditText = emailEditText;
        this.phoneEditText = phoneEditText;
        this.passwordEditText = passwordEditText;
        this.addressEditText = addressEditText;
        this.createProfileButton = createProfileButton;
    }

    public void createProfileButtonClicked() {
        firstName = firstNameEditText.getText().toString().trim();
        lastName = lastNameEditText.getText().toString().trim();
        email = emailEditText.getText().toString().trim();
        phone = phoneEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();
        address = addressEditText.getText().toString().trim();

        
    }
}
