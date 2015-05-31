package edu.ctl.pinjobs.utils;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Filips on 5/11/2015.
 */
public class LocationUtils implements LocationListener{

    private static LatLng currentLocation; //keeps track of the current location of the device

    public static LatLng getCurrentLocation(Context context){

        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        //Checks if the user has allowed the use of gps for the app

        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivity(intent);
            //Starts an intent for the user to allow gps
        }
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria,false);
        android.location.Location location = locationManager.getLastKnownLocation(provider);
        LatLng lastKnownLocation;
        if(location !=null) {
            lastKnownLocation = new LatLng(location.getLatitude(), location.getLongitude());
        }else{
            //places a dummy location in case no location is ever recorded by the system
            lastKnownLocation = new LatLng(57.686843, 11.921514);
        }

        if(currentLocation==null) {
            return lastKnownLocation;
        }else{
            return currentLocation;
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        //method implemented by the interface, sets currentlocation to the updated location from the gps
        currentLocation = new LatLng(location.getLatitude(),location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
