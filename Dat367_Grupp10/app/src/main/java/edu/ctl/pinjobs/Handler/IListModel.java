package edu.ctl.pinjobs.Handler;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import edu.ctl.pinjobs.Advertisement.Advertisement;
import edu.ctl.pinjobs.Advertisement.IAdvertisement;

/**
 * Created by Filips on 4/28/2015.
 */
public interface IListModel {

    public List<IAdvertisement> getList();
    public void addToList(IAdvertisement add);
    public void setList(List<IAdvertisement> addList);
    public void removeFromList(IAdvertisement add);
    public void sortForDistance(LatLng currentPosition);


}
