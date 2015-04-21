package com.example.filips.dat367_grupp10;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;


/**
 * Created by Filips on 4/1/2015.
 */
public class Location implements LocationListener {


    LatLng coordinates = null;

    public LatLng getLocationFromAddress(Context context,String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        //the coordinates in latitude and longitude
        LatLng position = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            position = new LatLng(location.getLatitude(), location.getLongitude());


        } catch (Exception e) {
            System.out.println("EXCEPTION");
        }
        return position;
    }

    public LatLng getCurrentLocation(Context context){

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

    //Calculates the distance from the users current location to the given coordinates using haversine formula
    public double calculateDistanceFromPosition(double lat1, double lat2, double long1, double long2){
        int radius = 6371; //earth radius
        double dlat = (lat1-lat2)*Math.PI/180; //delta lat
        double dlong = (long1-long2)*Math.PI/180; //delta long

        double calculation = Math.sin(dlat/2)*Math.sin(dlat/2) + //sin(delta latitude/2)^2
                Math.cos(lat1*Math.PI/180)*Math.cos(lat2*Math.PI/180)* //cos(latitude 1)*cos(latitude 2)
                Math.sin(dlong/2)*Math.sin(dlong/2);  //sin(delta longitude/2)^2

        double distance = radius * 2 * Math.asin(Math.sqrt(calculation)); //the distance given in kilometers
        return distance;
    }

    public double calculateDistanceFromCurrentPosition(Advertisement add, Context context){
         return calculateDistanceFromPosition(add.getPosition().latitude, getCurrentLocation(context).latitude,
            add.getPosition().longitude, getCurrentLocation(context).longitude);

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


    @Override
    public void onLocationChanged(android.location.Location location) {

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
