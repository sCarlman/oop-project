package edu.ctl.pinjobs.Advertisement;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.filips.dat367_grupp10.R;

public class ModifyAdActivity extends ActionBarActivity {

    private ModifyAdView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_ad);

        Bundle bundle = getIntent().getExtras();
        AndroidAdvertisement androidAd = (AndroidAdvertisement)bundle.getParcelable("Advertisement");
        IAdvertisement ad = androidAd.getAd();

        ModifyAdView view = new ModifyAdView((EditText)findViewById(R.id.titleEditText),
                (EditText)findViewById(R.id.descriptionEditText),(EditText)findViewById(R.id.nameEditText),
                (EditText)findViewById(R.id.addressEditText),(EditText)findViewById(R.id.phoneEditText),
                (EditText)findViewById(R.id.emailEditText),ad);
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
}
