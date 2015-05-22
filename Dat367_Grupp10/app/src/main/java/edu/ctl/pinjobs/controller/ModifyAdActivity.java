package edu.ctl.pinjobs.controller;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.filips.dat367_grupp10.R;

import java.util.ArrayList;
import java.util.List;

import edu.ctl.pinjobs.advertisement.model.Advertisement;
import edu.ctl.pinjobs.advertisement.model.AndroidAdvertisement;
import edu.ctl.pinjobs.advertisement.model.IAdvertisement;
import edu.ctl.pinjobs.advertisement.model.WrongAdInputException;
import edu.ctl.pinjobs.advertisement.utils.AdvertisementUtils;
import edu.ctl.pinjobs.advertisement.view.ModifyAdView;
import edu.ctl.pinjobs.eventbus.EventBus;
import edu.ctl.pinjobs.main.BackgroundThread;
import edu.ctl.pinjobs.services.AdvertisementService;
import edu.ctl.pinjobs.services.IAdvertisementService;

public class ModifyAdActivity extends ActionBarActivity implements View.OnClickListener {

    private ModifyAdView view;
    private IAdvertisementService adService;
    private IAdvertisement ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adService = new AdvertisementService();
        setContentView(R.layout.activity_modify_ad);

        Bundle bundle = getIntent().getExtras();
        AndroidAdvertisement androidAd = bundle.getParcelable("Advertisement");
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

    public void saveNewModifiedAd(List<IAdvertisement> ad){
        IAdvertisement oldAd = (ad).get(0);
        IAdvertisement newAd = (ad).get(1);
        adService.updateAd(adService.getAdID(oldAd), newAd);
        BackgroundThread thread = new BackgroundThread(adService);
        thread.start();
        Intent intent = new Intent(this.getApplicationContext(), MapActivity.class);
        AndroidAdvertisement androidAD = new AndroidAdvertisement(newAd);
        intent.putExtra("Advertisement", androidAD);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.getApplicationContext().startActivity(intent);
        Toast.makeText(this, "Annons ändrad!", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onClick(View view) {
        if(view == findViewById(R.id.modifyButton)) {

            if(!ModifyAdActivity.this.view.getEditable()){

                ModifyAdActivity.this.view.enableEditTextFields();
                ModifyAdActivity.this.view.enableRadioButtons();
                ModifyAdActivity.this.view.changeButtonText();
                ModifyAdActivity.this.view.setEditable();
            }else{

                List<IAdvertisement> oldAndModded = new ArrayList<>();
                oldAndModded.add(ad);

                AdvertisementUtils adUtils = new AdvertisementUtils();
                double lat = adUtils.getLocationFromAddress(ModifyAdActivity.this, ModifyAdActivity.this.view.getLocation()).latitude;
                double lng = adUtils.getLocationFromAddress(ModifyAdActivity.this, ModifyAdActivity.this.view.getLocation()).longitude;

                try {
                    IAdvertisement newAd = new Advertisement(ad.getAdvertiser(), ModifyAdActivity.this.view.getTitle(),
                            ModifyAdActivity.this.view.getDescription(), ModifyAdActivity.this.view.getLocation(),
                            ModifyAdActivity.this.view.getCategory(),ad.getDay(),ad.getMonth(),ad.getYear(),
                            lat, lng);
                    oldAndModded.add(newAd);

                }catch(WrongAdInputException e){
                    if(e.getName().equals("title")){
                        ModifyAdActivity.this.view.setInputError("title");
                    }else if(e.getName().equals("description")) {
                        ModifyAdActivity.this.view.setInputError("description");
                    }else if(e.getName().equals("location")){
                        ModifyAdActivity.this.view.setInputError("location");
                    }
                }
                ModifyAdActivity.this.view.disableEditTextFields();
                ModifyAdActivity.this.view.disableRadioButtons();
                ModifyAdActivity.this.view.setEditable();

                saveNewModifiedAd(oldAndModded);
            }

        }
    }
}
