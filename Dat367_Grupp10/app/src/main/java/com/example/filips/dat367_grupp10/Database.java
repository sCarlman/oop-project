package com.example.filips.dat367_grupp10;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Filips on 4/21/2015.
 */
public class Database  {
    private static Database instance = null;
    private static List<Advertisement> tempAdList;
    public Database() { }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    public List<Advertisement> getAdList(){
        ParseQuery<Advertisement> query = ParseQuery.getQuery("Advertisement");
        query.findInBackground(new FindCallback<Advertisement>() {
            public void done(List<Advertisement> objects, ParseException e) {
                if (e == null) {
                    tempAdList = objects;
                } else {
                    //FUDGE!
                }
            }
        });
        return tempAdList;
    }

    public void addAdToDatabase(Advertisement ad){
        Advertisement adReference = ParseObject.createWithoutData(Advertisement.class, ad.getObjectId());
        ad.saveInBackground();
        ad.setObjectId(adReference.getObjectId());
    }

    public void removeAddFromDatabase(Advertisement ad){


    }
}