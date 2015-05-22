package edu.ctl.pinjobs.advertisement.view;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.filips.dat367_grupp10.R;

import java.util.ArrayList;
import java.util.List;

import edu.ctl.pinjobs.controller.ModifyAdActivity;
import edu.ctl.pinjobs.advertisement.model.WrongAdInputException;
import edu.ctl.pinjobs.advertisement.model.Category;
import edu.ctl.pinjobs.advertisement.model.IAdvertisement;

/**
 * Created by filiplarsson on 15-05-15.
 */
public class ModifyAdView {

    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText addressEditText;
    private RadioButton modifyGardenRadioButton;
    private RadioButton modifyLabourRadioButton;
    private RadioButton modifyOtherRadioButton;
    private Button modifyButton;
    Boolean editable=false;

    private IAdvertisement ad;
    private List<IAdvertisement> oldAndModdedAd;

    public ModifyAdView(Activity activity, final IAdvertisement ad,
                        View.OnClickListener controller){

        this.titleEditText = (EditText)activity.findViewById(R.id.modifyTitleEditText);
        this.descriptionEditText = (EditText)activity.findViewById(R.id.modifyDesctriptionEditText);
        this.addressEditText = (EditText)activity.findViewById(R.id.modifyAddressEditText);;

        this.modifyGardenRadioButton = (RadioButton)activity.findViewById(R.id.modifyGardenRadioButton);
        this.modifyLabourRadioButton = (RadioButton)activity.findViewById(R.id.modifyLabourRadioButton);
        this.modifyOtherRadioButton = (RadioButton)activity.findViewById(R.id.modifyOtherRadioButton);;
        this.modifyButton = (Button)activity.findViewById(R.id.modifyButton);
        this.ad = ad;

        modifyButton.setOnClickListener(controller);
        setTexts();
        disableEditTextFields();
        disableRadioButtons();
    }

    public void changeButtonText(){
        if(modifyButton.getText().equals("Ändra")){
            modifyButton.setText("Spara");
        }else{
            modifyButton.setText("Ändra");
        }
    }


    public Boolean getEditable(){
        return editable;
    }
    public Boolean setEditable(){
        editable = !editable;
        return editable;
    }
    public String getTitle(){
        return titleEditText.getText().toString().trim();
    }
    public  String getDescription(){
        return descriptionEditText.getText().toString().trim();
    }
    public  String getLocation(){
        return addressEditText.getText().toString().trim();
    }
    public Category getCategory(){
        if(modifyGardenRadioButton.isChecked()){
            return Category.GARDEN;
        }else if(modifyLabourRadioButton.isChecked()){
            return Category.LABOUR;
        }else{
            return Category.OTHER;
        }
    }




    public void enableEditTextFields(){
        titleEditText.setFocusableInTouchMode(true);
        descriptionEditText.setFocusableInTouchMode(true);
        addressEditText.setFocusableInTouchMode(true);

    }
    public void enableRadioButtons(){
        modifyGardenRadioButton.setEnabled(true);
        modifyLabourRadioButton.setEnabled(true);
        modifyOtherRadioButton.setEnabled(true);
    }

    public void disableEditTextFields(){

        titleEditText.setFocusable(false);
        descriptionEditText.setFocusable(false);
        addressEditText.setFocusable(false);

    }

    public void disableRadioButtons(){
        modifyGardenRadioButton.setEnabled(false);
        modifyLabourRadioButton.setEnabled(false);
        modifyOtherRadioButton.setEnabled(false);
    }

    public void setTexts(){
        titleEditText.setText(ad.getTitle().toString());
        descriptionEditText.setText(ad.getDescription().toString());
        addressEditText.setText(ad.getLocation().toString());

    }
    public void setInputError(String error){
        switch (error){
            case "title": titleEditText.setError("Måste vara mellan 1 och 30 bokstäver");
                titleEditText.requestFocus();
            case "description": descriptionEditText.setError("Max 300 tecken, min 1");
                descriptionEditText.requestFocus();
            case "location": addressEditText.setError("Ej giltig adress");
                addressEditText.requestFocus();
        }
    }


}
