package edu.ctl.pinjobs.profile;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.ctl.pinjobs.profile.Profile;
import edu.ctl.pinjobs.Services.ProfileService;
import com.example.filips.dat367_grupp10.R;
import com.parse.ParseObject;


public class CreateProfileActivity extends ActionBarActivity implements View.OnClickListener{
    private CreateProfileView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile);
        view = new CreateProfileView((EditText) findViewById(R.id.firstNameEditText),
                (EditText) findViewById(R.id.lastNameEditText),
                (EditText) findViewById(R.id.emailEditText),
                (EditText) findViewById(R.id.phoneEditText),
                (EditText) findViewById(R.id.passwordEditText),
                (EditText) findViewById(R.id.addressEditText));
        //createProfileButton = (Button) findViewById(R.id.createProfileButton);
        //createProfileButton.setOnClickListener(this);
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
        //firstName = firstNameEditText.getText().toString().trim();
        //lastName = lastNameEditText.getText().toString().trim();
        //email = emailEditText.getText().toString().trim();
        //phone = phoneEditText.getText().toString().trim();
        //addressEditText.getText().toString().trim();
        //preferredLocation = addressEditText.getText().toString().trim();; //needs some map function
        //newProfile = new Profile(firstName, lastName, email, phone, preferredLocation);
        //ProfileService.saveProfile(newProfile);
    }
}
