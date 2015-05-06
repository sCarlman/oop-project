package edu.ctl.pinjobs.User;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.filips.dat367_grupp10.R;

import java.util.jar.Attributes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Albertsson on 15-05-05.
 */
public class LoginView extends LinearLayout {

    private Button loginButton;
    private EditText eMail;
    private EditText pwd;


    public LoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate(){
        loginButton = (android.widget.Button)findViewById(R.id.loginButton);
        eMail = (android.widget.EditText)findViewById(R.id.editText);
        pwd = (android.widget.EditText)findViewById(R.id.pwdEditText);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin() {

        // Reset errors.
        eMail.setError(null);
        pwd.setError(null);

        // Store values at the time of the login attempt.
        String email = eMail.getText().toString();
        String password = pwd.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password)) {
            pwd.setError("Du måste fylla i ett lösenord!!!");
            focusView = pwd;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            eMail.setError("Du måste fylla i en e-mail!!!");
            focusView = eMail;
            cancel = true;
        } else if (!isEmailCorrect(email)) {
            eMail.setError("Fel! Fel! Fel! LÖSENORDET ÄR EJ GILTLIGT!");
            focusView = eMail;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // perform the user login attempt.
        }
    }

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



}


