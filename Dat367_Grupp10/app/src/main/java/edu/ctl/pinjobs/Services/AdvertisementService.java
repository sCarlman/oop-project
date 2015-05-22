package edu.ctl.pinjobs.services;

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
        parseAd.put("Day", ad.getDay());
        parseAd.put("Month", ad.getMonth());
        parseAd.put("Year", ad.getYear());
        parseAd.put("Lat", ad.getLatitude());
        parseAd.put("Lng", ad.getLongitude());
    }

    @Override
    public List<IAdvertisement> fetchAllAds() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Advertisement");
        try {
            List<IAdvertisement> adList = copyToAdvertisements(query.find());
            return adList;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<IAdvertisement> copyToAdvertisements(List<ParseObject> parseAds) {
        List<IAdvertisement> fetchedAds = new ArrayList<IAdvertisement>();
        ProfileService pService = new ProfileService();
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

    public void removeOutDatedAds(){
        Calendar today = new GregorianCalendar();
        int thisYear = today.get(Calendar.YEAR);
        int thisMonth = today.get(Calendar.MONTH);
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

    public void deleteParseAd(ParseObject parseAd) {
        parseAd.deleteInBackground();
    }

}
