package edu.ctl.pinjobs.controller;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.ctl.pinjobs.model.Profile;
import edu.ctl.pinjobs.Services.ProfileService;
import com.example.filips.dat367_grupp10.R;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;


public class CreateProfileActivity extends ActionBarActivity implements View.OnClickListener{
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private EditText addressEditText;
    private Button createProfileButton;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private ParseGeoPoint preferredLocation;
    private Profile newProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile);
        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);
        addressEditText = (EditText) findViewById(R.id.passwordEditText);
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
        addressEditText.getText().toString().trim();
        //preferredLocation = addressEditText.getText().toString().trim();; //needs some map function
        newProfile = new Profile(firstName, lastName, email, phone, preferredLocation);
        ProfileService.saveProfile(newProfile);
    }
}
