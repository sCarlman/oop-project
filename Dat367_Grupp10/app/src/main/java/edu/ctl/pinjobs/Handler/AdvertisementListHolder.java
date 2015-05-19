package edu.ctl.pinjobs.Handler;

import java.util.ArrayList;
import java.util.List;

import edu.ctl.pinjobs.Advertisement.IAdvertisement;
import edu.ctl.pinjobs.eventbus.EventBus;

/**
 * Created by Filips on 5/15/2015.
 */
public class AdvertisementListHolder {
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
                EventBus.INSTANCE.publish(EventBus.Event.ADLIST_NOT_EMPTY, null);
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
}
