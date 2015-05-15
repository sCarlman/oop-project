package edu.ctl.pinjobs.Handler;

import android.content.Context;

import java.util.List;

import edu.ctl.pinjobs.Advertisement.IAdvertisement;

/**
 * Created by Filips on 5/14/2015.
 */
public class ListModelWrapper implements IListModel{

    ListModel listmodel;

    public ListModelWrapper(List<IAdvertisement> adList,Context context){
        listmodel = new ListModel(adList,context);
    }

    @Override
    public List<IAdvertisement> getList() {
        return listmodel.getList();
    }

    @Override
    public void addToList(IAdvertisement add) {
        listmodel.addToList(add);
    }

    @Override
    public void setList(List<IAdvertisement> addList) {
        listmodel.setList(addList);
    }

    @Override
    public void removeFromList(IAdvertisement add) {
        listmodel.removeFromList(add);
    }

    @Override
    public void sortForDistance(Context context) {
        listmodel.sortForDistance(context);
    }
}
