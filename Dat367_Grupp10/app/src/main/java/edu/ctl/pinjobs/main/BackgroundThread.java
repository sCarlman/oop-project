package edu.ctl.pinjobs.main;

import java.util.List;

import edu.ctl.pinjobs.advertisement.model.IAdvertisement;
import edu.ctl.pinjobs.services.IAdvertisementService;
import edu.ctl.pinjobs.eventbus.EventBus;

/**
 * Created by Filips on 5/15/2015.
 */
public class BackgroundThread extends Thread {
    IAdvertisementService adService;
    public BackgroundThread(IAdvertisementService adService){
        this.adService = adService;
    }

    @Override
    public void run(){
        List<IAdvertisement> adList = adService.fetchAllAds();
        EventBus.INSTANCE.publish(EventBus.Event.ADLIST_UPDATED,adList);
        adService.removeOutDatedAds();
    }
}
