package edu.ctl.pinjobs.handler.view;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import edu.ctl.pinjobs.advertisement.model.Advertisement;
import edu.ctl.pinjobs.advertisement.model.AndroidAdvertisement;
import edu.ctl.pinjobs.advertisement.model.IAdvertisement;
import edu.ctl.pinjobs.controller.HandlerActivity;
import edu.ctl.pinjobs.handler.utils.HandlerLocationUtils;
import edu.ctl.pinjobs.controller.UserListActivity;
import edu.ctl.pinjobs.utils.LocationUtils;

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
                    usListAct.openModifyAdView(context, androidAD);

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
