package edu.ctl.pinjobs.controller;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.filips.dat367_grupp10.R;

import edu.ctl.pinjobs.eventbus.EventBus;
import edu.ctl.pinjobs.profile.model.Profile;
import edu.ctl.pinjobs.profile.model.WrongInputExeption;
import edu.ctl.pinjobs.profile.view.CreateProfileView;

public class CreateProfileActivity extends ActionBarActivity implements View.OnClickListener {

    private CreateProfileView view;
    private Profile newProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile);

        view = new CreateProfileView(this, this);
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
        if (v == findViewById(R.id.createProfileButton)) {
            if(!view.attemptCreateProfile()){
                try{
                    newProfile = new Profile(view.getTextFromFirstNameEditText(), view.getTextFromLastNameEditText(),
                            view.getTextFromPasswordEditText(), view.getTextFromEmailEditText(), view.getTextFromPhoneEditText(),
                            view.getTextFromLocationEditText() + "," + view.getTextFromCityEditText());
                    finish();
                    Toast.makeText( this, "Profile created!", Toast.LENGTH_LONG).show();

                    //*!*!*! DENNA SKALL BORT!!! *!*!*!*!*!*!
                    EventBus.INSTANCE.publish(EventBus.Event.SAVE_PROFILE, newProfile);
                }catch (WrongInputExeption e){
                    if(e.getError().equals("FirstName")){
                        view.firstnameExceptionCought();
                    }
                    if(e.getError().equals("LastName")){
                        view.lastnameExceptionCought();
                    }
                    if(e.getError().equals("Password")){
                        view.passwordExceptionCought();
                    }
                    if(e.getError().equals("Email")){
                        view.emailExceptionCought();
                    }
                    if(e.getError().equals("Phone")){
                        view.phoneExceptionCought();
                    }
                    //SKALL DELAS UPP I ADRESS OCH STAD !*!*!*!*!*!*!
                    if(e.getError().equals("Location")){
                        view.locationExceptionCought();
                    }
                }
            }
        }
    }
}
