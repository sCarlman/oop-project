package edu.ctl.pinjobs.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
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

    public void showAlertDialog(final Context context) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Nätverksproblem!");
                alertDialog.setMessage("Kan inte kontakta databasen. Se till att du har " +
                        "internetanslutning och försök igen");
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Aight",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.dismiss();
                            }
                        });
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.show();
            }
        });
    }

}
