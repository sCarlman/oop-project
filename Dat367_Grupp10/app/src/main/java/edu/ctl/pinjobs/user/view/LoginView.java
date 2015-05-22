package edu.ctl.pinjobs.user.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.filips.dat367_grupp10.R;

import edu.ctl.pinjobs.user.model.LoginModel;

/**
 * Created by Albertsson on 15-05-05.
 */
public class LoginView{

    private EditText eMail;
    private EditText pwd;
    Context context;
    Activity activity;

    public LoginView(Context context, AttributeSet attrs) {
        this.context = context;
}

    public LoginView(Activity activity){

        this.activity = activity;
        this.eMail = (EditText)activity.findViewById(R.id.LoginEmaileditText);
        this.pwd = (EditText)activity.findViewById(R.id.LoginpasswordEditText);
        System.out.println(eMail);
    }

    /**
     * Attempts to sign in to the account.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public boolean attemptLoginSucces() {

        resetErrors();

        // Store values at the time of the login attempt.
        String email = getTextFromEmailField();
        String password = getTextFromPasswordField();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        //TODO: isEmptyfunkar inte
        if (TextUtils.isEmpty(password)) {
            setError(pwd,"Du måste fylla i ett lösenord");

            cancel = true;
            focusView = pwd;
        }

        // Check for a valid email address.
        //TODO: isEmpty fnkar inte
        if (TextUtils.isEmpty(email)) {
            setError(eMail,"Du måste fylla i en E-mail");

            cancel = true;
            focusView = eMail;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
            return false;
        } else {
            // perform the user login attempt.
            return true;
        }
    }

    public String getTextFromPasswordField(){
        return pwd.getText().toString();
    }
    public String getTextFromEmailField(){
        return eMail.getText().toString();
    }

    private void resetErrors(){
        eMail.setError(null);
        pwd.setError(null);
        eMail.setTextColor(Color.BLACK);
        pwd.setTextColor(Color.BLACK);
        eMail.setHintTextColor(Color.BLACK);
        pwd.setHintTextColor(Color.BLACK);
    }

    public void setError(EditText editText, String error){
        editText.setError(error);
        editText.setHintTextColor(Color.RED);
    }


    public void failedMatchEmailWithDatabase(){
        eMail.setError("Fel E-mail!!");
    }

    public void failedMatchPasswordWithDatabase(){
        pwd.setError("Fel Lösenord!");
    }

}


