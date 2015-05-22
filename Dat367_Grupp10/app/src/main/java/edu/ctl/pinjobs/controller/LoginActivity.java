package edu.ctl.pinjobs.controller;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.filips.dat367_grupp10.R;

import java.util.List;

import edu.ctl.pinjobs.eventbus.EventBus;
import edu.ctl.pinjobs.main.UserModel;
import edu.ctl.pinjobs.services.IProfileService;
import edu.ctl.pinjobs.services.ProfileService;
import edu.ctl.pinjobs.profile.model.IProfile;
import edu.ctl.pinjobs.user.model.LoginModel;
import edu.ctl.pinjobs.user.view.LoginView;

public class LoginActivity extends ActionBarActivity {

    private LoginView loginView;
    private LoginModel loginModel;
    private IProfileService profileService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.loginView = new LoginView(this);
        this.loginModel = new LoginModel();
        this.profileService = new ProfileService();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public void openCreateProfileView(View view) {
        Intent intent = new Intent(getApplicationContext(),CreateProfileActivity.class);
        startActivity(intent);
    }

    public void testView(View view){
        if(loginView.attemptLoginSucces()){
            List<IProfile> list = profileService.fetchAllProfiles();
            loginModel.setEmail(loginView.getTextFromEmailField());
            loginModel.setPassword(loginView.getTextFromPasswordField());
            if(loginModel.doesMailAndPasswordExistInList(list)){
                UserModel.getInstance().logIn(loginModel.getProfile());
                setResult(5);
                finish();

            }else{
                if(loginModel.doesMailExistInList(list)!=null) {
                    //TODO: ÄNDRA TILL SWITCH ERRORS
                    System.out.println("ERROR I EMAIL");
                }else {
                    System.out.println("ERROR I PASSWORD");
                }
            }
        }
    }

}