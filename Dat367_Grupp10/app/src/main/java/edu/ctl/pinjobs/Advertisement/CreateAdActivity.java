package edu.ctl.pinjobs.Advertisement;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.filips.dat367_grupp10.R;

import edu.ctl.pinjobs.Services.AdvertisementService;
import edu.ctl.pinjobs.Services.EventBus;
import edu.ctl.pinjobs.Services.IAdvertisementService;
import edu.ctl.pinjobs.User.UserModel;


public class CreateAdActivity extends ActionBarActivity implements View.OnClickListener,
        EventBus.IEventHandler{

    private IAdvertisementService service;
    private CreateAdView view;
    private UserModel user = UserModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ad);

        view = new CreateAdView((EditText) findViewById(R.id.adLocationEditText),
                (EditText) findViewById(R.id.adDescriptionEditText),
                (EditText) findViewById(R.id.adTitleEditText),
                (RadioButton) findViewById(R.id.gardenRadioButton),
                (RadioButton) findViewById(R.id.labourRadioButton),
                (RadioButton) findViewById(R.id.otherRadioButton),
                (Button) findViewById(R.id.createAdButton), this);

        if (user.getIsLoggedIn()){
            view.setNewProfile(user.getProfile());
            EventBus.INSTANCE.addListener(this);
        }else{
            view.notLoggedIn(this);
            finish();
        }

        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_ad, menu);
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

    public void onClick(View v){
        if (v == findViewById(R.id.createAdButton) && user.getIsLoggedIn()){
            view.createAd();
        }else if(v == findViewById(R.id.createAdButton) && !(user.getIsLoggedIn())){
            view.notLoggedIn(this);
        }
    }

    @Override
    public void onEvent(EventBus.Event evt, Object o) {
        if(evt == EventBus.Event.POST_AD){
            service = new AdvertisementService();
            service.saveAd((IAdvertisement)o);
            view.AdPosted(CreateAdActivity.this);
            finish();
        }
    }
}