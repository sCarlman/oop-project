package edu.ctl.pinjobs.controller;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.ctl.pinjobs.Advertisement.AndroidAdvertisement;
import edu.ctl.pinjobs.Advertisement.IAdvertisement;
import edu.ctl.pinjobs.Handler.HandlerActivity;
import edu.ctl.pinjobs.Handler.MapActivity;
import edu.ctl.pinjobs.Services.AdvertisementService;
import edu.ctl.pinjobs.Services.IAdvertisementService;
import edu.ctl.pinjobs.User.LoginActivity;
import edu.ctl.pinjobs.Utils.LocationUtils;
import edu.ctl.pinjobs.eventbus.EventBus;
import edu.ctl.pinjobs.profile.CreateProfileActivity;
import edu.ctl.pinjobs.Advertisement.CreateAdActivity;
import edu.ctl.pinjobs.profile.Profile;

import com.example.filips.dat367_grupp10.R;
import com.parse.Parse;


public class MainActivity extends ActionBarActivity implements View.OnClickListener, EventBus.IEventHandler{

    private MainView mainView;
    private LoginActivity loginActivity = new LoginActivity();
    private String profileName;
    IAdvertisementService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enable Local Datastore.
        // Parse.enableLocalDatastore(this);
        Parse.initialize(this, "W4QRsIPB5oFT6F6drmZi0BrxdPYPEYHY2GYSUU4q", "JpXn4VB0Y63wqNIf0qgvRGg7k3QmjfzJjD9qhzqE");

        EventBus.INSTANCE.addListener(this);

        //Gets boolean true if login success
        boolean login = getIntent().getBooleanExtra("LoginSuccess", false);
        EventBus.INSTANCE.addListener(this);

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,60000,100, new LocationUtils());


        this.mainView = new MainView((Button)findViewById(R.id.mapButton), (Button)findViewById(R.id.listButton),(Button)findViewById(R.id.postAdButton),
                (Button)findViewById(R.id.loginButton), (Button)findViewById(R.id.logOfButton), login, (TextView)findViewById(R.id.loggedInTextView),
                (Button)findViewById(R.id.modifyProfileButton), this);

        this.service = new AdvertisementService();
        service.removeOutDatedAds();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

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
    public void onClick(View view) {
        if(view == findViewById(R.id.modifyProfileButton)){
            Intent intent = new Intent(this, CreateProfileActivity.class);
            intent.putExtra("modify", true);
            startActivity(intent);
        }
    }

    public void openMapView(View view){
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    public void openCreateAdView(View view) {
        Intent intent = new Intent(getApplicationContext(), CreateAdActivity.class);
        startActivity(intent);
    }

    public void openListView(View view) {
        Intent intent = new Intent(getApplicationContext(), HandlerActivity.class);
        startActivity(intent);
    }

    public void openLoginView(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    public void logOfUser(View view){
        loginActivity.changeValueOfLoggedOfUser();
        mainView.repaintForLogOf();
    }

    public void setProfileNameForView(){
        String profileName = loginActivity.getProfileNameForMainView();
        this.profileName = profileName;
    }

    public String getProfileName() {
        return profileName;
    }

    @Override
    public void onEvent(EventBus.Event evt, Object o) {
        if(evt == EventBus.Event.POST_AD) {
            service = new AdvertisementService();
            service.saveAd((IAdvertisement) o);
            Intent intent = new Intent(this.getApplicationContext(), MapActivity.class);
            AndroidAdvertisement androidAD = new AndroidAdvertisement((IAdvertisement) o);
            intent.putExtra("Advertisement", androidAD);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.getApplicationContext().startActivity(intent);
        }
        if(evt == EventBus.Event.SET_BOOLEAN_LOGGED_IN){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("LoginSuccess", (boolean)o);
            startActivity(intent);
        }
    }
}

