package edu.ctl.pinjobs.Advertisement;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import edu.ctl.pinjobs.Handler.AdressNotFoundException;
import edu.ctl.pinjobs.eventbus.EventBus;
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
    private Button chooseDateButton;

    private Advertisement newAd;
    private String location;
    private String description;
    private String title;
    private Category category;
    private IProfile newProfile;
    private CreateAdActivity activity;
    private int day;
    private int month;
    private int year;
    private double lat;
    private double lng;

    private boolean cancel;
    private View focusView;
    private Context activityContext;

    private AdvertisementUtils adUtils;

    public CreateAdView(EditText addressEditText, EditText descriptionEditText,
                        EditText titleEditText,RadioButton gardenRadioButton,
                        RadioButton labourRadioButton, RadioButton otherRadioButton,
                        Button createAdButton, Button chooseDateButton, Context v,
                        DatePicker adEndDatePicker){

        this.locationEditText = addressEditText;
        this.descriptionEditText = descriptionEditText;
        this.titleEditText = titleEditText;
        this.gardenRadioButton = gardenRadioButton;
        this.labourRadioButton = labourRadioButton;
        this.otherRadioButton = otherRadioButton;
        createAdButton.setOnClickListener((View.OnClickListener)v);
        chooseDateButton.setOnClickListener((View.OnClickListener)v);
        activityContext = v;
        this.chooseDateButton = chooseDateButton;
        this.adEndDatePicker = adEndDatePicker;
        this.activity = (CreateAdActivity)v;
    }

    public void setNewProfile(IProfile newProfile){
        this.newProfile = newProfile;
        System.out.println("HOLA " + newProfile.getAddress());
        //sets location to default address of the advertiser
        locationEditText.setText(newProfile.getAddress());
    }

    //Attempt to create ad by validate if textfields contains a string
    public void attemptCreateAd(){

        resetTextFields(locationEditText);
        resetTextFields(descriptionEditText);
        resetTextFields(titleEditText);

        this.cancel = false;
        this.focusView = null;

        copyTextFieldData();

        checkFields(location, locationEditText, "Address ej ifylld");
        checkFields(description, descriptionEditText, "Beskrivning ej ifylld");
        checkFields(title, titleEditText, "Titel ej ifylld");

        if(cancel == true){
            focusView.requestFocus();
        }else{
            createAd();
        }
    }

    public void createAd(){
        copyTextFieldData();
        copySelectedCategory();
        copyEndDate();
        System.out.println("Location is: " + location);
        if(activityContext == null){
            System.out.println("Im motherfucking null!!!");
        }else{
            System.out.println(activityContext);
        }

        adUtils = new AdvertisementUtils();

        
        try {
            lat = adUtils.getLocationFromAddress(activityContext,location).latitude;
            lng = adUtils.getLocationFromAddress(activityContext,location).longitude;
        } catch (AdressNotFoundException e) {
            e.printStackTrace();
            //TODO: exception.
        }

        newAd = new Advertisement(newProfile, title, description, location, category,
                day, month, year, lat, lng);
        activity.finish();
        adPosted(activity);
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

    public void adPosted(Context c){
        Toast.makeText(c, "Ad posted!", Toast.LENGTH_LONG).show();
    }

    public void showDatePicker() {

        if(!(adEndDatePicker.getVisibility() == View.VISIBLE)) {
            adEndDatePicker.setVisibility(View.VISIBLE);
            chooseDateButton.setText("Dölj datum");
            focusView = adEndDatePicker;
            focusView.requestFocus();
        }else {
            adEndDatePicker.setVisibility(View.GONE);
            chooseDateButton.setText("Välj slutdatum för annons");
        }
    }

    //Make check if textfields is empty
    private void checkFields(String s, EditText e, String error){
        if(TextUtils.isEmpty(s)){
            e.setError(error);
            e.setHintTextColor(Color.RED);

            this.cancel = true;
            this.focusView = e;
        }
    }

    public static void resetTextFields(EditText e){
        e.setError(null);
        e.setHintTextColor(Color.BLACK);
    }
}
