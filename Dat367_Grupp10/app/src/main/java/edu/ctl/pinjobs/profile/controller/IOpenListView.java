package edu.ctl.pinjobs.profile.controller;

import android.content.Context;

import java.io.Serializable;

/**
 * Created by Filips on 5/26/2015.
 */
public interface IOpenListView extends Serializable {
    public void openListViewForEmail(Context context,String email);
}
