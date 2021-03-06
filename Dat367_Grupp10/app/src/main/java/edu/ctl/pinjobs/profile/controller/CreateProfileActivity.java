package edu.ctl.pinjobs.profile.controller;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.filips.dat367_grupp10.R;

import java.util.List;

import edu.ctl.pinjobs.profile.model.IProfile;
import edu.ctl.pinjobs.profile.model.Profile;
import edu.ctl.pinjobs.profile.model.UserModel;
import edu.ctl.pinjobs.profile.model.WrongInputException;
import edu.ctl.pinjobs.profile.service.IProfileService;
import edu.ctl.pinjobs.profile.view.CreateProfileView;

public class CreateProfileActivity extends ActionBarActivity implements View.OnClickListener {

    private CreateProfileView view;
    private Profile newProfile;
    private IProfileService profileService;

    private final int PROFILE_CREATED = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile);

        Bundle bundle = getIntent().getExtras();

        if(bundle!=null){
            profileService =(IProfileService)bundle.getSerializable("PROFILE_SERVICE");
        }
        //constructor has parameters (Activity, OnCLickListner)
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

    private boolean checkIfEmailExistsInList(List<IProfile> profilesList,String email){
        for(IProfile profile : profilesList ) {
            if (profile.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == findViewById(R.id.createProfileButton)) {
            if(!view.attemptCreateProfile()){
                try{
                    newProfile = new Profile(view.getTextFromFirstNameEditText(), view.getTextFromLastNameEditText(),
                            view.getTextFromPasswordEditText(), view.getTextFromEmailEditText(), view.getTextFromPhoneEditText(),
                            view.getTextFromLocationEditText() + "," + view.getTextFromCityEditText());

                    if(checkIfEmailExistsInList(profileService.fetchAllProfiles(), newProfile.getEmail())){
                        //checks if e-mail already exists in database
                        throw new WrongInputException("EMAIL_EXISTS");
                    }else{
                        Toast.makeText(this, "Profil skapad!", Toast.LENGTH_LONG).show();
                        profileService.saveProfile(newProfile);
                        UserModel.getInstance().logIn(newProfile);
                        setResult(PROFILE_CREATED);
                        finish();
                    }
                }catch (WrongInputException e){
                    view.exceptionCought(e.getError());
                }
            }
        }
    }
}
