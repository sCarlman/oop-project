package com.example.filips.dat367_grupp10;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class MainActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Advertisement.class);
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
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void openCreateProfileView(View view) {
        Intent intent = new Intent(getApplicationContext(),CreateProfileActivity.class);
        startActivity(intent);
    }

    public void openCreateAdView(View view) {
        Intent intent = new Intent(getApplicationContext(), CreateAdActivity.class);
        startActivity(intent);
    }

    public void openListView(View view) {
        Intent intent = new Intent(getApplicationContext(), AdListActivity.class);
        startActivity(intent);
    }

}

