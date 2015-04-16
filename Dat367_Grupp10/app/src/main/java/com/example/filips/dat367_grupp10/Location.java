package com.example.filips.dat367_grupp10;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.LocationManager;
import android.provider.Settings;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;


/**
 * Created by Filips on 4/1/2015.
 */
public class Location {


    LatLng coordinates = null;

    public LatLng getLocationFromAddress(Context context,String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        //the coordinates in latitude and longitude
        LatLng coordinates = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            coordinates = new LatLng(location.getLatitude(), location.getLongitude());


        } catch (Exception e) {
            System.out.println("EXCEPTION");
        }
        return coordinates;
    }

    public LatLng getCurrecntLocation(Context context){

        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!enabled) {
            System.out.println("i if sats");
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivity(intent);
        }
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria,false);
        android.location.Location location = locationManager.getLastKnownLocation(provider);
        LatLng currentLocation = new LatLng(location.getLatitude(),location.getLongitude());
        coordinates = currentLocation;

        return currentLocation;
    }






    public LatLng getCoordinates() {
        return coordinates;
    }

    public double getLatitude(){
        return coordinates.latitude;
    }

    public double getLongitude(){
        return coordinates.longitude;
    }


}
