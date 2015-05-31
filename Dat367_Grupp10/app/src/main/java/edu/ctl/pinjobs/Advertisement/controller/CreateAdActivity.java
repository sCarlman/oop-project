package edu.ctl.pinjobs.advertisement.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.filips.dat367_grupp10.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.ctl.pinjobs.advertisement.model.Advertisement;
import edu.ctl.pinjobs.advertisement.model.AndroidAdvertisement;
import edu.ctl.pinjobs.advertisement.model.IAdvertisement;
import edu.ctl.pinjobs.advertisement.model.WrongAdInputException;
import edu.ctl.pinjobs.advertisement.service.IAdvertisementService;
import edu.ctl.pinjobs.advertisement.utils.AdvertisementUtils;
import edu.ctl.pinjobs.advertisement.view.CreateAdView;
import edu.ctl.pinjobs.profile.model.IProfile;

public class CreateAdActivity extends ActionBarActivity implements View.OnClickListener {

    private CreateAdView view;
    private IProfile myProfile;
    private IAdvertisementService adService;
    private List<IAdvertisement> adList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ad);

        //Gets extra information/data from previous activity
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        adService = (IAdvertisementService) bundle.getSerializable("AD_SERVICE");
        myProfile = (IProfile)bundle.getSerializable("USER_PROFILE");
        Parcelable[] androidAdList = bundle.getParcelableArray("AD_LIST");
        view = new CreateAdView(this, this, myProfile);

        //Gets all ads so it's possible to see if an ad you try to create is already posted
        adList = new ArrayList<>();
        for(int i = 0; i < androidAdList.length - 1; i++){
            adList.add(((AndroidAdvertisement) androidAdList[i]).getAd());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_ad, menu);
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

    public void onClick(View v){

        //This happens if the create ad button is clicked
        if (v == findViewById(R.id.createAdButton)){
            if(!view.isTextFieldsNull()) {

                AdvertisementUtils adUtils = new AdvertisementUtils();

                System.out.println(view.getLocation());
                //Sets illegal coordinates so tha app don't say it posts ad if try catch passes
                double lat = 100.0;
                double lng= 100.0;


                try {
                    //Tries to get coordinates from the given location
                    lat = adUtils.getLocationFromAddress(CreateAdActivity.this, view.getLocation()).latitude;
                    lng = adUtils.getLocationFromAddress(CreateAdActivity.this, view.getLocation()).longitude;
                } catch (IOException e) {
                    //Creates an dialog telling you it's the internet connections fault you can't post new ad
                    new AlertDialog.Builder(CreateAdActivity.this)
                            .setTitle("Info")
                            .setMessage("Internet not available, Cross check your internet connectivity and try again")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            }).setIcon(android.R.drawable.ic_dialog_alert).show();
                }

                //Tries to save a modified ad and cast WrongAdInputException if inputs are wrong
                try {
                    Advertisement newAd = new Advertisement(myProfile, view.getTitle(),
                            view.getDescription(), view.getLocation(),
                            view.getSelectedCategory(), view.getDate().get(0),
                            view.getDate().get(1), view.getDate().get(2),
                            lat, lng);
                    postAd(newAd);
                }catch(WrongAdInputException e){
                    view.setInputError(e.getName());
                }

            }
        }else if(v == findViewById(R.id.chooseDateButton)){
            view.switchDatePickerVisibility();
        }
    }

    //uploads ad to database if there is no other ad equal to the newAd
    public void postAd(IAdvertisement newAd){

        if (checkIfAdExists(newAd, adList)) {
            Toast.makeText(this, "Du har redan skapat annonsen!",
                    Toast.LENGTH_LONG).show();
        } else {
            adService.saveAd(newAd);
            Toast.makeText(this, "Din annons har nu publicerats!", Toast.LENGTH_LONG).show();

            //Puts an ad in the intent so it's possible to have acces to it in next acivity
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable("Advertisement",new AndroidAdvertisement(newAd));
            intent.putExtras(bundle);

            setResult(10,intent);
            finish();
        }
    }

    //returns true if there is no ad equal to newAd in the database
    private boolean checkIfAdExists(IAdvertisement newAd, List<IAdvertisement> adList){
        for(IAdvertisement loopAd: adList){
            if(loopAd.equals(newAd)) {
                return true;
            }
        }
        return false;
    }

}
