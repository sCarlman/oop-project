package edu.ctl.pinjobs.Handler;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import edu.ctl.pinjobs.Advertisement.IAdvertisement;

/**
 * Created by Filips on 4/28/2015.
 */
public class ListView  {

    private android.widget.ListView listView;
    private Context context;
    HandlerLocationUtils locationUtils = new HandlerLocationUtils();

    public ListView(Context context,android.widget.ListView listView){
        this.listView = listView;
        this.context = context;
    }

    public void setupView(List<IAdvertisement> adList, int id){
        List<String> titleList = new ArrayList<String>();
        for(IAdvertisement ad : adList){
            titleList.add(setMessage(ad));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                id, titleList);
        listView.setAdapter(adapter);
        listView.invalidate();

    }
    private String setMessage(IAdvertisement ad){
        String message =ad.getTitle();
        String distance = ""+locationUtils.calculateDistanceFromCurrentPosition(ad,context);
        int index = distance.indexOf('.');
        distance = distance.substring(0,index+2);

        return message +"      "+ distance +" km bort";
    }

}
