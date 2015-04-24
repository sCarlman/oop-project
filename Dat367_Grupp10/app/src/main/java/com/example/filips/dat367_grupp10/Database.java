package com.example.filips.dat367_grupp10;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filips on 4/21/2015.
 */
public class Database  {
    private static Database instance = null;
    boolean doneCalc = false;
    private List<Advertisement> tempAdList = new ArrayList<Advertisement>();

    public Database() { }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    public synchronized void fetchAdList(){

        ParseQuery<Advertisement> query = ParseQuery.getQuery("Advertisement");
        try {
            System.out.println(query.find().get(0).getPosition().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public List<Advertisement> getAdList(){
        ParseQuery<Advertisement> query = ParseQuery.getQuery("Advertisement");
        try {
            return query.find();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addAdToDatabase(Advertisement ad){
        Advertisement adReference = ParseObject.createWithoutData(Advertisement.class, ad.getObjectId());
        ad.saveInBackground();
        ad.setObjectId(adReference.getObjectId());
    }

    public void removeAddFromDatabase(Advertisement ad){


    }
}