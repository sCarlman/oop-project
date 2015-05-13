package edu.ctl.pinjobs.Handler;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.filips.dat367_grupp10.R;
import com.google.android.gms.maps.MapFragment;

import java.util.List;

import edu.ctl.pinjobs.Advertisement.AndroidAdvertisement;
import edu.ctl.pinjobs.Advertisement.IAdvertisement;
import edu.ctl.pinjobs.Services.AdvertisementService;
import edu.ctl.pinjobs.Services.IAdvertisementService;

public class MapActivity extends ActionBarActivity {

    MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);

        IAdvertisementService adService = new AdvertisementService();
        List<IAdvertisement> adList = adService.fetchAllAds();

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            System.out.println("KOMMER HIIIIT");
            AndroidAdvertisement androidAd = (AndroidAdvertisement) bundle.getParcelable("Advertisement");
            IAdvertisement ad = androidAd.getAd();

            mapView = new MapView(this.getApplicationContext(),adList,mapFragment,ad);
        }else {
            mapView = new MapView(this.getApplicationContext(), adList, mapFragment);
        }
    }

    public void setNewPin(IAdvertisement ad){
        mapView.addPinAndZoom(ad);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
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
