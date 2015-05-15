package edu.ctl.pinjobs.Services;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
        ProfileService pService = new ProfileService();
        for (ParseObject parseAd: parseAds) {
            fetchedAds.add(new Advertisement(pService.fetchProfile(parseAd.getString("Email")),
                    parseAd.getString("Title"),
                    parseAd.getString("Description"),
                    parseAd.getString("Location"),
                    Category.valueOf(parseAd.getString("Category")),
                    (int) parseAd.getNumber("Day"),
                    (int) parseAd.getNumber("Month"),
                    (int) parseAd.getNumber("Year")));
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

    public void removeOutDatedAds(Context context){
        Calendar today = new GregorianCalendar();
        int thisYear = today.get(Calendar.YEAR);
        int thisMonth = today.get(Calendar.MONTH);
        int thisDay = today.get(Calendar.DAY_OF_MONTH);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Advertisement");
        try {
            for(ParseObject parseAd: query.find()){

                int endDay = (int)parseAd.getNumber("Day");
                int endMonth = (int)parseAd.getNumber("Month");
                int endYear = (int)parseAd.getNumber("Year");

                if(endYear < thisYear){
                    deleteParseAd(parseAd);
                }else if((endYear == thisYear) && (endMonth < thisMonth)){
                    deleteParseAd(parseAd);
                }else if((endYear == thisYear) && (endMonth == thisMonth) && (endDay < thisDay)){
                    deleteParseAd(parseAd);
                }
            }
        } catch (ParseException e) {
            System.out.println("EXCEPTION DELETE ADS");
            AlertDialog alertDialog = new AlertDialog.Builder(context).create();

            alertDialog.setTitle("Info");
            alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
            alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
            alertDialog.setButton( DialogInterface.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            e.printStackTrace();
        }
    }

    public void deleteParseAd(ParseObject parseAd) {
        parseAd.deleteInBackground();
    }

}
