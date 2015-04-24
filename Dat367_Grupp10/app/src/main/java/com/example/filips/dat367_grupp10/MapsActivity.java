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
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends Activity implements OnMapReadyCallback {

    Location locationHelper = new Location();
    GoogleMap map;
    List<MarkerOptions> markers = new ArrayList<MarkerOptions>();

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
    public void onMapReady(GoogleMap map) {
        Database database = Database.getInstance();
        this.map = map;
        LatLng currentPosition = locationHelper.getCurrentLocation(this);

        map.setMyLocationEnabled(true);


        System.out.println(database.getAdList());
        if(database.getAdList()!=null) {
            addAllStartMarkers();
        }

        addMarker(locationHelper.getLocationFromAddress(this,"Rosenvägen2a,Lerum"),"Här bor jag!");
        map.moveCamera(CameraUpdateFactory.newLatLngBounds(setMapBounds().build(),200)); //sets the location to your current location
    }

    private void addMarker(LatLng location,String title){

        map.addMarker(new MarkerOptions().position(location).title(title));

    }

    private void addMarker(Advertisement ad){

        MarkerOptions marker = new MarkerOptions().position(new LatLng(ad.getPosition().getLatitude(), ad.getPosition().getLongitude())).title(ad.getDescription()).icon(addCorrectCollorMarker(ad))
                .snippet("" + locationHelper.calculateDistanceFromCurrentPosition(ad, this));

        map.addMarker(marker);
        markers.add(marker);

    }

    private LatLngBounds.Builder setMapBounds(){
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        MarkerOptions closestMarker = null;
        double closestDistance = -1;
        LatLng currentposition = locationHelper.getCurrentLocation(this);

        for (MarkerOptions marker : markers){

            double markerDistance = locationHelper.calculateDistanceFromPosition(currentposition.latitude,marker.getPosition().latitude,
                    currentposition.longitude,marker.getPosition().longitude);
            if(closestMarker==null){ //Sets the first marker as the closest marker
                closestMarker = marker;
                closestDistance = markerDistance;
            }
            else if(closestDistance > markerDistance){ //chooses the closest marker
                closestDistance = markerDistance;
                closestMarker = marker;
            }
        }

        builder.include(currentposition);
        builder.include(closestMarker.getPosition());
        return builder;


    }

    private void addAllStartMarkers(){

        List<Advertisement> ads = Database.getInstance().getAdList();
        System.out.println(ads.size());

        for(Advertisement ad : ads){
            addMarker(ad);
            System.out.println(ad.getPosition());

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