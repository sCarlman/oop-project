package edu.ctl.pinjobs.handler.model;

import java.util.ArrayList;
import java.util.List;

import edu.ctl.pinjobs.advertisement.model.IAdvertisement;
import edu.ctl.pinjobs.handler.utils.HandlerLocationUtils;

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
    }

    @Override
    public void removeFromList(IAdvertisement add) {
        this.adList.remove(add);
    }

    public void sortForDistance(double currentLangitude, double currentLongitude){
        //sorts the list from currentLocation
        List<IAdvertisement> tempAdList = new ArrayList<IAdvertisement>(adList);
        adList.clear();
        int index=-1;
        IAdvertisement closestAd=null;
        double closestDistance = 10000000;
        int loopSize = tempAdList.size();
        for(int j=0;j<loopSize;j++) { //loops through every ad in adList
            for (int i = 0; i < tempAdList.size(); i++) { //loops through the remaining ads
                double adDistance = locationUtils.calculateDistanceFromPosition(currentLangitude,tempAdList.get(i).getLatitude()
                        ,currentLongitude,tempAdList.get(i).getLongitude());
                if (i == 0) { //if it is the first itteration of the loop
                    closestDistance = adDistance;
                    closestAd = tempAdList.get(i);
                } else { //checks if the ad in the current itteration is closer than the previously closest ads
                    if (closestDistance > adDistance) {
                        closestDistance = adDistance;
                        closestAd = tempAdList.get(i);
                    }
                }
            }
            adList.add(closestAd); //the final list that is sorted
            tempAdList.remove(closestAd);
        }
    }
}
