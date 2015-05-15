package edu.ctl.pinjobs.profile;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.filips.dat367_grupp10.R;

public class MyProfileActivity extends ActionBarActivity {

    private MyProfileView myProfileView;
    public IProfile myProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();

        IProfile myProfile = (IProfile)bundle.getSerializable("sendProfile");

        myProfileView = new MyProfileView((TextView)findViewById(R.id.myProfileNameTextView), (TextView)findViewById(R.id.myProfileProfileNameTextView),
                (TextView)findViewById(R.id.myProfileMailTextView), (TextView)findViewById(R.id.myProfileProfileMailTextView),
                (TextView)findViewById(R.id.myProfilePhoneTextView), (TextView)findViewById(R.id.myProfileProfilePhoneTextView),
                (TextView)findViewById(R.id.myProfileAddressTextView), (TextView)findViewById(R.id.myProfileProfileAdressTextView),
                (TextView)findViewById(R.id.myProfileCityTextEdit), (TextView)findViewById(R.id.myProfileProfileCityTextView),
                (Button)findViewById(R.id.myProfileMyAdsButton), myProfile);
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
                //Method open profile
                return true;
            case R.id.action_settings:
                //open setings method
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
