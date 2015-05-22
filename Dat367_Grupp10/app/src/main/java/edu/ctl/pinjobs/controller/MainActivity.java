package edu.ctl.pinjobs.controller;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import edu.ctl.pinjobs.advertisement.model.AndroidAdvertisement;
import edu.ctl.pinjobs.advertisement.model.IAdvertisement;
import edu.ctl.pinjobs.handler.model.AdvertisementListHolder;
import edu.ctl.pinjobs.main.BackgroundThread;
import edu.ctl.pinjobs.main.MainView;
import edu.ctl.pinjobs.main.UserModel;
import edu.ctl.pinjobs.services.AdvertisementService;
import edu.ctl.pinjobs.services.IAdvertisementService;
import edu.ctl.pinjobs.services.IProfileService;
import edu.ctl.pinjobs.services.ProfileService;
import edu.ctl.pinjobs.user.model.LoginModel;
import edu.ctl.pinjobs.utils.LocationUtils;
import edu.ctl.pinjobs.eventbus.EventBus;
import edu.ctl.pinjobs.profile.model.IProfile;

import com.example.filips.dat367_grupp10.R;
import com.parse.Parse;

import java.util.List;


public class MainActivity extends ActionBarActivity implements EventBus.IEventHandler{

    private MainView mainView;
    private IProfileService profileService;
    private IAdvertisementService adService;
    private UserModel user = UserModel.getInstance();
    private BackgroundThread backgroundThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.initialize(this, "W4QRsIPB5oFT6F6drmZi0BrxdPYPEYHY2GYSUU4q", "JpXn4VB0Y63wqNIf0qgvRGg7k3QmjfzJjD9qhzqE");

        EventBus.INSTANCE.addListener(this);

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,60000,100, new LocationUtils());

        this.mainView = new MainView((Button)findViewById(R.id.mapButton),
                (Button)findViewById(R.id.listButton),(Button)findViewById(R.id.postAdButton),
                (Button)findViewById(R.id.loginButton), (Button)findViewById(R.id.logOfButton),
                (TextView)findViewById(R.id.loggedInTextView));

        this.adService = new AdvertisementService();
        profileService = new ProfileService();
        this.backgroundThread = new BackgroundThread(adService);
        backgroundThread.start();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_person:
                if(user.getIsLoggedIn() == true) {
                    openMyProfileView();
                }
                return true;
            case R.id.action_settings:
                //open setings method
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openMapView(View view){
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    public void openMyProfileView(){
        Intent intent = new Intent(this, MyProfileActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("sendProfile", user.getProfile());
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void openCreateAdView(View view) {
        Intent intent = new Intent(getApplicationContext(), CreateAdActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("sendProfile", user.getProfile());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void callCreateAd() {
        EventBus.INSTANCE.publish(EventBus.Event.CREATE_AD, user.getProfile());
    }

    public void openListView(View view) {
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        //UserModel um = UserModel.getInstance();
        if (user.getIsLoggedIn()==false){
            String email = null;
            intent.putExtra("Email", email);
            startActivity(intent);
        }else{
            String email = user.getProfile().getEmail();
            intent.putExtra("Email", email);
            startActivity(intent);
        }

    }

    public void openLoginView(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivityForResult(intent, 1);
    }

    public void logOfUser(View view){
        user.logOff();
        mainView.repaintLogInView(false,null);
    }


    @Override
    public void onResume(){
        super.onResume();
        BackgroundThread thread = new BackgroundThread(adService);
        thread.start();
        UserModel userModel = UserModel.getInstance();
        mainView.repaintLogInView(userModel.isLoggedIn, userModel.getProfile());
    }

    @Override
    public void onEvent(EventBus.Event evt, Object o) {
        if (evt == EventBus.Event.POST_AD) {
            adService.saveAd((IAdvertisement) o);
            BackgroundThread thread = new BackgroundThread(adService);
            thread.start();
            Intent intent = new Intent(this.getApplicationContext(), MapActivity.class);
            AndroidAdvertisement androidAD = new AndroidAdvertisement((IAdvertisement) o);
            intent.putExtra("Advertisement", androidAD);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.getApplicationContext().startActivity(intent);
            Toast.makeText(this, "Anons skapad!", Toast.LENGTH_LONG).show();
        } else if (evt == EventBus.Event.CREATE_AD_SETUP) {
            callCreateAd();
        } else if (evt == EventBus.Event.UPDATE_PROFILE) {
            profileService.updateProfile((IProfile) o);
            adService.updateAdvertiser((IProfile) o);
            loginUser((IProfile) o);

        }
    }

    private void loginUser(IProfile profile) {

        user.logIn(profile);
        mainView.repaintLogInView(true,UserModel.getInstance().getProfile());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode==5){
            UserModel usermodel = UserModel.getInstance();
            mainView.repaintLogInView(usermodel.isLoggedIn,usermodel.getProfile());
        }
    }

}

