package edu.ctl.pinjobs.Services;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import edu.ctl.pinjobs.IProfile;
import edu.ctl.pinjobs.model.Advertisement;
import edu.ctl.pinjobs.model.Category;
import edu.ctl.pinjobs.model.IAdvertisement;
import edu.ctl.pinjobs.profile.Profile;

/**
 * Created by Isaac on 2015-04-27.
 */
public class AdvertisementService implements IAdvertisementService {

    @Override
    public void saveAd(IAdvertisement Ad) {
        ParseObject parseAd = new ParseObject("Advertisement");
        setParseAdvertisement(Ad, parseAd);
        uploadToParse(parseAd);
    }

    private void uploadToParse(ParseObject parseAd) {
        parseAd.saveInBackground();
    }

    private void setParseAdvertisement(IAdvertisement ad, ParseObject parseAd) {
        parseAd.put("FirstName", ad.getAdvertiser().getFirstName());
        parseAd.put("LastName", ad.getAdvertiser().getLastName());
        parseAd.put("Email", ad.getAdvertiser().getEmail());
        parseAd.put("Phone", ad.getAdvertiser().getPhone());
        parseAd.put("Location", ad.getLocation());
        parseAd.put("Title", ad.getTitle());
        parseAd.put("Description", ad.getDescription());
        parseAd.put("Category", ad.getCategory().toString());
    }

    @Override
    public List<IAdvertisement> fetchAllAds() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Advertisement");
        try {
            return copyToAdvertisements(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
            //TODO: Handle error.
            return null;
        }
    }

    private List<IAdvertisement> copyToAdvertisements(List<ParseObject> parseAds) {
        List<IAdvertisement> fetchedAds = new ArrayList<IAdvertisement>();
        for (ParseObject parseAd: parseAds) {
            fetchedAds.add(new Advertisement(new Profile(parseAd.getString("FirstName"),
                    parseAd.getString("LastName"),
                    parseAd.getString("Email"),
                    parseAd.getString("Phone"),
                    parseAd.getString("PreferredLocation")),
                    parseAd.getString("Title"),
                    parseAd.getString("Description"),
                    Category.valueOf(parseAd.getString("Category"))));
        }
        return fetchedAds;
    }

    @Override
    public List<IAdvertisement> fetchAdsOfAdvertiser(IProfile advertiser) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Advertisement");
        query.whereEqualTo("Email", advertiser.getEmail());
        try {
            return copyToAdvertisements(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
            //TODO: Handle error.
            return null;
        }
    }

}
