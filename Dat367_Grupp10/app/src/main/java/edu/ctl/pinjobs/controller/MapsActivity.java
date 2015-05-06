package edu.ctl.pinjobs.controller;

import android.app.Activity;
import android.os.Bundle;

import edu.ctl.pinjobs.Handler.MapModel;
import edu.ctl.pinjobs.Services.AdvertisementService;
import edu.ctl.pinjobs.Services.IAdListService;
import edu.ctl.pinjobs.Services.IAdvertisementService;

import com.example.filips.dat367_grupp10.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

public class MapsActivity extends Activity implements OnMapReadyCallback {

    @Override
    //Sets up the map, check onMapReady for "Constructor"
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap Gmap) {

        IAdvertisementService service = new AdvertisementService();
        MapModel map = new MapModel(Gmap,this);
        map.setUpMap(service.fetchAllAds());

    }
}