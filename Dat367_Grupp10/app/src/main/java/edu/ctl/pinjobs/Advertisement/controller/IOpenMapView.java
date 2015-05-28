package edu.ctl.pinjobs.advertisement.controller;

import android.content.Context;

import java.io.Serializable;

import edu.ctl.pinjobs.advertisement.model.IAdvertisement;

/**
 * Created by Isaac on 2015-05-27.
 */
public interface IOpenMapView extends Serializable {
    public void startActivity(Context context, IAdvertisement newAd);
}
