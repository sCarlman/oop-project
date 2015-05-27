package edu.ctl.pinjobs.advertisement.controller;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.filips.dat367_grupp10.R;

import edu.ctl.pinjobs.advertisement.model.AndroidAdvertisement;
import edu.ctl.pinjobs.advertisement.model.IAdvertisement;
import edu.ctl.pinjobs.advertisement.view.DetailedAdView;

public class DetailedAdActivity extends ActionBarActivity {

    private DetailedAdView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_ad);

        Bundle bundle = getIntent().getExtras();

        AndroidAdvertisement androidAd = bundle.getParcelable("Advertisement");
        IAdvertisement ad = androidAd.getAd();
        String distance = getIntent().getStringExtra("Distance");

        DetailedAdView view = new DetailedAdView(this, ad, distance);
        this.view = view;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detailed_ad, menu);
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
}
