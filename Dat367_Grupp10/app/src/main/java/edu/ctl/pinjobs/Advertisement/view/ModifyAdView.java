package edu.ctl.pinjobs.advertisement.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

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
    private ModifyAdActivity modifyAdActivity;

    public ModifyAdView(EditText titleEditText, EditText descriptionEditText,
                          EditText addressEditText,  Button modifyButton,
                        RadioButton modifyGardenRadioButton, RadioButton modifyLabourRadioButton,
                        RadioButton modifyOtherRadioButton, final IAdvertisement ad,
                        View.OnClickListener controller, ModifyAdActivity activity){

        this.titleEditText = titleEditText;
        this.descriptionEditText = descriptionEditText;
        this.addressEditText = addressEditText;

        this.modifyGardenRadioButton = modifyGardenRadioButton;
        this.modifyLabourRadioButton = modifyLabourRadioButton;
        this.modifyOtherRadioButton = modifyOtherRadioButton;
        this.modifyButton = modifyButton;
        this.ad = ad;
        this.modifyAdActivity = activity;
        modifyButton.setOnClickListener(controller);
        setTexts();
        disableEditTextFields();
        disableRadioButtons();
    }

    private void changeButtonText(){
        if(modifyButton.getText().equals("Ändra")){
            modifyButton.setText("Spara");
        }else{
            modifyButton.setText("Ändra");
        }
    }

    public void modifyButtonClicked(){
        if(!editable){
            enableEditTextFields();
            enableRadioButtons();
            changeButtonText();
        }else{
            modifyAd();
            disableEditTextFields();
            disableRadioButtons();
            modifyAdActivity.saveNewModifiedAd(oldAndModdedAd);
        }
        editable = !editable;
    }

    private void modifyAd(){
        oldAndModdedAd = new ArrayList<>();
        oldAndModdedAd.add(ad);
        try{
            ad.setTitle(titleEditText.getText().toString().trim());
        }catch (WrongAdInputException e){

        }
        try{
            ad.setDescription(descriptionEditText.getText().toString().trim());
        }catch (WrongAdInputException e){

        }

        try{
            ad.setLocation(addressEditText.getText().toString().trim());
        }catch (WrongAdInputException e) {

        }

        if(modifyGardenRadioButton.isChecked()){
            ad.setCategory(Category.GARDEN);
        }else if(modifyLabourRadioButton.isChecked()){
            ad.setCategory(Category.LABOUR);
        }else{
            ad.setCategory(Category.OTHER);
        }
        oldAndModdedAd.add(ad);

    }

    private void enableEditTextFields(){
        /*titleEditText.setEnabled(true);
        descriptionEditText.setEnabled(true);
        addressEditText.setEnabled(true);

        titleEditText.setFocusable(true);
        descriptionEditText.setFocusable(true);
        addressEditText.setFocusable(true);*/
        titleEditText.setFocusableInTouchMode(true);
        descriptionEditText.setFocusableInTouchMode(true);
        addressEditText.setFocusableInTouchMode(true);

    }
    private void enableRadioButtons(){
        modifyGardenRadioButton.setEnabled(true);
        modifyLabourRadioButton.setEnabled(true);
        modifyOtherRadioButton.setEnabled(true);
    }

    private void disableEditTextFields(){

        titleEditText.setFocusable(false);
        descriptionEditText.setFocusable(false);
        addressEditText.setFocusable(false);
        //titleEditText.setEnabled(false);
        //descriptionEditText.setEnabled(false);
        //addressEditText.setEnabled(false);
    }

    private void disableRadioButtons(){
        modifyGardenRadioButton.setEnabled(false);
        modifyLabourRadioButton.setEnabled(false);
        modifyOtherRadioButton.setEnabled(false);
    }

    private void setTexts(){
        titleEditText.setText(ad.getTitle().toString());
        descriptionEditText.setText(ad.getDescription().toString());
        addressEditText.setText(ad.getLocation().toString());

    }


}
