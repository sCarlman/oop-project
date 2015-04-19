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
       
        EditText firstName = (EditText)findViewById(R.id.firstNameEditText);
        EditText lastName = (EditText)findViewById(R.id.lastNameEditText);
        EditText email = (EditText)findViewById(R.id.emailEditText);
        EditText phone = (EditText)findViewById(R.id.phoneEditText);
        EditText address = (EditText)findViewById(R.id.adressEditText);
        EditText description = (EditText)findViewById(R.id.discriptionEditText);

        Profile newProfile = new Profile(firstName.getText().toString(), lastName.getText().toString(),
                email.getText().toString(),phone.getText().toString(),address.getText().toString());

        RadioButton gardenRadioButton = (RadioButton) findViewById(R.id.gardenRadioButton);
        RadioButton labourRadioButton = (RadioButton) findViewById(R.id.labourRadioButton);
        RadioButton otherRadioButton = (RadioButton) findViewById(R.id.otherRadioButton);

        if (gardenRadioButton.isSelected()){
            Advertisement newAd = new Advertisement(newProfile, description.getText().toString(), Category.GARDEN);
        }else if(labourRadioButton.isSelected()){
            Advertisement newAd = new Advertisement(newProfile, description.getText().toString(), Category.LABOUR);
        }else{
            Advertisement newAd = new Advertisement(newProfile, description.getText().toString(), Category.OTHER);
        }

        Toast.makeText(this, "Din annons har publicerats", Toast.LENGTH_LONG).show();

    }
}
