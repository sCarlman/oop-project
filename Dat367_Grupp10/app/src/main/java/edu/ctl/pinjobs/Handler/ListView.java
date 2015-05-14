package edu.ctl.pinjobs.Handler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.ctl.pinjobs.Advertisement.Advertisement;
import edu.ctl.pinjobs.Advertisement.AndroidAdvertisement;
import edu.ctl.pinjobs.Advertisement.DetailedAdActivity;
import edu.ctl.pinjobs.Advertisement.IAdvertisement;
import edu.ctl.pinjobs.controller.MainActivity;

/**
 * Created by Filips on 4/28/2015.
 */
public class ListView{

    private android.widget.ListView listView;
    private Context context;
    HandlerLocationUtils locationUtils = new HandlerLocationUtils();



    public ListView(Context context,android.widget.ListView listView){


        this.listView = listView;
        this.context = context;
    }

    public void setupView(final List<IAdvertisement> adList, int id){
        List<String> titleList = new ArrayList<String>();
        for(IAdvertisement ad : adList){
            titleList.add(setMessage(ad));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                id, titleList);
        listView.setAdapter(adapter);
        listView.invalidate();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HandlerActivity hej = new HandlerActivity();
                IAdvertisement ad = (Advertisement) adList.get(position);
                AndroidAdvertisement androidAD = new AndroidAdvertisement(ad);
                try {
                    String adDistance = "" + locationUtils.calculateDistanceFromCurrentPosition(ad, context);
                    hej.openDetailedAdView(context,androidAD,adDistance);
                }catch(AdressNotFoundException e){
                    e.printStackTrace();
                }


            }
        });
    }
    private String setMessage(IAdvertisement ad){
        String message =ad.getTitle();
        String distance ="";
        try {
            distance = "" + locationUtils.calculateDistanceFromCurrentPosition(ad, context);
            int index = distance.indexOf('.');
            distance = distance.substring(0, index + 2);
        }catch(AdressNotFoundException e){
            e.printStackTrace();
        }
        return message + "      " + distance + " km bort";
    }

}
