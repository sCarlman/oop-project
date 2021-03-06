package edu.ctl.pinjobs.user.controller;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.filips.dat367_grupp10.R;

import java.util.List;

import edu.ctl.pinjobs.profile.model.UserModel;
import edu.ctl.pinjobs.profile.controller.CreateProfileActivity;
import edu.ctl.pinjobs.profile.service.IProfileService;
import edu.ctl.pinjobs.profile.model.IProfile;
import edu.ctl.pinjobs.user.model.LoginModel;
import edu.ctl.pinjobs.user.view.LoginView;

public class LoginActivity extends ActionBarActivity {

    private LoginView loginView;
    private LoginModel loginModel;
    private IProfileService profileService;

    private final int PROFILE_CREATED = 5;
    private final int LOGGIN_SUCCESS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.loginView = new LoginView(this);
        this.loginModel = new LoginModel();
        this.profileService = (IProfileService)this.getIntent().getSerializableExtra("PROFILE_SERVICE");
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(resultCode == PROFILE_CREATED){
            setResult(LOGGIN_SUCCESS);
            finish();
        }
    }

    public void openCreateProfileView(View view) {
        //OnClick for pressing "Registrer dig här"
        Intent intent = new Intent(getApplicationContext(),CreateProfileActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("PROFILE_SERVICE",profileService);
        intent.putExtras(bundle);
        startActivityForResult(intent, 1);
    }

    public void testView(View view){
        if(loginView.attemptLoginSucces()){ //the view checks if fields are empty
            List<IProfile> list = profileService.fetchAllProfiles();
            loginModel.setEmail(loginView.getTextFromEmailField());
            loginModel.setPassword(loginView.getTextFromPasswordField());
            if(loginModel.doesMailAndPasswordExistInList(list)){
                UserModel.getInstance().logIn(loginModel.getProfile());
                setResult(LOGGIN_SUCCESS);
                finish();
            }else{
                if(loginModel.doesMailExistInList(list)==null) {
                    loginView.setInputError("email");
                }else {
                    loginView.setInputError("password");
                }
            }
        }
    }

}
