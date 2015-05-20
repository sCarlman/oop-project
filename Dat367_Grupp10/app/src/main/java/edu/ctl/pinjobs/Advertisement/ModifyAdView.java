package edu.ctl.pinjobs.Advertisement;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

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

    public ModifyAdView(EditText titleEditText, EditText descriptionEditText,
                          EditText addressEditText,  Button modifyButton,
                        RadioButton modifyGardenRadioButton, RadioButton modifyLabourRadioButton,
                        RadioButton modifyOtherRadioButton, final IAdvertisement ad){

        this.titleEditText = titleEditText;
        this.descriptionEditText = descriptionEditText;
        this.addressEditText = addressEditText;

        this.modifyGardenRadioButton = modifyGardenRadioButton;
        this.modifyLabourRadioButton = modifyLabourRadioButton;
        this.modifyOtherRadioButton = modifyOtherRadioButton;
        this.modifyButton = modifyButton;
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editable == false){
                    enableEditTextFields();
                    enableRadioButtons();
                    editable = true;
                    changeButtonText();
                }else{
                    modifyAd(ad);
                    disableEditTextFields();
                    disableRadioButtons();
                    editable = false;
                    ModifyAdActivity tempActivity = new ModifyAdActivity();
                    tempActivity.saveNewModifiedAd(ad);
                }
            }
        });
        setTexts(ad);
        disableEditTextFields();
    }

    private void changeButtonText(){
        if(modifyButton.getText().equals("Ändra")){
            modifyButton.setText("Spara");
        }else{
            modifyButton.setText("Ändra");
        }
    }

    private void modifyAd(IAdvertisement ad){

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


    }

    private void enableEditTextFields(){
        titleEditText.setEnabled(true);
        descriptionEditText.setEnabled(true);
        addressEditText.setEnabled(true);

        titleEditText.setFocusable(true);
        descriptionEditText.setFocusable(true);
        addressEditText.setFocusable(true);

    }
    private void enableRadioButtons(){
        modifyGardenRadioButton.setEnabled(true);
        modifyLabourRadioButton.setEnabled(true);
        modifyOtherRadioButton.setEnabled(true);
    }

    private void disableEditTextFields(){
        titleEditText.setEnabled(false);
        descriptionEditText.setEnabled(false);
        addressEditText.setEnabled(false);

        titleEditText.setFocusable(false);
        descriptionEditText.setFocusable(false);
        addressEditText.setFocusable(false);

    }

    private void disableRadioButtons(){
        modifyGardenRadioButton.setEnabled(false);
        modifyLabourRadioButton.setEnabled(false);
        modifyOtherRadioButton.setEnabled(false);
    }

    private void setTexts(IAdvertisement ad){
        titleEditText.setText(ad.getTitle().toString());
        descriptionEditText.setText(ad.getDescription().toString());
        addressEditText.setText(ad.getLocation().toString());

    }


}
