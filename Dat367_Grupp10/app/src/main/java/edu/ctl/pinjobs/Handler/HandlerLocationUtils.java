package edu.ctl.pinjobs.Handler;

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

import edu.ctl.pinjobs.Advertisement.Advertisement;
import edu.ctl.pinjobs.Advertisement.IAdvertisement;
import edu.ctl.pinjobs.Utils.LocationUtils;


/**
 * Created by Filips on 4/1/2015.
 */
public class HandlerLocationUtils {

    public LatLng getLocationFromAddress(Context context,String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address; //A list witch holds the adresses that match the adress input.
        LatLng position = null; //the coordinates in latitude and longitude

        try {
            address = coder.getFromLocationName(strAddress, 5); //saves the 5 best matches from the input
            if (address == null) {
                throw new AdressNotFoundException();
            }
            Address location = address.get(0);
            position = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (Exception e) { //the method throws both IOexception and illegalargumentexcpetion, Ioexception if there is no
            //internet connection and illegalargument if input is null
            System.out.println("EXCEPTION");
        }
        return position;
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

    public double calculateDistanceFromCurrentPosition(IAdvertisement add, Context context){
        LatLng addLocation = getLocationFromAddress(context,add.getLocation());
        return calculateDistanceFromPosition(addLocation.latitude, LocationUtils.getCurrentLocation(context).latitude,
                addLocation.longitude, LocationUtils.getCurrentLocation(context).longitude);
    }



}
