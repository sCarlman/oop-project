package edu.ctl.pinjobs.advertisement.service;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import edu.ctl.pinjobs.advertisement.model.WrongAdInputException;
import edu.ctl.pinjobs.profile.model.IProfile;
import edu.ctl.pinjobs.advertisement.model.Advertisement;
import edu.ctl.pinjobs.advertisement.model.Category;
import edu.ctl.pinjobs.advertisement.model.IAdvertisement;
import edu.ctl.pinjobs.profile.service.IProfileService;

/**
 * Created by Isaac on 2015-04-27.
 * This Service class handles all available usability of Parse database concerning Advertisements
 * in this application.
 */
public class AdvertisementService implements IAdvertisementService {
    private IProfileService pService;

    public AdvertisementService(IProfileService pService){
        this.pService = pService;
    }

    @Override
    public void saveAd(IAdvertisement Ad) {
        ParseObject parseAd = new ParseObject("Advertisement");
        setParseAdvertisement(Ad, parseAd);
        uploadToParse(parseAd);
    }

    //saves to Parse
    private void uploadToParse(ParseObject parseAd) {
        parseAd.saveInBackground();
    }

    //makes an Ad into a ParseObject to be saved to Parse
    private void setParseAdvertisement(IAdvertisement ad, ParseObject parseAd) {
        parseAd.put("FirstName", ad.getAdvertiser().getFirstName());
        parseAd.put("LastName", ad.getAdvertiser().getLastName());
        parseAd.put("Email", ad.getAdvertiser().getEmail());
        parseAd.put("Phone", ad.getAdvertiser().getPhone());
        parseAd.put("Location", ad.getLocation());
        parseAd.put("Title", ad.getTitle());
        parseAd.put("Description", ad.getDescription());
        parseAd.put("Category", ad.getCategory().toString());
        parseAd.put("Day", ad.getDay());
        parseAd.put("Month", ad.getMonth());
        parseAd.put("Year", ad.getYear());
        parseAd.put("Lat", ad.getLatitude());
        parseAd.put("Lng", ad.getLongitude());
    }

    @Override
    public List<IAdvertisement> fetchAllAds() throws ParseException{
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Advertisement");
        try {
            List<IAdvertisement> adList = copyToAdvertisements(query.find());
            return adList;
        } catch (ParseException e) {
            throw e;
        }
    }

    //when fetching ads they need to first be made into IAdvertisements before
    //they can be returned.
    private List<IAdvertisement> copyToAdvertisements(List<ParseObject> parseAds) {
        List<IAdvertisement> fetchedAds = new ArrayList<IAdvertisement>();
        for (ParseObject parseAd: parseAds) {
            try {
                fetchedAds.add(new Advertisement(pService.fetchProfile(parseAd.getString("Email")),
                        parseAd.getString("Title"),
                        parseAd.getString("Description"),
                        parseAd.getString("Location"),
                        Category.valueOf(parseAd.getString("Category")),
                        parseAd.getInt("Day"),
                        parseAd.getInt("Month"),
                        parseAd.getInt("Year"),
                        parseAd.getDouble("Lat"),
                        parseAd.getDouble("Lng")));
            }catch(WrongAdInputException e){
                deleteParseAd(parseAd);
            }
        }
        return fetchedAds;
    }

    @Override
    public List<IAdvertisement> fetchAdsOfAdvertiser(String email) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Advertisement");
        query.whereEqualTo("Email", email);
        try {
            return copyToAdvertisements(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    //removes ads that have run out of end date
    public void removeOutDatedAds(){
        Calendar today = new GregorianCalendar();
        int thisYear = today.get(Calendar.YEAR);
        //+1 because months are indexed from 0 to 11, for some reason
        int thisMonth = today.get(Calendar.MONTH) +1;
        int thisDay = today.get(Calendar.DAY_OF_MONTH);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Advertisement");
        try {
            for(ParseObject parseAd: query.find()){

                int endDay = parseAd.getInt("Day");
                int endMonth = parseAd.getInt("Month");
                int endYear = parseAd.getInt("Year");

                if(endYear < thisYear){
                    deleteParseAd(parseAd);
                }else if((endYear == thisYear) && (endMonth < thisMonth)){
                    deleteParseAd(parseAd);
                }else if((endYear == thisYear) && (endMonth == thisMonth) && (endDay < thisDay)){
                    deleteParseAd(parseAd);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //updates ad
    @Override
    public void updateAd(String id, final IAdvertisement ad) {
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Advertisement");
        query.getInBackground(id, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {
                    setParseAdvertisement(ad, parseObject);
                    uploadToParse(parseObject);
                }else{
                    e.printStackTrace();
                }
            }
        });
    }

    //gets ID from Ad, unlike Profiles Ads do not have any field that are unique.
    //But all ParseObjects in Parse have a unique ID.
    public String getAdID(IAdvertisement ad) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Advertisement");
        query.whereEqualTo("Email", ad.getAdvertiser().getEmail());
        query.whereEqualTo("Title", ad.getTitle());
        query.whereEqualTo("Description", ad.getDescription());
        query.whereEqualTo("Location", ad.getLocation());
        try {
            return query.getFirst().getObjectId();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    //updates the advertiser/Profile of an Ad, this has to be done when a Profile
    //which has published Ads is modified.
    public void updateAdvertiser(final IProfile profile) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Advertisement");
        query.whereEqualTo("Email", profile.getEmail());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (e == null) {
                    for (ParseObject parseAd : parseObjects) {
                        parseAd.put("FirstName", profile.getFirstName());
                        parseAd.put("LastName", profile.getLastName());
                        parseAd.put("Email", profile.getEmail());
                        parseAd.put("Phone", profile.getPhone());
                        uploadToParse(parseAd);
                    }
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    //deletes an ad from Parse
    public void deleteParseAd(ParseObject parseAd) {
        parseAd.deleteInBackground();
    }

}
