package edu.ctl.pinjobs.Handler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import edu.ctl.pinjobs.Advertisement.Advertisement;
import edu.ctl.pinjobs.Advertisement.AndroidAdvertisement;
import edu.ctl.pinjobs.Advertisement.DetailedAdActivity;
import edu.ctl.pinjobs.Services.AdvertisementService;
import edu.ctl.pinjobs.Services.EventBus;
import edu.ctl.pinjobs.Services.IAdvertisementService;
import edu.ctl.pinjobs.Advertisement.IAdvertisement;
import com.example.filips.dat367_grupp10.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.List;
import java.util.logging.Handler;


public class HandlerActivity extends ActionBarActivity implements EventBus.IEventHandler {
    private static Context cont;
    IListModel listModel;
    edu.ctl.pinjobs.Handler.ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        cont = this.getApplicationContext();

        EventBus.INSTANCE.addListener(this);
        IAdvertisementService adService = new AdvertisementService();
        List<IAdvertisement> adList = adService.fetchAllAds();
        setListView(adList);
    }

    @Override
    public void onEvent(EventBus.Event evt, Object o){
        if(evt == EventBus.Event.ADLIST_UPDATED) {
            //Starts the view with the list from model
            listView.setupView((List<IAdvertisement>) o, android.R.layout.simple_list_item_1);
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

    private void setListView(List<IAdvertisement> adList){
        setContentView(R.layout.activity_ad_list);
        this.listView = new ListView(this,(android.widget.ListView)findViewById(R.id.adListView));
        this.listModel = new ListModel(adList);
    }

    public void openDetailedAdView(View view, AndroidAdvertisement ad){

        //Bundle bundle = new Bundle();

        Intent intent = new Intent(cont.getApplicationContext(), DetailedAdActivity.class);
        intent.putExtra("Advertisement", ad);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        cont.getApplicationContext().startActivity(intent);

    }

}
