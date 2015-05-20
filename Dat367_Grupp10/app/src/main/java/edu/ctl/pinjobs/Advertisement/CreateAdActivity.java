package edu.ctl.pinjobs.Advertisement;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.filips.dat367_grupp10.R;

import java.util.List;

import edu.ctl.pinjobs.Handler.AdvertisementListHolder;
import edu.ctl.pinjobs.eventbus.EventBus;
import edu.ctl.pinjobs.profile.IProfile;

public class CreateAdActivity extends ActionBarActivity implements View.OnClickListener,
        EventBus.IEventHandler {

    private CreateAdView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ad);
        EventBus.INSTANCE.addListener(this);
        view = new CreateAdView((EditText) findViewById(R.id.adLocationEditText),
                (EditText) findViewById(R.id.adDescriptionEditText),
                (EditText) findViewById(R.id.adTitleEditText),
                (RadioButton) findViewById(R.id.gardenRadioButton),
                (RadioButton) findViewById(R.id.labourRadioButton),
                (RadioButton) findViewById(R.id.otherRadioButton),
                (Button) findViewById(R.id.createAdButton),
                (Button) findViewById(R.id.chooseDateButton), this,
                (DatePicker) findViewById(R.id.adEndDateDatePicker),
                (EditText) findViewById(R.id.createAdCityEditText));
        EventBus.INSTANCE.publish(EventBus.Event.CREATE_AD_SETUP, null);
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
            view.attemptCreateAd();
        }else if(v == findViewById(R.id.chooseDateButton)){
            view.showDatePicker();
        }
    }

    public void checkIfAdExistsAndSendEvent(IAdvertisement newAd){
        List<IAdvertisement> adList = AdvertisementListHolder.getInstance().getList();
        if(checkIfAdExists(newAd, adList)) {
            Toast.makeText(getApplicationContext(), "Anonsen finns redan",
                    Toast.LENGTH_LONG).show();
        }else {
            EventBus.INSTANCE.publish(EventBus.Event.POST_AD, newAd);
            finish();
        }
    }

    private boolean checkIfAdExists(IAdvertisement newAd,List<IAdvertisement> adList){
        for(IAdvertisement loopAd: adList){
            if(loopAd.equals(newAd)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onEvent(EventBus.Event evt, Object o) {
        if(evt == EventBus.Event.CREATE_AD){
            view.setNewProfile((IProfile) o);
        }
    }
}
