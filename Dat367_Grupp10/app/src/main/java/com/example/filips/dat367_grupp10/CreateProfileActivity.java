package com.example.filips.dat367_grupp10;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;


public class CreateProfileActivity extends ActionBarActivity implements View.OnClickListener{
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private EditText passwordEditText;
    private Button createProfileButton;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String preferredLocation;
    private String password;
    private Profile newProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile);
        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        createProfileButton = (Button) findViewById(R.id.createProfileButton);
        createProfileButton.setOnClickListener(this);

        // Test creation of object
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        firstName = firstNameEditText.getText().toString().trim();
        lastName = lastNameEditText.getText().toString().trim();
        email = emailEditText.getText().toString().trim();
        phone = phoneEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();
        preferredLocation = ""; //tills vidare tom...

        newProfile = new Profile(firstName, lastName, email, phone, preferredLocation);
        ParseUser PinJobProfile = new ParseUser();
        PinJobProfile.setEmail(email);
        PinJobProfile.setPassword(password);
        PinJobProfile.setUsername(firstName + "_" + lastName);
        PinJobProfile.put("phone", phone);

        PinJobProfile.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                //TODO: hantera det...
                if (e != null) {
                    // Show the error message
                    Toast.makeText(CreateProfileActivity.this, e.getMessage(),
                            Toast.LENGTH_LONG).show();
                } else {
                    // Start an intent for the dispatch activity
                    Intent intent = new Intent(CreateProfileActivity.this, DispatchActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
        //newProfile = new Profile(firstName, lastName, email, phone, preferredLocation);
        //LoggedIn.loggaIn();
        //LoggedIn.chosenProfile(newProfile);


    }
}
