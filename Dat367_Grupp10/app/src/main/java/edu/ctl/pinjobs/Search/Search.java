package edu.ctl.pinjobs.Search;

import com.parse.ParseObject;
import com.parse.ParseException;

import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import edu.ctl.pinjobs.Handler.ListModel;
import edu.ctl.pinjobs.Services.AdvertisementService;
import edu.ctl.pinjobs.model.Advertisement;
import edu.ctl.pinjobs.model.Category;
import edu.ctl.pinjobs.model.IAdvertisement;
import edu.ctl.pinjobs.profile.Profile;

/**
 * Created by filiplarsson on 15-05-07.
 */
public class Search {

    public void doSearch(String s, List<ParseObject> parseAds){

        List<IAdvertisement> searchedAds = new ArrayList<IAdvertisement>();
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
                        Category.valueOf(parseAd.getString("Category"))));
            }
        }
    }

}
