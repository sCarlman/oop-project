package edu.ctl.pinjobs.Services;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import edu.ctl.pinjobs.Advertisement.WrongAdInputException;
import edu.ctl.pinjobs.profile.IProfile;
import edu.ctl.pinjobs.Advertisement.Advertisement;
import edu.ctl.pinjobs.Advertisement.Category;
import edu.ctl.pinjobs.Advertisement.IAdvertisement;

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
            //TODO: Handle error.
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
            //TODO: Handle error.
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
            //DO SOMETHING
        }
    }

    @Override
    public void updateAd(final IAdvertisement ad) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Advertisement");
        query.whereEqualTo("Email", ad.getAdvertiser().getEmail());
        query.whereEqualTo("Title", ad.getTitle());
        query.whereEqualTo("Description", ad.getDescription());
        query.whereEqualTo("Location", ad.getLocation());
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseAd, ParseException e) {
                if (e == null) {
                    setParseAdvertisement(ad, parseAd);
                    uploadToParse(parseAd);
                } else {
                    //TODO: error walla!
                }
            }
        });
    }

    public void updateAdvertiser(final IProfile profile) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Advertisement");
        query.whereEqualTo("Email", profile.getEmail());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if(e == null) {
                    for (ParseObject parseAd : parseObjects) {
                        parseAd.put("FirstName", profile.getFirstName());
                        parseAd.put("LastName", profile.getLastName());
                        parseAd.put("Email", profile.getEmail());
                        parseAd.put("Phone", profile.getPhone());
                        uploadToParse(parseAd);
                    }
                }else {
                    //TODO: error walla!
                }
            }
        });
    }

    public void deleteParseAd(ParseObject parseAd) {
        parseAd.deleteInBackground();
    }

}
