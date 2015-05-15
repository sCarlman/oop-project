package edu.ctl.pinjobs.Handler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.ctl.pinjobs.Advertisement.Advertisement;
import edu.ctl.pinjobs.Advertisement.AndroidAdvertisement;
import edu.ctl.pinjobs.Advertisement.DetailedAdActivity;
import edu.ctl.pinjobs.Advertisement.IAdvertisement;
import edu.ctl.pinjobs.Utils.LocationUtils;
import edu.ctl.pinjobs.controller.MainActivity;
import edu.ctl.pinjobs.controller.UserModel;
import edu.ctl.pinjobs.profile.IProfile;

/**
 * Created by Filips on 4/28/2015.
 */
public class ListView{

    private android.widget.ListView listView;
    private Context context;
    private String email;
    HandlerLocationUtils locationUtils = new HandlerLocationUtils();



    public ListView(Context context,android.widget.ListView listView, String email){

        this.email = email;
        this.listView = listView;
        this.context = context;
    }

    public void setupView(final List<IAdvertisement> adList, int id){
        List<String> titleList = new ArrayList<String>();
        final LatLng currentPosition = LocationUtils.getCurrentLocation(context);
        for(IAdvertisement ad : adList){
            titleList.add(setMessage(ad,currentPosition));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                id, titleList);
        listView.setAdapter(adapter);
        listView.invalidate();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                IAdvertisement ad = (Advertisement) adList.get(position);
                String adDistance="";
                    adDistance = "" + locationUtils.calculateDistanceFromPosition(currentPosition.latitude,
                            ad.getLatitude(),currentPosition.longitude,ad.getLongitude());
                AndroidAdvertisement androidAD = new AndroidAdvertisement(ad);


                if(email==null){
                    HandlerActivity hej = new HandlerActivity();
                    hej.openDetailedAdView(context,androidAD,adDistance);
                }else if(email.equals(ad.getAdvertiser().getEmail())){
                    UserListActivity usListAct = new UserListActivity();
                    usListAct.openModifyAdView(context, androidAD, adDistance);
                }else{
                    HandlerActivity hej = new HandlerActivity();
                    hej.openDetailedAdView(context,androidAD,adDistance);
                }

            }
        });
    }
    private String setMessage(IAdvertisement ad,LatLng currentPos){
        String message =ad.getTitle();
        String distance = "" + locationUtils.calculateDistanceFromPosition(currentPos.latitude,ad.getLatitude(),currentPos.longitude,ad.getLongitude());
        int index = distance.indexOf('.');
        distance = distance.substring(0, index + 2);
        return message + "      " + distance + " km bort";
    }

}
