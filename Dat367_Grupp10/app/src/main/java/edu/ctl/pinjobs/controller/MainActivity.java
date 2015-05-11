package edu.ctl.pinjobs.controller;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import edu.ctl.pinjobs.Handler.HandlerActivity;
import edu.ctl.pinjobs.Handler.MapActivity;
import edu.ctl.pinjobs.Services.AdvertisementService;
import edu.ctl.pinjobs.User.LoginActivity;
import edu.ctl.pinjobs.profile.CreateProfileActivity;
import edu.ctl.pinjobs.Advertisement.CreateAdActivity;
import edu.ctl.pinjobs.profile.Profile;

import com.example.filips.dat367_grupp10.R;
import com.parse.Parse;


public class MainActivity extends ActionBarActivity {

    private MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Gets boolean true if login success
        boolean login = getIntent().getBooleanExtra("LoginSuccess", false);

        MainView mainView = new MainView((Button)findViewById(R.id.mapButton), (Button)findViewById(R.id.listButton),(Button)findViewById(R.id.postAdButton),
                (Button)findViewById(R.id.loginButton), login);

        this.mainView = mainView;

        // Enable Local Datastore.
        // Parse.enableLocalDatastore(this);
        Parse.initialize(this, "W4QRsIPB5oFT6F6drmZi0BrxdPYPEYHY2GYSUU4q", "JpXn4VB0Y63wqNIf0qgvRGg7k3QmjfzJjD9qhzqE");

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
}
