package edu.ctl.pinjobs.Advertisement;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


/**
 * Created by Isaac on 2015-05-07.
 */
public class CreateAdView {

    private EditText addressEditText;
    private EditText descriptionEditText;
    private RadioButton gardenRadioButton;
    private RadioButton labourRadioButton;
    private RadioButton otherRadioButton;

    private String address;
    private String description;
    private Category category;

    public CreateAdView(EditText addressEditText, EditText descriptionEditText,
                        RadioButton gardenRadioButton, RadioButton labourRadioButton,
                        RadioButton otherRadioButton, Button createAdButton,
                        View.OnClickListener v){
        this.addressEditText = addressEditText;
        this.descriptionEditText = descriptionEditText;
        this.gardenRadioButton = gardenRadioButton;
        this.labourRadioButton = labourRadioButton;
        this.otherRadioButton = otherRadioButton;
        createAdButton.setOnClickListener(v);
    }

    public void createAdButtonClicked(){
        
    }
}
