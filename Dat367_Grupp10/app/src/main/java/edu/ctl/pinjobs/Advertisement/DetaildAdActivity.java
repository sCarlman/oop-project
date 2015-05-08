package edu.ctl.pinjobs.Advertisement;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.filips.dat367_grupp10.R;

import edu.ctl.pinjobs.Handler.Location;
import edu.ctl.pinjobs.Services.EventBus;
import edu.ctl.pinjobs.Advertisement.Advertisement;
import edu.ctl.pinjobs.profile.Profile;

/**
 * Created by filiplarsson on 15-05-07.
 */
public class DetaildAdActivity extends ActionBarActivity implements EventBus.IEventHandler{

    private  DetaildAdView view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_ad);

        DetaildAdView view = new DetaildAdView((TextView)findViewById(R.id.titleTextView),
                (TextView)findViewById(R.id.categoryTextView),
                (TextView)findViewById(R.id.distanceTextView),
                (TextView)findViewById(R.id.descriptionTextView),
                (TextView)findViewById(R.id.nameTextView),
                (TextView)findViewById(R.id.addressTextView),
                (TextView)findViewById(R.id.phoneTextView),
                (TextView)findViewById(R.id.mailTextView));
        this.view = view;


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detailed_ad, menu);
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

    }

    @Override
    public void onEvent(EventBus.Event evt, Object o) {

    }
}

