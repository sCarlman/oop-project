package edu.ctl.pinjobs.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filips on 4/21/2015.
 */
public class TempAddList {
    private static TempAddList instance = null;
    List<Advertisement> adList = new ArrayList<Advertisement>();

    private TempAddList() {
    }

    public static synchronized TempAddList getInstance() {
        if (instance == null) {
            instance = new TempAddList();
        }

        return instance;
    }

    public List<Advertisement> getAddList() {
        return adList;
    }

    public void addAdToDatabase(Advertisement add) {

        adList.add(add);

    }

    public void removeAddFromDatabase(Advertisement add) {

        adList.remove(add);
    }
}