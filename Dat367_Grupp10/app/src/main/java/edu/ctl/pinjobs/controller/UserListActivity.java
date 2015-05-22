package edu.ctl.pinjobs.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.filips.dat367_grupp10.R;

import java.util.List;

import edu.ctl.pinjobs.advertisement.model.AndroidAdvertisement;
import edu.ctl.pinjobs.advertisement.model.IAdvertisement;
import edu.ctl.pinjobs.handler.model.AdvertisementListHolder;
import edu.ctl.pinjobs.handler.model.IListModel;
import edu.ctl.pinjobs.handler.model.ListModel;
import edu.ctl.pinjobs.handler.view.ListView;
import edu.ctl.pinjobs.utils.LocationUtils;
import edu.ctl.pinjobs.eventbus.EventBus;

public class UserListActivity extends ActionBarActivity implements EventBus.IEventHandler {

    IListModel listModel;
    ListView listView;
    String email;
    boolean isActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.INSTANCE.addListener(this);
        isActive = true;
        email = getIntent().getStringExtra("Email");

        if(AdvertisementListHolder.getInstance().getList().size()==0) {
            //starts progressbarView
            Intent intent = new Intent(getApplicationContext(), LoadingScreen.class);
            startActivityForResult(intent,1);
        }else {
            setListView(AdvertisementListHolder.getInstance().getAdvertiserAdsList(email), email);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_list, menu);
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

    private void setListView(List<IAdvertisement> adList, String email){
        //TODO: KAN HA FUCKAT UPP
        setContentView(R.layout.activity_ad_list);
        //this.listView = new ListView(getApplicationContext(),(android.widget.ListView)findViewById(R.id.adListView), email);
        this.listModel = new ListModel(adList);
        listModel.sortForDistance(LocationUtils.getCurrentLocation(this).latitude,LocationUtils.getCurrentLocation(this).longitude);
        listView.setupView(listModel.getList(), android.R.layout.simple_list_item_1);
    }

    //Fixa context skiten! samma problem som med handlerActivity
    public void openModifyAdView(Context context, AndroidAdvertisement ad){

        Intent intent = new Intent(context, ModifyAdActivity.class);
        intent.putExtra("Advertisement", ad);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void onEvent(EventBus.Event evt, Object o) {
        if(evt == EventBus.Event.ADLIST_UPDATED){
            AdvertisementListHolder.getInstance().setList((List<IAdvertisement>)o);
        }
    }
}
