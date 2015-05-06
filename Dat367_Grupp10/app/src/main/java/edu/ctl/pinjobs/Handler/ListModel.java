package edu.ctl.pinjobs.Handler;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

import edu.ctl.pinjobs.Services.EventBus;
import edu.ctl.pinjobs.model.Advertisement;
import edu.ctl.pinjobs.model.IAdvertisement;

/**
 * Created by Filips on 4/28/2015.
 */
public class ListModel implements IListModel {

    private List<IAdvertisement> adList = new ArrayList<>();

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
        }else {
            adList.addAll(addList);
            EventBus.INSTANCE.publish(EventBus.Event.ADLIST_UPDATED, adList);
        }
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
                double adDistance = Location.calculateDistanceFromCurrentPosition(tempAdList.get(i), context);
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
