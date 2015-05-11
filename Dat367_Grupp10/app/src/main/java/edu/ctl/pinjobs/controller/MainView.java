package edu.ctl.pinjobs.controller;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.ctl.pinjobs.Services.EventBus;

/**
 * Created by Albertsson on 15-05-11.
 */
public class MainView {

    private Button mapButton;
    private Button listButton;
    private Button createNewAdButton;
    private Button loginButton;
    private Button logOfButton;
    private Boolean loginSuccess;
    private TextView loggedInTextView;

    private MainActivity mainActivity = new MainActivity();

    public MainView(Button map, Button list, Button newAd, Button login, Button logOf, Boolean loginSuccess, TextView loggedInText){

        this.mapButton = map;
        this.listButton = list;
        this.createNewAdButton = newAd;
        this.loginButton = login;
        this.logOfButton = logOf;
        this.loginSuccess = loginSuccess;
        this.loggedInTextView = loggedInText;

        repaintViewOnLogin();

    }

    public void repaintForLogOf(){
        createNewAdButton.setVisibility(View.GONE);
        logOfButton.setVisibility(View.GONE);
        loginButton.setVisibility(View.VISIBLE);
        loggedInTextView.setVisibility(View.GONE);
    }

    private void repaintViewOnLogin() {

        if (loginSuccess == true) {
            mainActivity.setProfileNameForView();
            loggedInTextView.setText("Du är inloggad som: " + mainActivity.getProfileName());
            createNewAdButton.setVisibility(View.VISIBLE);
            logOfButton.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.GONE);
            loggedInTextView.setVisibility(View.VISIBLE);

        } else {
            repaintForLogOf();
        }

    }

}
