package edu.ctl.pinjobs.Services;

import android.content.Context;

import com.parse.ParseObject;

import java.util.List;

import edu.ctl.pinjobs.profile.IProfile;
import edu.ctl.pinjobs.Advertisement.IAdvertisement;

/**
 * Created by Isaac on 2015-05-05.
 */
public interface IAdvertisementService {

    public void saveAd(IAdvertisement ad);
    public void deleteParseAd(ParseObject parseAd);
    public void removeOutDatedAds();
    public void updateAd(String id, IAdvertisement ad);
    public void updateAdvertiser(IProfile profile);

    public List<IAdvertisement> fetchAllAds();
    public List<IAdvertisement> fetchAdsOfAdvertiser(String email);

}
