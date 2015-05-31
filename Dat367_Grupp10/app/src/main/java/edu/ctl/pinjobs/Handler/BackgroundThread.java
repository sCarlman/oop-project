package edu.ctl.pinjobs.handler;

import com.parse.ParseException;

import java.util.List;

import edu.ctl.pinjobs.advertisement.model.IAdvertisement;
import edu.ctl.pinjobs.handler.model.AdvertisementListHolder;
import edu.ctl.pinjobs.advertisement.service.IAdvertisementService;

/**
 * Created by Filips on 5/15/2015.
 */
public class BackgroundThread extends Thread {

    private IAdvertisementService adService;
    private IActivity activity;

    public BackgroundThread(IAdvertisementService adService, IActivity activity){
        this.adService = adService;
        this.activity = activity;
    }

    @Override
    public void run() {
        try {
            //places the Ad list from the databse in the system
            List<IAdvertisement> adList = adService.fetchAllAds();
            AdvertisementListHolder.getInstance().setList(adList);
        }catch(ParseException e){
            activity.showConnectionErrorMsg();
        }
        //adService.removeOutDatedAds();
    }
}
