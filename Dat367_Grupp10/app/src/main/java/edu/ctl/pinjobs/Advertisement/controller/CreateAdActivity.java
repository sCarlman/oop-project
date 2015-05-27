package edu.ctl.pinjobs.advertisement.controller;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.filips.dat367_grupp10.R;

import java.util.List;

import edu.ctl.pinjobs.advertisement.model.Advertisement;
import edu.ctl.pinjobs.advertisement.model.AndroidAdvertisement;
import edu.ctl.pinjobs.advertisement.model.IAdvertisement;
import edu.ctl.pinjobs.advertisement.model.WrongAdInputException;
import edu.ctl.pinjobs.advertisement.utils.AdvertisementUtils;
import edu.ctl.pinjobs.advertisement.view.CreateAdView;
import edu.ctl.pinjobs.handler.model.AdvertisementListHolder;
import edu.ctl.pinjobs.profile.model.IProfile;

public class CreateAdActivity extends ActionBarActivity implements View.OnClickListener {

    private CreateAdView view;
    private IProfile myProfile;
    private IAdvertisementService adService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ad);

        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        adService = (IAdvertisementService) bundle.getSerializable("AD_SERVICE");
        myProfile = (IProfile)bundle.getSerializable("USER_PROFILE");
        view = new CreateAdView(this, this, myProfile);
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
        if (v == findViewById(R.id.createAdButton)){
            if(!view.isTextFieldsNull()) {

                AdvertisementUtils adUtils = new AdvertisementUtils();
                double lat = adUtils.getLocationFromAddress(CreateAdActivity.this, view.getLocation()).latitude;
                double lng = adUtils.getLocationFromAddress(CreateAdActivity.this, view.getLocation()).longitude;
                try {
                    Advertisement newAd = new Advertisement(myProfile, view.getTitle(),
                            view.getDescription(), view.getLocation(),
                            view.getSelectedCategory(), view.getDate().get(0),
                            view.getDate().get(1), view.getDate().get(2),
                            lat, lng);
                    postAd(newAd);
                }catch(WrongAdInputException e){
                    if(e.getName().equals("title")){
                        view.setInputError("title");
                    }else if(e.getName().equals("description")) {
                        view.setInputError("description");
                    }else if(e.getName().equals("location")){
                        view.setInputError("location");
                    }
                }

            }
        }else if(v == findViewById(R.id.chooseDateButton)){
            view.switchDatePickerVisibility();
        }
    }



    //uploads ad to database if there is no other ad equal to the newAd
    public void postAd(IAdvertisement newAd){
        List<IAdvertisement> adList = AdvertisementListHolder.getInstance().getList();
        if (checkIfAdExists(newAd, adList)) {
            Toast.makeText(this, "Anonsen finns redan",
                    Toast.LENGTH_LONG).show();
        } else {
            adService.saveAd(newAd);
            Toast.makeText(this, "Din annons har nu publicerats!", Toast.LENGTH_LONG).show();
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
