package edu.ctl.pinjobs.handler.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.filips.dat367_grupp10.R;
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
    private AdapterView.OnItemClickListener clickListener;
    private SwipeRefreshLayout.OnRefreshListener onRefreshListener;
    private HandlerLocationUtils locationUtils;
    private SwipeRefreshLayout swipeRefreshList;

    public ListView(Activity activity,AdapterView.OnItemClickListener clickListener,
                    SwipeRefreshLayout.OnRefreshListener onRefreshListener){

        this.listView = (android.widget.ListView) activity.findViewById(R.id.adListView);
        this.swipeRefreshList = (SwipeRefreshLayout) activity.findViewById(R.id.swipeRefreshList);
        this.context = activity.getApplicationContext();
        this.clickListener = clickListener;
        this.onRefreshListener = onRefreshListener;
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
        swipeRefreshList.setOnRefreshListener(onRefreshListener);
        swipeRefreshList.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    private String setMessage(IAdvertisement ad,LatLng currentPos){
        String message =ad.getTitle();
        String distance = "" + locationUtils.calculateDistanceFromPosition(currentPos.latitude,ad.getLatitude(),currentPos.longitude,ad.getLongitude());
        int index = distance.indexOf('.');
        distance = distance.substring(0, index + 2);
        return message + "\n" + distance + "km bort";
    }

    public void setRefreshing(boolean b){
        swipeRefreshList.setRefreshing(b);
    }

}
