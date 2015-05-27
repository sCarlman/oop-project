package edu.ctl.pinjobs.handler.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import edu.ctl.pinjobs.advertisement.model.AndroidAdvertisement;
import edu.ctl.pinjobs.advertisement.model.IAdvertisement;

/**
 * Created by Filips on 5/15/2015.
 */
public class AdvertisementListHolder extends Observable {
    private static AdvertisementListHolder ourInstance = new AdvertisementListHolder();
    List<IAdvertisement> adList = new ArrayList<>();

    public static AdvertisementListHolder getInstance() {
        return ourInstance;
    }

    private AdvertisementListHolder() {

    }
    public List<IAdvertisement> getList(){
        return adList;
    }
    public void setList(List<IAdvertisement> list){
        if(list!=null) {
            if(adList.size()==0) {
                adList.addAll(list);
                setChanged();
                notifyObservers();
            }else {
                adList.clear();
                adList.addAll(list);
            }
        }
    }
    public List<IAdvertisement> getAdvertiserAdsList(String email){
        List<IAdvertisement> advertiserAdList = new ArrayList<>();
        if(adList.size() != 0){

            for(IAdvertisement ad : adList){
                if(ad.getAdvertiser().getEmail().equals(email)){
                    advertiserAdList.add(ad);
                }
            }

        }
        return advertiserAdList;
    }

    public AndroidAdvertisement[] getAndroidAdList(){
        AndroidAdvertisement[] androidAd = new AndroidAdvertisement[getList().size()];
        int i = 0;
        for(IAdvertisement ad: getList()){
            androidAd[i] = new AndroidAdvertisement(ad);
            i++;
        }
        return androidAd;
    }
}
