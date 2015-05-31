package edu.ctl.pinjobs.main.controller;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import edu.ctl.pinjobs.advertisement.model.AndroidAdvertisement;
import edu.ctl.pinjobs.advertisement.controller.CreateAdActivity;
import edu.ctl.pinjobs.handler.controller.ListActivity;
import edu.ctl.pinjobs.handler.controller.MapActivity;
import edu.ctl.pinjobs.handler.model.AdvertisementListHolder;
import edu.ctl.pinjobs.handler.BackgroundThread;
import edu.ctl.pinjobs.handler.ConnectionErrorActivity;
import edu.ctl.pinjobs.main.view.MainView;
import edu.ctl.pinjobs.profile.model.IUserModel;
import edu.ctl.pinjobs.profile.model.UserModel;
import edu.ctl.pinjobs.profile.controller.MyProfileActivity;
import edu.ctl.pinjobs.advertisement.service.AdvertisementService;
import edu.ctl.pinjobs.advertisement.service.IAdvertisementService;
import edu.ctl.pinjobs.profile.service.IProfileService;
import edu.ctl.pinjobs.profile.service.ProfileService;
import edu.ctl.pinjobs.user.controller.LoginActivity;
import edu.ctl.pinjobs.utils.LocationUtils;

import com.example.filips.dat367_grupp10.R;
import com.parse.Parse;


public class MainActivity extends ActionBarActivity implements ConnectionErrorActivity {

    private MainView mainView;
    private IProfileService profileService;
    private IAdvertisementService adService;
    private IUserModel user;
    private final String PARSE_APPLICATION_ID = "W4QRsIPB5oFT6F6drmZi0BrxdPYPEYHY2GYSUU4q";
    private final String PARSE_CLIENT_KEY = "JpXn4VB0Y63wqNIf0qgvRGg7k3QmjfzJjD9qhzqE";
    private final int LOGIN_SUCCESS = 5;
    private final int AD_POSTED = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializes this app with our project in Parse database.
        //This needs to be done before accessing it.
        Parse.initialize(MainActivity.this, PARSE_APPLICATION_ID, PARSE_CLIENT_KEY);

        //updates LocationUtils by GPS every minute
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,60000,100, new LocationUtils());

        mainView = new MainView(MainActivity.this);
        user = UserModel.getInstance();
        profileService = new ProfileService();
        adService = new AdvertisementService(profileService);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        //hides profiles if not logged in. Shows if logged in.
        MenuItem item = menu.findItem(R.id.action_person);
        if(user.getIsLoggedIn()){
            item.setVisible(true);
        }else{
            item.setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        switch (item.getItemId()) {
            case R.id.action_person:
                if(user.getIsLoggedIn()) {
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

    //onClick for mapButton, defined in activity_main.xml
    public void openMapView(View view){
        //on click on "Karta" button from mainView that opens map view
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    //opens MyProfileActivity and puts some necesarry extras
    public void openMyProfileView(){
        Intent intent = new Intent(this, MyProfileActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("IOPENLISTVIEW", new ListActivity());
        bundle.putSerializable("OPEN_MAP_VIEW", new MapActivity());
        bundle.putSerializable("sendProfile", user.getProfile());
        bundle.putSerializable("PROFILE_SERVICE",profileService);
        bundle.putSerializable("AD_SERVICE",adService);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //onClick for PostAdButton
    //opens CreateAdActivity and puts some necesarry extras
    public void openCreateAdView(View view) {
        Intent intent = new Intent(getApplicationContext(), CreateAdActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArray("AD_LIST", AdvertisementListHolder.getInstance().getAndroidAdList());
        bundle.putSerializable("AD_SERVICE", adService);
        bundle.putSerializable("USER_PROFILE", user.getProfile());
        intent.putExtras(bundle);
        startActivityForResult(intent, 1);
    }

    //onClick for listButton
    //opens ListActivity and puts some necesarry extras
    public void openListView(View view) {
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("AD_SERVICE", adService);
        if (!user.getIsLoggedIn()){
            String email = null;
            intent.putExtra("Email", email);
            intent.putExtras(bundle);
            startActivity(intent);
        }else{
            String email = user.getProfile().getEmail();
            intent.putExtra("Email", email);
            bundle.putSerializable("OPEN_MAP_VIEW", new MapActivity());
            bundle.putSerializable("PROFILE_SERVICE", profileService);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }

    //onClick for loginButton
    //opens loginView and puts necesarry extra
    public void openLoginView(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.putExtra("PROFILE_SERVICE", profileService);
        startActivityForResult(intent, 1);
    }

    //onClick for logOfButton
    public void logOfUser(View view){
        user.logOff();
        mainView.repaintLogInView(false, null);
        invalidateOptionsMenu();
    }


    @Override
    public void onResume(){
        super.onResume();
        BackgroundThread thread = new BackgroundThread(adService, MainActivity.this);
        thread.start();

        mainView.repaintLogInView(user.getIsLoggedIn(), user.getProfile());
        if(user.getIsLoggedIn()) {
            System.out.println(user.getProfile().getFirstName());
        }
        invalidateOptionsMenu();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == LOGIN_SUCCESS){
            //when an activity has been finished and the next navigation step is
            // to login the user and repaint loginmodel
            UserModel usermodel = UserModel.getInstance();
            mainView.repaintLogInView(usermodel.getIsLoggedIn(),usermodel.getProfile());

        }else if(resultCode == AD_POSTED &&
                data.getExtras().getParcelable("Advertisement").getClass() ==
                        AndroidAdvertisement.class){
            //Happens after a new ad has been created and the activity is closed
            // which start a new intent for MapActivity
            Intent intent = new Intent(getApplicationContext(), MapActivity.class);
            intent.putExtras(data.getExtras());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    //calls when parseException is thrown from the thread.
    @Override
    public void showConnectionErrorMsg() {
        mainView.showAlertDialog(MainActivity.this);
    }
}

