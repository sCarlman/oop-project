package edu.ctl.pinjobs.controller;

import android.view.View;
import android.widget.Button;

import edu.ctl.pinjobs.Services.EventBus;

/**
 * Created by Albertsson on 15-05-11.
 */
public class MainView {

    private Button mapButton;
    private Button listButton;
    private Button createNewAdButton;
    private Button loginButton;

    public MainView(Button map, Button list, Button newAd, Button login, Boolean loginSuccess){

        if(loginSuccess == true){
            newAd.setVisibility(View.VISIBLE);
        }else{
            newAd.setVisibility(View.GONE);
        }

        this.mapButton = map;
        this.listButton = list;
        this.createNewAdButton = newAd;
        this.listButton = login;

    }

}
