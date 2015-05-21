package edu.ctl.pinjobs.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import edu.ctl.pinjobs.Advertisement.AndroidAdvertisement;
import edu.ctl.pinjobs.Advertisement.IAdvertisement;
import edu.ctl.pinjobs.Handler.AdvertisementListHolder;
import edu.ctl.pinjobs.Handler.HandlerActivity;
import edu.ctl.pinjobs.Handler.MapActivity;
import edu.ctl.pinjobs.Handler.UserListActivity;
import edu.ctl.pinjobs.Services.AdvertisementService;
import edu.ctl.pinjobs.Services.IAdvertisementService;
import edu.ctl.pinjobs.Services.IProfileService;
import edu.ctl.pinjobs.Services.ProfileService;
import edu.ctl.pinjobs.User.LoginActivity;
import edu.ctl.pinjobs.User.LoginModel;
import edu.ctl.pinjobs.Utils.LocationUtils;
import edu.ctl.pinjobs.eventbus.EventBus;
import edu.ctl.pinjobs.profile.CreateProfileActivity;
import edu.ctl.pinjobs.Advertisement.CreateAdActivity;
import edu.ctl.pinjobs.profile.IProfile;
import edu.ctl.pinjobs.profile.MyProfileActivity;

import com.example.filips.dat367_grupp10.R;
import com.parse.Parse;

import java.util.List;


public class MainActivity extends ActionBarActivity implements View.OnClickListener, EventBus.IEventHandler{

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
        EventBus.INSTANCE.addListener(new HandlerActivity());

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

    @Override
    public void onClick(View view) {

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
        startActivity(intent);
        System.out.println(user.getProfile().getAddress());
    }

    private void callCreateAd() {
        EventBus.INSTANCE.publish(EventBus.Event.CREATE_AD, user.getProfile());
    }

    public void openListView(View view) {
        Intent intent = new Intent(getApplicationContext(), HandlerActivity.class);
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
        startActivity(intent);
    }

    public void logOfUser(View view){
        user.logOff();
        mainView.repaintLogInView(false);
    }

    public String getProfileName() {
        return (user.getProfile().getFirstName() + " " + user.getProfile().getLastName());
    }

    @Override
    public void onResume(){
        super.onResume();
        BackgroundThread thread = new BackgroundThread(adService);
        thread.start();
    }

    @Override
    public void onEvent(EventBus.Event evt, Object o) {
        if(evt == EventBus.Event.POST_AD) {
            IAdvertisementService adService = new AdvertisementService();
            adService.saveAd((IAdvertisement)o);
            BackgroundThread thread = new BackgroundThread(adService);
            thread.start();
            Intent intent = new Intent(this.getApplicationContext(), MapActivity.class);
            AndroidAdvertisement androidAD = new AndroidAdvertisement((IAdvertisement) o);
            intent.putExtra("Advertisement", androidAD);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.getApplicationContext().startActivity(intent);
            Toast.makeText(this, "Anons skapad!", Toast.LENGTH_LONG).show();
        }else if(evt == EventBus.Event.SAVE_PROFILE) {
            profileService.saveProfile((IProfile) o);
            loginUser((IProfile) o);
        }else if(evt == EventBus.Event.LOGIN_SUCCESS){
            loginUser(((LoginModel) o).getProfile());
        }else if(evt == EventBus.Event.CREATE_AD_SETUP){
            callCreateAd();
        }else if(evt == EventBus.Event.SHOW_MY_ADS){
            Intent intent = new Intent(this.getApplicationContext(), UserListActivity.class);
            intent.putExtra("Email", (String) o);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else if(evt == EventBus.Event.UPDATE_PROFILE){
            profileService.updateProfile((IProfile) o);
            adService.updateAdvertiser((IProfile) o);
            loginUser((IProfile) o);
        }else if(evt == EventBus.Event.UPDATE_AD){
            //adService.updateAd((IAdvertisement) o);
            //TODO: fixa loading screen under omladdning av fetch ads
            BackgroundThread thread = new BackgroundThread(adService);
            thread.start();
            Intent intent = new Intent(this.getApplicationContext(), MapActivity.class);
            AndroidAdvertisement androidAD = new AndroidAdvertisement((IAdvertisement) o);
            intent.putExtra("UpdateAdvertisement", androidAD);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.getApplicationContext().startActivity(intent);
            Toast.makeText(this, "Annons ändrad!", Toast.LENGTH_LONG).show();
        }

    }

    private void loginUser(IProfile profile) {
        user.logIn(profile);
        mainView.repaintLogInView(true);
    }
}

