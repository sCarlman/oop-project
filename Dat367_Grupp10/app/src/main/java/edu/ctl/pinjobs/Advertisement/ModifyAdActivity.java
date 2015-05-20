package edu.ctl.pinjobs.Advertisement;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.filips.dat367_grupp10.R;

import java.util.List;

import edu.ctl.pinjobs.Handler.AdvertisementListHolder;
import edu.ctl.pinjobs.eventbus.EventBus;

public class ModifyAdActivity extends ActionBarActivity {

    private ModifyAdView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_modify_ad);

        Bundle bundle = getIntent().getExtras();
        AndroidAdvertisement androidAd = bundle.getParcelable("Advertisement");
        IAdvertisement ad = androidAd.getAd();

        ModifyAdView view = new ModifyAdView((EditText)findViewById(R.id.modifyTitleEditText),
                (EditText)findViewById(R.id.modifyDesctriptionEditText),
                (EditText)findViewById(R.id.modifyAddressEditText),
                (Button)findViewById(R.id.modifyButton),(RadioButton)findViewById(R.id.modifyGardenRadioButton),
                (RadioButton)findViewById(R.id.modifyLabourRadioButton),
                (RadioButton)findViewById(R.id.modifyOtherRadioButton),ad);
        this.view = view;
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
    public void saveNewModifiedAd(IAdvertisement ad){
        EventBus.INSTANCE.publish(EventBus.Event.UPDATE_AD, ad);
    }

}
