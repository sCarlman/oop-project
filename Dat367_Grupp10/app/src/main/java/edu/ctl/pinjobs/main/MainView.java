package edu.ctl.pinjobs.main;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.filips.dat367_grupp10.R;

import edu.ctl.pinjobs.profile.model.IProfile;

/**
 * Created by Albertsson on 15-05-11.
 */
public class MainView {

    private Button createNewAdButton;
    private Button loginButton;
    private Button logOfButton;
    private TextView loggedInTextView;

    public MainView(Activity activity){
        this.createNewAdButton = (Button) activity.findViewById(R.id.postAdButton);
        this.loginButton = (Button) activity.findViewById(R.id.loginButton);
        this.logOfButton = (Button) activity.findViewById(R.id.logOfButton);
        this.loggedInTextView = (TextView) activity.findViewById(R.id.loggedInTextView);
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
