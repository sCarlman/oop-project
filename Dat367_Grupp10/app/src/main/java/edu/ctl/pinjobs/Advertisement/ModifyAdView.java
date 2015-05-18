package edu.ctl.pinjobs.Advertisement;

import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by filiplarsson on 15-05-15.
 */
public class ModifyAdView {

    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText nameEditText;
    private EditText addressEditText;
    private EditText phoneEditText;
    private EditText emailEditText;

    public ModifyAdView(EditText titleEditText, EditText descriptionEditText, EditText nameEditText,
                          EditText addressEditText, EditText phoneEditText, EditText emailEditText,
                          IAdvertisement ad){

        this.titleEditText = titleEditText;
        this.descriptionEditText = descriptionEditText;
        this.nameEditText = nameEditText;
        this.addressEditText = addressEditText;
        this.phoneEditText = phoneEditText;
        this.emailEditText = emailEditText;

        setTexts(ad);
    }

    private void setTexts(IAdvertisement ad){
        this.titleEditText.setText(ad.getTitle());
        this.descriptionEditText.setText(ad.getDescription());
        this.nameEditText.setText(ad.getAdvertiser().getFirstName() + " " + ad.getAdvertiser().getLastName());
        this.addressEditText.setText(ad.getLocation());
        this.phoneEditText.setText(ad.getAdvertiser().getPhone());
        this.emailEditText.setText(ad.getAdvertiser().getEmail());
    }

}
