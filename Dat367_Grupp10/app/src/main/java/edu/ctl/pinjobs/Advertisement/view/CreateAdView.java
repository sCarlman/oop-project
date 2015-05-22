package edu.ctl.pinjobs.advertisement.view;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.filips.dat367_grupp10.R;

import java.util.ArrayList;
import java.util.List;

import edu.ctl.pinjobs.advertisement.model.Category;
import edu.ctl.pinjobs.profile.model.IProfile;


/**
 * Created by Isaac on 2015-05-07.
 */
public class CreateAdView {

    private EditText locationEditText;
    private EditText descriptionEditText;
    private EditText titleEditText;
    private RadioButton gardenRadioButton;
    private RadioButton labourRadioButton;
    private DatePicker adEndDatePicker;
    private Button chooseDateButton;
    private Button createAdButton;
    private EditText cityEditText;

    private boolean isEmpty;
    private View focusView;

    public CreateAdView(Activity activity, View.OnClickListener v, IProfile myProfile){

        this.locationEditText = (EditText) activity.findViewById(R.id.adLocationEditText);
        this.descriptionEditText = (EditText) activity.findViewById(R.id.adDescriptionEditText);
        this.titleEditText = (EditText) activity.findViewById(R.id.adTitleEditText);
        this.gardenRadioButton = (RadioButton) activity.findViewById(R.id.adGardenRadioButton);
        this.labourRadioButton = (RadioButton) activity.findViewById(R.id.adLabourRadioButton);
        this.cityEditText = (EditText) activity.findViewById(R.id.adCityEditText);
        this.createAdButton = (Button) activity.findViewById(R.id.createAdButton);
        this.chooseDateButton = (Button) activity.findViewById(R.id.chooseDateButton);
        this.createAdButton.setOnClickListener(v);
        chooseDateButton.setOnClickListener(v);
        this.adEndDatePicker = (DatePicker) activity.findViewById(R.id.adEndDateDatePicker);
        setDefaultLocation(myProfile);
    }

    //sets location to default address of the advertiser
    public void setDefaultLocation(IProfile myProfile){
        String[] addressAndCity = myProfile.getAddress().split(",");
        locationEditText.setText(addressAndCity[0]);
        cityEditText.setText(addressAndCity[1]);
    }

    //returns true if textFields are empty
    public boolean isTextFieldsNull(){
        //resets textFields from  previous errorMarks
        resetTextFields(locationEditText);
        resetTextFields(descriptionEditText);
        resetTextFields(titleEditText);
        resetTextFields(cityEditText);

        isEmpty = false;
        focusView = null;

        //checks if textFields contains any input, if so; sets given errorMessage
        checkField(cityEditText, "Stad ej ifylld");
        checkField(locationEditText, "Address ej ifylld");
        checkField(descriptionEditText, "Beskrivning ej ifylld");
        checkField(titleEditText, "Titel ej ifylld");

        if(isEmpty == true){
            focusView.requestFocus();
        }
        return isEmpty;
    }

    //returns chosen date. returns List<Integer> of {day, month, year}
    public List<Integer> getDate() {
        List<Integer> dayMonthYear= new ArrayList<>();
        dayMonthYear.add(adEndDatePicker.getDayOfMonth());
        dayMonthYear.add(adEndDatePicker.getMonth());
        dayMonthYear.add(adEndDatePicker.getYear());
        return dayMonthYear;
    }

    //returns chosen Category
    public Category getSelectedCategory() {
        if(gardenRadioButton.isChecked()){
            return Category.GARDEN;
        }else if(labourRadioButton.isChecked()){
            return Category.LABOUR;
        }else{
            return Category.OTHER;
        }
    }

    //shows or hides the massive datePicker
    public void switchDatePickerVisibility() {
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

    //checks if textField is empty, if so; sets an error at that textField
    private void checkField(EditText e, String error){
        if(TextUtils.isEmpty(e.getText().toString().trim())){
            e.setError(error);
            e.setHintTextColor(Color.RED);

            isEmpty = true;
            focusView = e;
        }
    }
    //resets textFields from previous errorMessages and red marks
    public static void resetTextFields(EditText e){
        e.setError(null);
        e.setHintTextColor(Color.BLACK);
    }

    public String getLocation(){
        return locationEditText.getText().toString().trim() + "," +
                cityEditText.getText().toString().trim();
    }

    public String getTitle(){
        return titleEditText.getText().toString().trim();
    }

    public String getDescription(){
        return descriptionEditText.getText().toString().trim();
    }

    public void setInputError(String error){
        switch (error){
            case "title": titleEditText.setError("Måste vara mellan 1 och 30 bokstäver");
                titleEditText.requestFocus();
                break;
            case "description": descriptionEditText.setError("Max 300 tecken, min 1");
                descriptionEditText.requestFocus();
                break;
            case "location": locationEditText.setError("Ej giltig adress");
                locationEditText.requestFocus();
                break;
        }
    }

}
