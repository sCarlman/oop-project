package edu.ctl.pinjobs.Handler;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import edu.ctl.pinjobs.eventbus.EventBus;
import edu.ctl.pinjobs.Advertisement.IAdvertisement;

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
        System.out.println(adList);
    }

    @Override
    public void removeFromList(IAdvertisement add) {
        this.adList.remove(add);
    }

    public void sortForDistance(LatLng currentPosition){
        List<IAdvertisement> tempAdList = new ArrayList<IAdvertisement>(adList);
        adList.clear();
        int index=-1;
        IAdvertisement closestAd=null;
        double closestDistance = 10000000;
        int loopSize = tempAdList.size();
        for(int j=0;j<loopSize;j++) {
            for (int i = 0; i < tempAdList.size(); i++) {
                double adDistance = locationUtils.calculateDistanceFromPosition(currentPosition.latitude,tempAdList.get(i).getLatitude()
                        ,currentPosition.longitude,tempAdList.get(i).getLongitude());
                if (i == 0) {
                    closestDistance = adDistance;
                    closestAd = tempAdList.get(i);
                } else {
                    if (closestDistance > adDistance) {
                        closestDistance = adDistance;
                        closestAd = tempAdList.get(i);
                    }
                }
            }
            adList.add(closestAd);
            tempAdList.remove(closestAd);
        }
    }
}
