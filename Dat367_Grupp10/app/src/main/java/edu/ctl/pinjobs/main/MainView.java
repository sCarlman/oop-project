package edu.ctl.pinjobs.main;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import edu.ctl.pinjobs.controller.MainActivity;
import edu.ctl.pinjobs.profile.model.IProfile;

/**
 * Created by Albertsson on 15-05-11.
 */
public class MainView {

    private Button mapButton;
    private Button listButton;
    private Button createNewAdButton;
    private Button loginButton;
    private Button logOfButton;
    private TextView loggedInTextView;

    private MainActivity mainActivity = new MainActivity();

    public MainView(Button map, Button list, Button newAd, Button login, Button logOf,
                    TextView loggedInText){

        this.mapButton = map;
        this.listButton = list;
        this.createNewAdButton = newAd;
        this.loginButton = login;
        this.logOfButton = logOf;
        this.loggedInTextView = loggedInText;
    }

    public void repaintForLogOf(){
        createNewAdButton.setVisibility(View.GONE);
        logOfButton.setVisibility(View.GONE);
        loginButton.setVisibility(View.VISIBLE);
        loggedInTextView.setVisibility(View.GONE);
    }

    public void repaintLogInView(boolean loggedIn,IProfile loggedInProfile) {

        if (loggedIn) {
            loggedInTextView.setText("Du är inloggad som: " + loggedInProfile.getFirstName()
                    +" "+ loggedInProfile.getLastName());
            createNewAdButton.setVisibility(View.VISIBLE);
            logOfButton.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.GONE);
            loggedInTextView.setVisibility(View.VISIBLE);
        } else {
            repaintForLogOf();
        }
    }

}