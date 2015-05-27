package edu.ctl.pinjobs.handler.view;

import android.content.Context;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import edu.ctl.pinjobs.advertisement.model.IAdvertisement;
import edu.ctl.pinjobs.handler.utils.HandlerLocationUtils;

import edu.ctl.pinjobs.utils.LocationUtils;

/**
 * Created by Filips on 4/28/2015.
 */
public class ListView{

    private android.widget.ListView listView;
    private Context context;
    AdapterView.OnItemClickListener clickListener;
    HandlerLocationUtils locationUtils;



    public ListView(Context context,android.widget.ListView listView,AdapterView.OnItemClickListener clickListener){

        this.listView = listView;
        this.context = context;
        this.clickListener = clickListener;
        this.locationUtils = new HandlerLocationUtils();
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
        listView.setOnItemClickListener(clickListener);

    }
    private String setMessage(IAdvertisement ad,LatLng currentPos){
        String message =ad.getTitle();
        String distance = "" + locationUtils.calculateDistanceFromPosition(currentPos.latitude,ad.getLatitude(),currentPos.longitude,ad.getLongitude());
        int index = distance.indexOf('.');
        distance = distance.substring(0, index + 2);
        return message + "      " + distance + " km bort";
    }

}
