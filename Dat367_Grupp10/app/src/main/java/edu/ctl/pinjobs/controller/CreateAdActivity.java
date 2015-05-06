package edu.ctl.pinjobs.controller;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import edu.ctl.pinjobs.model.Advertisement;
import edu.ctl.pinjobs.model.Category;
import edu.ctl.pinjobs.Handler.Location;
import edu.ctl.pinjobs.model.Profile;
import com.example.filips.dat367_grupp10.R;


public class CreateAdActivity extends ActionBarActivity implements View.OnClickListener{
    private Profile newProfile;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private EditText addressEditText;
    private EditText descriptionEditText;
    private RadioButton gardenRadioButton;
    private RadioButton labourRadioButton;
    private RadioButton otherRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ad);

        //Fixar defaults om man är inloggad.
        //Saknar location tills vidare
        //if(LoggedIn.isInLoggad()){
             firstNameEditText = (EditText)findViewById(R.id.firstNameEditText);
//            firstNameText.setText(LoggedIn.valdProfil.getFirstName(), android.widget.TextView.BufferType.EDITABLE);
              lastNameEditText = (EditText)findViewById(R.id.lastNameEditText);
  //          lastNameText.setText(LoggedIn.valdProfil.getLastName(), android.widget.TextView.BufferType.EDITABLE);
              emailEditText = (EditText)findViewById(R.id.emailEditText);
//            emailText.setText(LoggedIn.valdProfil.getEmail(), android.widget.TextView.BufferType.EDITABLE);
              phoneEditText = (EditText)findViewById(R.id.phoneEditText);
      //      phoneText.setText(LoggedIn.valdProfil.getPhone(), android.widget.TextView.BufferType.EDITABLE);
              addressEditText = (EditText)findViewById(R.id.adressEditText);
              descriptionEditText = (EditText)findViewById(R.id.discriptionEditText);
        //    addressText.setText(LoggedIn.valdProfil.getPreferredLocation().toString(), android.widget.TextView.BufferType.EDITABLE);

        //}
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

        Location locationhelper = new Location();

        //Gör det möjligt att hämta värdet från
        /*
        firstName = (EditText)findViewById(R.id.firstNameEditText);
        lastName = (EditText)findViewById(R.id.lastNameEditText);
        email = (EditText)findViewById(R.id.emailEditText);
        phone = (EditText)findViewById(R.id.phoneEditText);
        address = (EditText)findViewById(R.id.adressEditText);
        description = (EditText)findViewById(R.id.discriptionEditText);
        */

        //Skapar en ny profil av det man anger i input för att sedan kunna skapa en ad
        //newProfile = new Profile(firstNameEditText.getText().toString(), lastNameEditText.getText().toString(),
                //emailEditText.getText().toString(),phoneEditText.getText().toString(),
               // locationhelper.getLocationFromAddress(this,addressEditText.getText().toString()));

        //Ger ett värde till radiobuttons
        gardenRadioButton = (RadioButton) findViewById(R.id.gardenRadioButton);
        labourRadioButton = (RadioButton) findViewById(R.id.labourRadioButton);
        otherRadioButton = (RadioButton) findViewById(R.id.otherRadioButton);

        /*Database tempDataBase = Database.getInstance();

        //Skapar ad beroende på category
        if (gardenRadioButton.isSelected()){
            Advertisement newAd = new Advertisement();
            newAd.setAdvertisement(firstNameEditText.getText().toString(), lastNameEditText.getText().toString(),
                    emailEditText.getText().toString(), phoneEditText.getText().toString(), locationhelper.getLocationFromAddress(this, addressEditText.getText().toString()),
                    Category.GARDEN, descriptionEditText.getText().toString());
            System.out.println(newAd);
            System.out.println(newAd.getPosition());
            tempDataBase.addAdToDatabase(newAd);
        }else if(labourRadioButton.isSelected()){
            Advertisement newAd = new Advertisement();
            newAd.setAdvertisement(firstNameEditText.getText().toString(), lastNameEditText.getText().toString(),
                    emailEditText.getText().toString(), phoneEditText.getText().toString(), locationhelper.getLocationFromAddress(this, addressEditText.getText().toString()),
                    Category.LABOUR, descriptionEditText.getText().toString());
            System.out.println(newAd);
            System.out.println(newAd.getPosition());
            tempDataBase.addAdToDatabase(newAd);
        }else{

            Advertisement newAd = new Advertisement();
            newAd.setAdvertisement(firstNameEditText.getText().toString(), lastNameEditText.getText().toString(),
                    emailEditText.getText().toString(), phoneEditText.getText().toString(), locationhelper.getLocationFromAddress(this, addressEditText.getText().toString()),
                    Category.OTHER, descriptionEditText.getText().toString());
            System.out.println(newAd);
            tempDataBase.addAdToDatabase(newAd);
        }

        //Notis som meddelar att aden har publicerats
        Toast.makeText(this, "Din annons har publicerats", Toast.LENGTH_LONG).show();
        */
    }
}
