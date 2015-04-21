package com.example.filips.dat367_grupp10;

import java.util.List;

/**
 * Created by Filips on 4/21/2015.
 */
public interface DataBaseInterface {
    public List<Advertisement> getAddList();

    public void adAddToDataBase(Advertisement add);

    public void removeAddFromDataBase(Advertisement add);
}