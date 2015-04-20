package com.example.filips.dat367_grupp10;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class CreateAdActivity extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ad);

        //Fixar defaults om man är inloggad.
        //Saknar location tills vidare
        if(LoggedIn.isInLoggad()){
            EditText firstNameText = (EditText)findViewById(R.id.firstNameEditText);
            firstNameText.setText(LoggedIn.valdProfil.getFirstName(), android.widget.TextView.BufferType.EDITABLE);
            EditText lastNameText = (EditText)findViewById(R.id.lastNameEditText);
            lastNameText.setText(LoggedIn.valdProfil.getLastName(), android.widget.TextView.BufferType.EDITABLE);
            EditText emailText = (EditText)findViewById(R.id.emailEditText);
            emailText.setText(LoggedIn.valdProfil.getEmail(), android.widget.TextView.BufferType.EDITABLE);
            EditText phoneText = (EditText)findViewById(R.id.phoneEditText);
            phoneText.setText(LoggedIn.valdProfil.getPhone(), android.widget.TextView.BufferType.EDITABLE);
            EditText addressText = (EditText)findViewById(R.id.adressEditText);
            addressText.setText(LoggedIn.valdProfil.getPreferredLocation(), android.widget.TextView.BufferType.EDITABLE);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_ad, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v){

    }

    public void createAd(View v){

        //Gör det möjligt att hämta värdet från textfälten
        EditText firstName = (EditText)findViewById(R.id.firstNameEditText);
        EditText lastName = (EditText)findViewById(R.id.lastNameEditText);
        EditText email = (EditText)findViewById(R.id.emailEditText);
        EditText phone = (EditText)findViewById(R.id.phoneEditText);
        EditText address = (EditText)findViewById(R.id.adressEditText);
        EditText description = (EditText)findViewById(R.id.discriptionEditText);

        //Skapar en ny profil av det man anger i input för att sedan kunna skapa en ad
        Profile newProfile = new Profile(firstName.getText().toString(), lastName.getText().toString(),
                email.getText().toString(),phone.getText().toString(),address.getText().toString());

        //Ger ett värde till radiobuttons
        RadioButton gardenRadioButton = (RadioButton) findViewById(R.id.gardenRadioButton);
        RadioButton labourRadioButton = (RadioButton) findViewById(R.id.labourRadioButton);
        RadioButton otherRadioButton = (RadioButton) findViewById(R.id.otherRadioButton);

        //Skapar ad beroende på category
        if (gardenRadioButton.isSelected()){
            Advertisement newAd = new Advertisement(newProfile, description.getText().toString(), Category.GARDEN);
        }else if(labourRadioButton.isSelected()){
            Advertisement newAd = new Advertisement(newProfile, description.getText().toString(), Category.LABOUR);
        }else{
            Advertisement newAd = new Advertisement(newProfile, description.getText().toString(), Category.OTHER);
        }

        //Notis som meddelar att aden har publicerats
        Toast.makeText(this, "Din annons har publicerats", Toast.LENGTH_LONG).show();

    }
}
