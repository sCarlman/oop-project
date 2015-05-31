package edu.ctl.pinjobs.advertisement.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.filips.dat367_grupp10.R;

import java.io.IOException;

import edu.ctl.pinjobs.advertisement.model.Advertisement;
import edu.ctl.pinjobs.advertisement.model.AndroidAdvertisement;
import edu.ctl.pinjobs.advertisement.model.IAdvertisement;
import edu.ctl.pinjobs.advertisement.model.WrongAdInputException;
import edu.ctl.pinjobs.advertisement.service.AdvertisementService;
import edu.ctl.pinjobs.advertisement.service.IAdvertisementService;
import edu.ctl.pinjobs.advertisement.utils.AdvertisementUtils;
import edu.ctl.pinjobs.advertisement.view.ModifyAdView;
import edu.ctl.pinjobs.profile.service.IProfileService;

public class ModifyAdActivity extends ActionBarActivity implements View.OnClickListener {

    private ModifyAdView view;
    private IAdvertisementService adService;
    private IProfileService profileService;
    private IAdvertisement ad;
    private IOpenMapView iOpenMapView;
    private AndroidAdvertisement androidAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_ad);

        //gets all extra info/data form previous activity
        Bundle bundle = getIntent().getExtras();
        profileService = (IProfileService) getIntent().getExtras().getSerializable("PROFILE_SERVICE");
        adService = new AdvertisementService(profileService);
        iOpenMapView = (IOpenMapView) getIntent().getExtras().getSerializable("OPEN_MAP_VIEW");
        androidAd = bundle.getParcelable("Advertisement");
        ad = androidAd.getAd();

        view = new ModifyAdView(this, ad, this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modify_ad, menu);
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

    //method that saves the modified ad and fisishes the activity
    public void saveNewModifiedAd(IAdvertisement newAd){
        adService.updateAd(adService.getAdID(androidAd.getAd()), newAd);
        iOpenMapView.startActivity(this, newAd,androidAd);
        finish();
    }

    @Override
    public void onClick(View view) {
        if(view == findViewById(R.id.modifyButton)) {
            if(!ModifyAdActivity.this.view.getEditable()){
                setEditableView();
            }else{
                AdvertisementUtils adUtils = new AdvertisementUtils();
                //Sets illegal coordinates so tha app don't say it posts ad if try catch passes
                double lat = 100.0;
                double lng = 100.0;
                try{
                    //Tries to get coordinates from the given location
                    lat = adUtils.getLocationFromAddress(ModifyAdActivity.this, ModifyAdActivity.this.view.getLocation()).latitude;
                    lng = adUtils.getLocationFromAddress(ModifyAdActivity.this, ModifyAdActivity.this.view.getLocation()).longitude;
                }catch(IOException e) {
                   showInternetConnectionLost();
                }
                //Tries to save a modified ad and cast WrongAdInputException if inputs are wrong
                try {
                    IAdvertisement newAd = new Advertisement(ad.getAdvertiser(), ModifyAdActivity.this.view.getTitle(),
                            ModifyAdActivity.this.view.getDescription(), ModifyAdActivity.this.view.getLocation(),
                            ModifyAdActivity.this.view.getCategory(),ad.getDay(),ad.getMonth(),ad.getYear(),
                            lat, lng);
                    saveNewModifiedAd(newAd);
                }catch(WrongAdInputException e){
                    ModifyAdActivity.this.view.setInputError(e.getName());
                }

            }

        }
    }

    //Creates an dialog telling you it's the internet connections fault you can't post new ad
    private void showInternetConnectionLost(){
        new AlertDialog.Builder(ModifyAdActivity.this)
                .setTitle("Info")
                .setMessage("Internet not available, Cross check your internet connectivity and try again")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).setIcon(android.R.drawable.ic_dialog_alert).show();
    }

    private void setEditableView(){
        ModifyAdActivity.this.view.enableEditTextFields();
        ModifyAdActivity.this.view.enableRadioButtons();
        ModifyAdActivity.this.view.changeButtonText();
        ModifyAdActivity.this.view.setEditable();
    }

}
