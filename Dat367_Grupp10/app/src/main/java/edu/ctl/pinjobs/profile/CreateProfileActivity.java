package edu.ctl.pinjobs.profile;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.filips.dat367_grupp10.R;

public class CreateProfileActivity extends ActionBarActivity implements View.OnClickListener {

    private CreateProfileView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile);

        view = new CreateProfileView((EditText) findViewById(R.id.myProfileFirstNameEditText),
                (EditText) findViewById(R.id.myProfileLastNameEditText),
                (EditText) findViewById(R.id.emailEditText),
                (EditText) findViewById(R.id.myProfilePhoneEditText),
                (EditText) findViewById(R.id.passwordEditText),
                (EditText) findViewById(R.id.myProfileAddressEditText),
                (Button) findViewById(R.id.createProfileButton), this,
                (EditText) findViewById(R.id.myProfileCityEditText));
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
            view.attemptCreateProfile();
        }
    }

}
