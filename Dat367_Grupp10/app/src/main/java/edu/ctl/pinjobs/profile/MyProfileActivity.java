package edu.ctl.pinjobs.profile;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.filips.dat367_grupp10.R;

import edu.ctl.pinjobs.Handler.UserListActivity;
import edu.ctl.pinjobs.eventbus.EventBus;

public class MyProfileActivity extends ActionBarActivity implements View.OnClickListener{

    private MyProfileView myProfileView;
    private IProfile myProfile;
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();

        myProfile = (IProfile)bundle.getSerializable("sendProfile");
        email = myProfile.getEmail();

        myProfileView = new MyProfileView((TextView)findViewById(R.id.myProfileNameTextView), (TextView)findViewById(R.id.myProfileProfileNameTextView),
                (TextView)findViewById(R.id.myProfileMailTextView), (TextView)findViewById(R.id.myProfileProfileMailTextView),
                (TextView)findViewById(R.id.myProfilePhoneTextView), (TextView)findViewById(R.id.myProfileProfilePhoneTextView),
                (TextView)findViewById(R.id.myProfileAddressTextView), (TextView)findViewById(R.id.myProfileProfileAdressTextView),
                (TextView)findViewById(R.id.myProfileCityTextEdit), (TextView)findViewById(R.id.myProfileProfileCityTextView),
                (Button)findViewById(R.id.myProfileMyAdsButton), myProfile, this, (EditText)findViewById(R.id.myProfileFirstNameEditText),
                (EditText)findViewById(R.id.myProfileLastNameEditText), (EditText)findViewById(R.id.myProfilePhoneEditText),
                (EditText)findViewById(R.id.myProfileAddressEditText), (EditText)findViewById(R.id.myProfileCityEditText),
                (Button)findViewById(R.id.myProfileSaveEditProfileButton));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_my_profile, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_edit:
                myProfileView.setupEditProfilePage();
                return true;
            case R.id.action_settings:
                //open setings method
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        EventBus.INSTANCE.publish(EventBus.Event.SHOW_MY_ADS, email);

    }


}
