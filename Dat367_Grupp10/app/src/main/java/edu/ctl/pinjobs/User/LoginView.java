package edu.ctl.pinjobs.User;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Albertsson on 15-05-05.
 */
public class LoginView{

    private Button loginButton;
    private EditText eMail;
    private EditText pwd;
    LoginModel lm = new LoginModel();
    Context context;

    public LoginView(Context context, AttributeSet attrs) {
        this.context = context;
}

    public LoginView(EditText eMail, EditText password){
        
        this.eMail = eMail;
        this.pwd = password;
    }

    /**
     * Attempts to sign in to the account.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin() {

        // Reset errors.
        eMail.setError(null);
        pwd.setError(null);
        eMail.setTextColor(Color.BLACK);
        pwd.setTextColor(Color.BLACK);
        eMail.setHintTextColor(Color.BLACK);
        pwd.setHintTextColor(Color.BLACK);

        // Store values at the time of the login attempt.
        String email = eMail.getText().toString();
        String password = pwd.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            pwd.setError("Du måste fylla i ett lösenord!");
            pwd.setHintTextColor(Color.RED);

            cancel = true;
            focusView = pwd;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            eMail.setError("Du måste fylla i en e-mail!");
            eMail.setHintTextColor(Color.RED);

            cancel = true;
            focusView = eMail;

        } else if (!isEmailCorrect(email)) {
            eMail.setError("Fel struktur på e-mail!");
            eMail.setTextColor(Color.RED);

            cancel = true;
            focusView = eMail;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // perform the user login attempt.
            lm.seteMail(email);
            lm.setPassword(password);
            lm.matchLoginWithDatabase();
        }
    }

    //Makes check if e-maill is typed correct
    public static boolean isEmailCorrect(String email) {
        if (email != null || email != "") {
            Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
            Matcher m = p.matcher(email);
            boolean emailValid = m.matches();
            return emailValid;
        }else {
            return false;
        }
    }

    public void failedMatchEmailWithDatabase(){
        eMail.setError("Fel lösenord!");
    }

    public void failedMatchPasswordWithDatabase(){
        pwd.setError("Fel E-mail!");
    }

}


