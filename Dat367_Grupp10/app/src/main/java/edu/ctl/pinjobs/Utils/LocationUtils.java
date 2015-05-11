package edu.ctl.pinjobs.Utils;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import edu.ctl.pinjobs.Advertisement.IAdvertisement;

/**
 * Created by Filips on 5/11/2015.
 */
public class LocationUtils{


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
        LatLng currentLocation = new LatLng(location.getLatitude(),location.getLongitude());

        return currentLocation;
    }

}
