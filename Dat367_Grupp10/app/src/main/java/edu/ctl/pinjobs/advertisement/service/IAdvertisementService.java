package edu.ctl.pinjobs.advertisement.service;

import com.parse.ParseException;
import com.parse.ParseObject;

import java.io.Serializable;
import java.util.List;

import edu.ctl.pinjobs.profile.model.IProfile;
import edu.ctl.pinjobs.advertisement.model.IAdvertisement;

/**
 * Created by Isaac on 2015-05-05.
 */
public interface IAdvertisementService extends Serializable{

    public void saveAd(IAdvertisement ad);
    public void deleteParseAd(ParseObject parseAd);
    public void removeOutDatedAds();
    public void updateAd(String id, IAdvertisement ad);
    public void updateAdvertiser(IProfile profile);

    public List<IAdvertisement> fetchAllAds() throws ParseException;
    public List<IAdvertisement> fetchAdsOfAdvertiser(String email);
    public String getAdID(IAdvertisement ad);

}
