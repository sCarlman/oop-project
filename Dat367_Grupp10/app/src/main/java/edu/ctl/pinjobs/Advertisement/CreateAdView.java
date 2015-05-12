package edu.ctl.pinjobs.Advertisement;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
    private DatePicker adEndDatePicker;

    private Advertisement newAd;
    private String location;
    private String description;
    private String title;
    private Category category;
    private IProfile newProfile;

    private int day;
    private int month;
    private int year;

    public CreateAdView(EditText addressEditText, EditText descriptionEditText,
                        EditText titleEditText,RadioButton gardenRadioButton,
                        RadioButton labourRadioButton, RadioButton otherRadioButton,
                        Button createAdButton, View.OnClickListener v,
                        DatePicker adEndDatePicker){
        this.locationEditText = addressEditText;
        this.descriptionEditText = descriptionEditText;
        this.titleEditText = titleEditText;
        this.gardenRadioButton = gardenRadioButton;
        this.labourRadioButton = labourRadioButton;
        this.otherRadioButton = otherRadioButton;
        createAdButton.setOnClickListener(v);
        this.adEndDatePicker = adEndDatePicker;
    }

    public void setNewProfile(IProfile newProfile){
        this.newProfile = newProfile;

        //sets location to default address of the advertiser
        locationEditText.setText(newProfile.getAddress());
    }

    public void createAd(){
        copyTextFieldData();
        copySelectedCategory();
        copyEndDate();

        newAd = new Advertisement(newProfile, title, description, location, category,
                day, month, year);
        EventBus.INSTANCE.publish(EventBus.Event.POST_AD, newAd);
    }

    private void copyEndDate() {
        day = adEndDatePicker.getDayOfMonth();
        month = adEndDatePicker.getMonth();
        year = adEndDatePicker.getYear();

    }

    private void copyTextFieldData() {
        location = locationEditText.getText().toString().trim();
        description = descriptionEditText.getText().toString().trim();
        title = titleEditText.getText().toString().trim();
    }

    private void copySelectedCategory() {
        if(gardenRadioButton.isChecked()){
            category = Category.GARDEN;
        }else if(labourRadioButton.isChecked()){
            category = Category.LABOUR;
        }else if(otherRadioButton.isChecked()){
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
