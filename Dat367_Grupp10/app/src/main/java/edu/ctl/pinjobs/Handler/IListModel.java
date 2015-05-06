package edu.ctl.pinjobs.Handler;

import android.content.Context;

import java.util.List;

import edu.ctl.pinjobs.model.Advertisement;
import edu.ctl.pinjobs.model.IAdvertisement;

/**
 * Created by Filips on 4/28/2015.
 */
public interface IListModel {

    public List<IAdvertisement> getList();
    public void addToList(IAdvertisement add);
    public void setList(List<IAdvertisement> addList);
    public void removeFromList(IAdvertisement add);
    public void sortForDistance(Context context);


}
