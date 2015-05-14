package edu.ctl.pinjobs.Handler;

import android.content.Context;

import java.util.List;

import edu.ctl.pinjobs.Advertisement.IAdvertisement;

/**
 * Created by Filips on 5/14/2015.
 */
public class ListModelWrapper implements IListModel{

    public ListModelWrapper(List<IAdvertisement> adList,Context context){

    }

    @Override
    public List<IAdvertisement> getList() {
        return null;
    }

    @Override
    public void addToList(IAdvertisement add) {

    }

    @Override
    public void setList(List<IAdvertisement> addList) {

    }

    @Override
    public void removeFromList(IAdvertisement add) {

    }

    @Override
    public void sortForDistance(Context context) {

    }
}
