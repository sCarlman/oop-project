package edu.ctl.pinjobs.User;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.filips.dat367_grupp10.R;

import java.util.List;

import edu.ctl.pinjobs.Services.EventBus;
import edu.ctl.pinjobs.Services.IProfileService;
import edu.ctl.pinjobs.Services.ProfileService;
import edu.ctl.pinjobs.profile.IProfile;
import edu.ctl.pinjobs.profile.Profile;

public class LoginActivity extends ActionBarActivity implements EventBus.IEventHandler {

    private LoginView view;
    private LoginModel loginModel;
    private IProfileService service = new ProfileService();
    private UserModel userModel = new UserModel();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginView view = new LoginView((EditText)findViewById(R.id.editText), (EditText)findViewById(R.id.pwdEditText));
        this.view = view;
        EventBus.INSTANCE.addListener(this);
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

    public void testView(View view){
        this.view.attemptLogin();
    }

    @Override
    public void onEvent(EventBus.Event evt, Object o) {
        if(evt == EventBus.Event.LOGIN_MATCH){
            List<IProfile> profileList = service.fetchAllProfiles();
            if(profileList == null){
                Toast.makeText(this, "Finns inga registrerade profiler", Toast.LENGTH_LONG).show();
            }else{
                System.out.println("****NU SKALL DET KOLLAS OM MAIL FINNS!******");
                this.loginModel.doesMailExistInUserDatabase(profileList);
            }

        }
        if(evt == EventBus.Event.LOGIN_SUCCESS){
            System.out.println("*****!!!!!**** SOULD BE FALSE >>>>>>" + userModel.getIsLoggedIn());
            userModel.setLoggedIn(true);
            System.out.println("!!!!!******!!!!!! SOULD BE TRUE >>>>>>" + userModel.getIsLoggedIn());
        }
    }
}
