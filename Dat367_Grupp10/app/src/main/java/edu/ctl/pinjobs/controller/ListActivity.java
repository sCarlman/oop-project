package edu.ctl.pinjobs.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import edu.ctl.pinjobs.advertisement.model.Advertisement;
import edu.ctl.pinjobs.advertisement.model.AndroidAdvertisement;
import edu.ctl.pinjobs.handler.model.AdvertisementListHolder;
import edu.ctl.pinjobs.handler.model.IListModel;
import edu.ctl.pinjobs.handler.model.ListModel;
import edu.ctl.pinjobs.handler.utils.HandlerLocationUtils;
import edu.ctl.pinjobs.handler.view.ListView;
import edu.ctl.pinjobs.utils.LocationUtils;
import edu.ctl.pinjobs.eventbus.EventBus;
import edu.ctl.pinjobs.advertisement.model.IAdvertisement;
import com.example.filips.dat367_grupp10.R;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;


public class ListActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {
    IListModel listModel;
    ListView listView;
    String email;
    HandlerLocationUtils locationUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.locationUtils = new HandlerLocationUtils();
        email = getIntent().getStringExtra("Email");

        Boolean myList = getIntent().getBooleanExtra("myList", false);

        if(AdvertisementListHolder.getInstance().getList().size()==0) {
            //starts progressbarView
            Intent intent = new Intent(getApplicationContext(), LoadingScreen.class);
            startActivityForResult(intent,1);
        }else {
            if(myList==true){
                //Creates a MyAdsView
                setListView(AdvertisementListHolder.getInstance().getAdvertiserAdsList(email));
            }else{
                //Creates a ListView
                setListView(AdvertisementListHolder.getInstance().getList());
            }

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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        //Happends when returned from progressBarView
        if(resultCode == 2) {
            setListView(AdvertisementListHolder.getInstance().getList());
            Toast.makeText(getApplicationContext(), "Finns inga anonser uppe för tillfället",
                    Toast.LENGTH_LONG).show();
        }else {
            setListView(AdvertisementListHolder.getInstance().getList());
        }

    }

    private void setListView(List<IAdvertisement> adList){
        setContentView(R.layout.activity_ad_list);
        this.listView = new ListView(this.getApplicationContext(),(android.widget.ListView)findViewById(R.id.adListView),this);
        this.listModel = new ListModel(adList);
        listModel.sortForDistance(LocationUtils.getCurrentLocation(this).latitude,LocationUtils.getCurrentLocation(this).longitude);
        listView.setupView(listModel.getList(), android.R.layout.simple_list_item_1);

    }


    public void openDetailedAdView(Context context, AndroidAdvertisement ad,String distance){
        Intent intent = new Intent(context, DetailedAdActivity.class);
        intent.putExtra("Advertisement", ad);
        intent.putExtra("Distance", distance);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity(intent);
    }

    public void openModifyAdView(Context context, AndroidAdvertisement ad){
        Intent intent = new Intent(context, ModifyAdActivity.class);
        intent.putExtra("Advertisement", ad);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //For onclick on the specified item in the list.
        final LatLng currentPosition = LocationUtils.getCurrentLocation(this);
        IAdvertisement ad = listModel.getList().get(position);
        String adDistance = "" + locationUtils.calculateDistanceFromPosition(currentPosition.latitude,
                ad.getLatitude(), currentPosition.longitude, ad.getLongitude());
        AndroidAdvertisement androidAD = new AndroidAdvertisement(ad);

        if (email.equals(ad.getAdvertiser().getEmail())) {
            openModifyAdView(this, androidAD);
        } else {
            openDetailedAdView(this, androidAD, adDistance);
        }
    }
}
