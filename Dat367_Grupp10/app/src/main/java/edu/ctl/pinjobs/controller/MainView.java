package edu.ctl.pinjobs.controller;

import android.view.View;
import android.widget.Button;

import edu.ctl.pinjobs.Services.EventBus;

/**
 * Created by Albertsson on 15-05-11.
 */
public class MainView implements EventBus.IEventHandler {

    private Button mapButton;
    private Button listButton;
    private Button createNewAdButton;
    private Button loginButton;

    public MainView(Button map, Button list, Button newAd, Button login){

        this.mapButton = map;
        this.listButton = list;
        this.createNewAdButton = newAd;
        this.listButton = login;

        EventBus.INSTANCE.addListener(this);
    }

    @Override
    public void onEvent(EventBus.Event evt, Object o) {
        if(evt == EventBus.Event.LOGIN_SUCCESS_REDRAW_MAIN){
            createNewAdButton.setVisibility(View.VISIBLE);
            createNewAdButton.invalidate();
        }
    }
}
