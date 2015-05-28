package edu.ctl.pinjobs.handler.model;

import java.util.List;

import edu.ctl.pinjobs.advertisement.model.IAdvertisement;

/**
 * Created by Filips on 4/28/2015.
 */
public interface IListModel {

    public List<IAdvertisement> getList();
    public void addToList(IAdvertisement add);
    public void setList(List<IAdvertisement> addList);
    public void removeFromList(IAdvertisement add);
    public void sortForDistance(double currentLatitidue, double currentLongitude);

}
