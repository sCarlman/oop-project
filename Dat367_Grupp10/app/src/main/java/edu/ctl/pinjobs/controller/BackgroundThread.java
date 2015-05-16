package edu.ctl.pinjobs.controller;

import java.util.List;

import edu.ctl.pinjobs.Advertisement.IAdvertisement;
import edu.ctl.pinjobs.Services.AdvertisementService;
import edu.ctl.pinjobs.Services.IAdvertisementService;
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
    }
}
