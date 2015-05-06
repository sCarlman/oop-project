package edu.ctl.pinjobs.Handler;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import edu.ctl.pinjobs.Services.AdvertisementService;
import edu.ctl.pinjobs.Services.EventBus;
import edu.ctl.pinjobs.Services.IAdListService;
import edu.ctl.pinjobs.Services.IAdvertisementService;
import edu.ctl.pinjobs.model.Advertisement;
import com.example.filips.dat367_grupp10.R;

import java.util.ArrayList;
import java.util.List;


public class HandlerActivity extends ActionBarActivity implements EventBus.IEventHandler {
    private ListView adListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        IAdvertisementService adService = new AdvertisementService();
        IListModel listModel = new ListModel(adService.fetchAllAds());
        ListView view = (ListView) View.inflate(this, R.layout.activity_ad_list, null);


    }

    @Override
    public void onEvent(EventBus.Event evt, Object o){
        if(evt == EventBus.Event.ADLIST_UPDATED) {
            setContentView(R.layout.activity_ad_list);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ad_list, menu);
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
