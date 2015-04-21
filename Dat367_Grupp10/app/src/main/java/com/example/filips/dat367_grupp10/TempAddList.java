package com.example.filips.dat367_grupp10;

import java.util.List;

/**
 * Created by Filips on 4/21/2015.
 */
public class TempAddList implements DataBaseInterface {
    private static TempAddList instance = null;

    private TempAddList() {
    }

    public static synchronized TempAddList getInstance() {
        if (instance == null) {
            instance = new TempAddList();
        }

        return instance;
    }

    @Override
    public List<Advertisement> getAdList() {
        return null;
    }

    @Override
    public void addAdToDatabase(Advertisement add) {

    }

    @Override
    public void removeAddFromDatabase(Advertisement add) {

    }
}