package edu.ctl.pinjobs.Handler;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import edu.ctl.pinjobs.Services.EventBus;
import edu.ctl.pinjobs.Advertisement.IAdvertisement;
import edu.ctl.pinjobs.Utils.LocationUtils;

/**
 * Created by Filips on 4/28/2015.
 */
public class ListModel implements IListModel {

    private List<IAdvertisement> adList = new ArrayList<>();
    HandlerLocationUtils locationUtils = new HandlerLocationUtils();

    public ListModel(List<IAdvertisement> adList) {
        setList(adList);
    }

    @Override
    public List<IAdvertisement> getList() {
        return adList;
    }

    @Override
    public void addToList(IAdvertisement add) {
        this.adList.add(add);
    }

    @Override
    public void setList(List<IAdvertisement> addList) {
        if(adList!=null) {
            this.adList.clear();
        }
        adList.addAll(addList);
        EventBus.INSTANCE.publish(EventBus.Event.ADLIST_UPDATED, adList);
    }

    @Override
    public void removeFromList(IAdvertisement add) {
        this.adList.remove(add);
    }

    public void sortForDistance(Context context){
        List<IAdvertisement> tempAdList = new ArrayList<IAdvertisement>(adList);
        adList.clear();
        int index=-1;
        IAdvertisement closestAd=null;
        double closestDistance = 10000000;
        for(int j=0;tempAdList.size()<j;j++) {

            for (int i = 0; i < tempAdList.size(); i++) {
                double adDistance = locationUtils.calculateDistanceFromCurrentPosition(tempAdList.get(i), context);
                if (i == 0) {
                    closestDistance = adDistance;
                    closestAd = tempAdList.get(i);
                    index = i;
                } else {
                    if (closestDistance > adDistance) {
                        closestDistance = adDistance;
                        closestAd = tempAdList.get(i);
                        index = i;
                    }
                }
            }
            adList.add(closestAd);
        }
    }
}
