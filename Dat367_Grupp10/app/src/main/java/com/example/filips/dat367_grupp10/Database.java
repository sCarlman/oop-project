package com.example.filips.dat367_grupp10;

import java.util.List;

/**
 * Created by Filips on 4/21/2015.
 */
public class Database {
    private static Database instance = null;
    private Database() { }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    public List<Advertisement> getAddList(){

        return null;
    }

    public void adAddToDataBase(Advertisement add){


    }

    public void removeAddFromDataBase(Advertisement add){

    }
}