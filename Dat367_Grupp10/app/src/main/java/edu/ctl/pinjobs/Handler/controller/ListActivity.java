package edu.ctl.pinjobs.handler.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import edu.ctl.pinjobs.advertisement.service.IAdvertisementService;
import edu.ctl.pinjobs.advertisement.controller.IOpenMapView;
import edu.ctl.pinjobs.advertisement.model.AndroidAdvertisement;
import edu.ctl.pinjobs.advertisement.controller.DetailedAdActivity;
import edu.ctl.pinjobs.advertisement.controller.ModifyAdActivity;
import edu.ctl.pinjobs.handler.model.AdvertisementListHolder;
import edu.ctl.pinjobs.handler.model.IListModel;
import edu.ctl.pinjobs.handler.model.ListModel;
import edu.ctl.pinjobs.handler.utils.HandlerLocationUtils;
import edu.ctl.pinjobs.handler.view.ListView;
import edu.ctl.pinjobs.profile.controller.IOpenListView;
import edu.ctl.pinjobs.utils.LocationUtils;
import edu.ctl.pinjobs.advertisement.model.IAdvertisement;
import com.example.filips.dat367_grupp10.R;
import com.google.android.gms.maps.model.LatLng;
import com.parse.ParseException;

import java.util.List;


public class ListActivity extends ActionBarActivity implements AdapterView.OnItemClickListener,
        IOpenListView, SwipeRefreshLayout.OnRefreshListener {
    private IListModel listModel;
    private ListView listView;
    private String email;
    private HandlerLocationUtils locationUtils;
    private IOpenMapView iOpenMapView;
    private IAdvertisementService adService;
    private boolean isMyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.locationUtils = new HandlerLocationUtils();
        email = getIntent().getExtras().getString("Email");
        adService = (IAdvertisementService) getIntent().getExtras().getSerializable("AD_SERVICE");
        iOpenMapView = (IOpenMapView) getIntent().getExtras().getSerializable("OPEN_MAP_VIEW");
        System.out.println(iOpenMapView);

        isMyList = getIntent().getExtras().getBoolean("myList", false);

        if (AdvertisementListHolder.getInstance().getList().size() == 0) {
            //starts progressbarView
            Intent intent = new Intent(getApplicationContext(), LoadingScreen.class);
            startActivityForResult(intent, 1);
        } else {
            if (isMyList) {
                //Creates a MyAdsView
                setListView(AdvertisementListHolder.getInstance().getAdvertiserAdsList(email));
            } else {
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Happens when returned from progressBarView
        if (resultCode == 2) {
            setListView(AdvertisementListHolder.getInstance().getList());
            Toast.makeText(getApplicationContext(), "Finns inga anonser uppe för tillfället",
                    Toast.LENGTH_LONG).show();
        } else {
            setListView(AdvertisementListHolder.getInstance().getList());
        }

    }

    private void setListView(List<IAdvertisement> adList) {
        setContentView(R.layout.activity_ad_list);
        this.listView = new ListView(this, this, this);
        this.listModel = new ListModel(adList);
        listModel.sortForDistance(LocationUtils.getCurrentLocation(this).latitude, LocationUtils.getCurrentLocation(this).longitude);
        listView.setupView(listModel.getList(), android.R.layout.simple_list_item_1);

    }


    public void openDetailedAdView(Context context, AndroidAdvertisement ad, String distance) {
        Intent intent = new Intent(context, DetailedAdActivity.class);
        intent.putExtra("Advertisement", ad);
        intent.putExtra("Distance", distance);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity(intent);
    }

    public void openModifyAdView(Context context, AndroidAdvertisement ad) {
        Intent intent = new Intent(context, ModifyAdActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("AD_SERVICE", adService);
        bundle.putSerializable("OPEN_MAP_VIEW", iOpenMapView);
        intent.putExtra("Advertisement", ad);
        intent.putExtras(bundle);
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

        if (email == null) {
            openDetailedAdView(this, androidAD, adDistance);
        } else if (email.equals(ad.getAdvertiser().getEmail())) {
            openModifyAdView(this, androidAD);
        } else {
            openDetailedAdView(this, androidAD, adDistance);
        }

    }

    @Override
    public void openListViewForEmail(Context context, String email, Object map) {
        Intent intent = new Intent(context, ListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("OPEN_MAP_VIEW", (IOpenMapView) map);
        bundle.putString("Email", email);
        bundle.putBoolean("myList", true);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public void onRefresh() {
        if (isMyList) {
            //Creates a MyAdsView
            setListView(adService.fetchAdsOfAdvertiser(email));
            AdvertisementListHolder.getInstance().setList(adService.fetchAdsOfAdvertiser(email));
        } else {
            //Creates a ListView
            try {
                setListView(adService.fetchAllAds());
                AdvertisementListHolder.getInstance().setList(adService.fetchAllAds());
            } catch (ParseException e) {
                finish();
            }
        }
        listView.setRefreshing(false);
    }
}

