package edu.ctl.pinjobs.Services;

import java.util.List;

import edu.ctl.pinjobs.IProfile;
import edu.ctl.pinjobs.model.IAdvertisement;

/**
 * Created by Isaac on 2015-05-05.
 */
public interface IAdvertisementService {

    public void saveAd(IAdvertisement Ad);

    public List<IAdvertisement> fetchAllAds();
    public List<IAdvertisement> fetchAdsOfAdvertiser(IProfile advertiser);

}
