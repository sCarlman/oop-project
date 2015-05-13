package edu.ctl.pinjobs.Handler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

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
                System.out.println(locationUtils.calculateDistanceFromCurrentPosition(ad,context));
                String adDistance = ""+locationUtils.calculateDistanceFromCurrentPosition(ad,context);
                System.out.println(locationUtils.calculateDistanceFromCurrentPosition(ad,context));
                hej.openDetailedAdView(listView, androidAD, adDistance);


            }
        });
    }
    private String setMessage(IAdvertisement ad){
        String message =ad.getTitle();
        String distance = ""+locationUtils.calculateDistanceFromCurrentPosition(ad,context);

        int index = distance.indexOf('.');
        distance = distance.substring(0,index+2);

        return message +"      "+ distance +" km bort";
    }

}
