package com.example.filips.dat367_grupp10;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends Activity implements OnMapReadyCallback {

    Location locationHelper = new Location();
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
        LatLng currentPosition = locationHelper.getCurrecntLocation(this);
        System.out.println(currentPosition);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 13));
        //addAllStartMarkers();

        Advertisement add = new Advertisement("slottner","Seholm","Fille","0302302230","ros",Category.LABOUR,"asdassd asdasdasd f asd eeffsa as");

        addMarker(add);
        addMarker(locationHelper.getLocationFromAddress(this,"Rosenvägen2a,Lerum"),"Här bor jag!");
    }

    private void addMarker(LatLng location,String title){

        map.addMarker(new MarkerOptions().position(location).title(title));

    }

    private void addMarker(Advertisement ad){

        map.addMarker(new MarkerOptions().position(ad.getPosition()).title(ad.getDescription()).icon(addCorrectCollorMarker(ad))
                .snippet(ad.getDescription()));

    }

    private void addAllStartMarkers(List<Advertisement> ads){

        for(Advertisement ad : ads){
            addMarker(ad);

        }
    }

    //Chooses different colors depending on the Job category
    private BitmapDescriptor addCorrectCollorMarker(Advertisement ad){
        Category category = ad.getCategory();
        switch (category){
            case GARDEN : return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
            case LABOUR : return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
            case OTHER: return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
            default: return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
        }
    }

}