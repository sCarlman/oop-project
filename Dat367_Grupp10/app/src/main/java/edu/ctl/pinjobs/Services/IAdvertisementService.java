package edu.ctl.pinjobs.Services;

import com.parse.ParseObject;

import java.util.List;

import edu.ctl.pinjobs.profile.IProfile;
import edu.ctl.pinjobs.Advertisement.IAdvertisement;

/**
 * Created by Isaac on 2015-05-05.
 */
public interface IAdvertisementService {

    public void saveAd(IAdvertisement Ad);
    public void deleteParseAd(ParseObject parseAd);
    public void removeOutDatedAds();

    public List<IAdvertisement> fetchAllAds();
    public List<IAdvertisement> fetchAdsOfAdvertiser(String email);

}
