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
    public List<Advertisement> getAddList() {
        return null;
    }

    @Override
    public void adAddToDataBase(Advertisement add) {

    }

    @Override
    public void removeAddFromDataBase(Advertisement add) {

    }
}