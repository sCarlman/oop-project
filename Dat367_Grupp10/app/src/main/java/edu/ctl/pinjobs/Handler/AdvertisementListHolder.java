package edu.ctl.pinjobs.Handler;

import java.util.ArrayList;
import java.util.List;

import edu.ctl.pinjobs.Advertisement.IAdvertisement;
import edu.ctl.pinjobs.eventbus.EventBus;

/**
 * Created by Filips on 5/15/2015.
 */
public class AdvertisementListHolder implements EventBus.IEventHandler {
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
            if(adList.size()==0)
            adList.addAll(list);
        }else{
            adList.clear();
            adList.addAll(list);
        }
    }

    @Override
    public void onEvent(EventBus.Event evt, Object o) {
        if(evt== EventBus.Event.ADLIST_UPDATED){
            setList((List<IAdvertisement>)o);
        }
    }
}
