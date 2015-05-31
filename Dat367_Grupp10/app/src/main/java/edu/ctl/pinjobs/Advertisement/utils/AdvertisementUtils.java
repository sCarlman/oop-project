package edu.ctl.pinjobs.advertisement.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by Filips on 5/15/2015.
 */
public class AdvertisementUtils {

    public LatLng getLocationFromAddress(Context context,String strAddress) throws IOException{

        Geocoder coder = new Geocoder(context);
        List<Address> address; //A list witch holds the adresses that match the adress input.
        LatLng position = null; //the coordinates in latitude and longitude

        try {
            address = coder.getFromLocationName(strAddress, 5); //saves the 5 best matches from the input
            if (address.size()==0) {
                return new LatLng(200,200);
            }
            Address location = address.get(0);
            position = new LatLng(location.getLatitude(), location.getLongitude());

        }catch(NullPointerException e){

        } catch (IllegalArgumentException e) { //the method throws both IOexception and illegalargumentexcpetion, Ioexception if there is no
            //internet connection and illegalargument if input is null

        }
        return position;
    }
}
