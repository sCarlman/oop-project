package edu.ctl.pinjobs.Advertisement;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import edu.ctl.pinjobs.Services.EventBus;
import edu.ctl.pinjobs.profile.IProfile;


/**
 * Created by Isaac on 2015-05-07.
 */
public class CreateAdView {

    private EditText locationEditText;
    private EditText descriptionEditText;
    private EditText titleEditText;
    private RadioButton gardenRadioButton;
    private RadioButton labourRadioButton;
    private RadioButton otherRadioButton;

    private Advertisement newAd;
    private String location;
    private String description;
    private String title;
    private Category category;
    private IProfile newProfile;

    public CreateAdView(EditText addressEditText, EditText descriptionEditText,
                        EditText titleEditText,RadioButton gardenRadioButton,
                        RadioButton labourRadioButton, RadioButton otherRadioButton,
                        Button createAdButton, View.OnClickListener v){
        this.locationEditText = addressEditText;
        this.descriptionEditText = descriptionEditText;
        this.titleEditText = titleEditText;
        this.gardenRadioButton = gardenRadioButton;
        this.labourRadioButton = labourRadioButton;
        this.otherRadioButton = otherRadioButton;
        createAdButton.setOnClickListener(v);
    }

    public void setNewProfile(IProfile newProfile){
        this.newProfile = newProfile;

        //sets location to default address of the advertiser
        locationEditText.setText(newProfile.getAddress());
    }

    public void createAd(){
        location = locationEditText.getText().toString().trim();
        description = descriptionEditText.getText().toString().trim();
        title = titleEditText.getText().toString().trim();
        setSelectedCategory();

        newAd = new Advertisement(newProfile, title, description, location, category);
        EventBus.INSTANCE.publish(EventBus.Event.POST_AD, newAd);
    }

    private void setSelectedCategory() {
        if(gardenRadioButton.isChecked()){
            category = Category.GARDEN;
        }else if(labourRadioButton.isChecked()){
            category = Category.LABOUR;
        }else{
            category = Category.OTHER;
        }
    }

    public void notLoggedIn(Context c){
        Toast.makeText(c, "Pls log in to create ad", Toast.LENGTH_LONG).show();
    }

    public void AdPosted(Context c){
        Toast.makeText(c, "Ad posted!", Toast.LENGTH_LONG).show();
        clearFields();
    }

    private void clearFields() {
        titleEditText.setText("");
        descriptionEditText.setText("");
        locationEditText.setText("");
        newProfile = null;
    }
}
