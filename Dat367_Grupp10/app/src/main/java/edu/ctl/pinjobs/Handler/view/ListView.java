package edu.ctl.pinjobs.handler.view;

import android.app.Activity;
import android.content.Context;
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
    private HandlerLocationUtils locationUtils;
    private SwipeRefreshLayout swipeRefreshList;

    public ListView(Activity activity,AdapterView.OnItemClickListener clickListener){

        this.listView = (android.widget.ListView) activity.findViewById(R.id.adListView);
        this.swipeRefreshList = (SwipeRefreshLayout) activity.findViewById(R.id.swipeRefreshList);
        this.context = activity.getApplicationContext();
        this.clickListener = clickListener;
        this.locationUtils = new HandlerLocationUtils();

    }

    public void setupView(final List<IAdvertisement> adList, final int id){
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
        //calls refreshList when swiped to refresh.
        swipeRefreshList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshList(context,id);
            }
        });

    }

    private void refreshList(Context context, int id) {
        //TODO: Fetch adlist from parse, pls!
        //Here's how to do: https://www.bignerdranch.com/blog/implementing-swipe-to-refresh/
    }

    private String setMessage(IAdvertisement ad,LatLng currentPos){
        String message =ad.getTitle();
        String distance = "" + locationUtils.calculateDistanceFromPosition(currentPos.latitude,ad.getLatitude(),currentPos.longitude,ad.getLongitude());
        int index = distance.indexOf('.');
        distance = distance.substring(0, index + 2);
        return message + "      " + distance + " km bort";
    }

}
