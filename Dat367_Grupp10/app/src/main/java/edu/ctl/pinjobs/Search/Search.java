package edu.ctl.pinjobs.Search;

import com.parse.ParseObject;
import com.parse.ParseException;

import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import edu.ctl.pinjobs.Handler.ListModel;
import edu.ctl.pinjobs.Services.AdvertisementService;
import edu.ctl.pinjobs.Advertisement.Advertisement;
import edu.ctl.pinjobs.Advertisement.Category;
import edu.ctl.pinjobs.Advertisement.IAdvertisement;
import edu.ctl.pinjobs.profile.Profile;

/**
 * Created by filiplarsson on 15-05-07.
 */

public class Search {
    List<IAdvertisement> searchedAds;
    public List<IAdvertisement> doSearch(String s, List<ParseObject> parseAds){

        searchedAds = new ArrayList<IAdvertisement>();
        for(ParseObject parseAd: parseAds){
            if (parseAd.getString("Title").equals(s)){
               // searchedAds.add(parseAd.);
                searchedAds.add(new Advertisement(new Profile(parseAd.getString("FirstName"),
                        parseAd.getString("LastName"),
                        "",
                        parseAd.getString("Email"),
                        parseAd.getString("Phone"),
                        parseAd.getString("PreferredLocation")),

                        parseAd.getString("Title"),
                        parseAd.getString("Description"),
                        parseAd.getString("Location"),
                        Category.valueOf(parseAd.getString("Category")),
                        parseAd.getInt("Day"),
                        parseAd.getInt("Month"),
                        parseAd.getInt("Year"),
                        parseAd.getDouble("Lat"),
                        parseAd.getDouble("Lng")));
            }
        }
        return searchedAds;
    }

}

