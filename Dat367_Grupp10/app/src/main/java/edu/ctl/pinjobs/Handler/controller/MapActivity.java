package edu.ctl.pinjobs.handler.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.filips.dat367_grupp10.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

import edu.ctl.pinjobs.advertisement.controller.IOpenMapView;
import edu.ctl.pinjobs.advertisement.model.AndroidAdvertisement;
import edu.ctl.pinjobs.advertisement.model.IAdvertisement;
import edu.ctl.pinjobs.handler.model.AdvertisementListHolder;
import edu.ctl.pinjobs.handler.utils.HandlerLocationUtils;
import edu.ctl.pinjobs.handler.view.MapView;
import edu.ctl.pinjobs.utils.LocationUtils;

public class MapActivity extends ActionBarActivity implements GoogleMap.OnInfoWindowClickListener,
        IOpenMapView {

    private MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Saves the mapfragment(the view object) from .xml
        super.onCreate(savedInstanceState);

        if(AdvertisementListHolder.getInstance().getList().size()==0) {
            //Starts a loading screen if AdList is not loaded
            Intent intent = new Intent(getApplicationContext(), LoadingScreen.class);
            startActivityForResult(intent,1);
        }else{
            setUpMapView();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
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
        setUpMapView();
    }

    private void setUpMapView(){
        setContentView(R.layout.activity_maps);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        List<IAdvertisement> adList = AdvertisementListHolder.getInstance().getList();

        //uses different constructors depending on coming from create ad or mainwindow
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if(bundle.getParcelable("OLD_ADVERTISEMENT")!=null){
                //from update ad window
                System.out.println("I update ad");
                AndroidAdvertisement androidAd = bundle.getParcelable("Advertisement");
                IAdvertisement newAd = androidAd.getAd();
                AndroidAdvertisement oldAndroidAd = bundle.getParcelable("OLD_ADVERTISEMENT");
                IAdvertisement oldAd = oldAndroidAd.getAd();
                mapView = new MapView(this.getApplicationContext(),adList,mapFragment,newAd,this,oldAd);
            }
            //from create ad window
             else if(bundle.getParcelable("Advertisement")!=null) {
                AndroidAdvertisement androidAd = bundle.getParcelable("Advertisement");
                IAdvertisement ad = androidAd.getAd();
                mapView = new MapView(this.getApplicationContext(), adList, mapFragment, ad,this);
            }
        } else { //from mainwindow
            mapView = new MapView(this.getApplicationContext(), adList, mapFragment,this);
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        //this is done when clicked on a markers info window
        ListActivity handlerController = new ListActivity();
        IAdvertisement ad = mapView.getAdFromMarker(marker); //gets the correct ad that represents the marker
        LatLng currentPosition = LocationUtils.getCurrentLocation(this);
        String distance = "" + new HandlerLocationUtils().calculateDistanceFromPosition(currentPosition.latitude,
                ad.getLatitude(),currentPosition.longitude,ad.getLongitude());
        AndroidAdvertisement androidAD = new AndroidAdvertisement(ad);
        handlerController.openDetailedAdView(this.getApplicationContext(), androidAD, distance);
    }

    @Override
    public void startActivity(Context context, IAdvertisement newAd, AndroidAdvertisement oldAd) {
        Intent intent = new Intent(context, MapActivity.this.getClass());
        AndroidAdvertisement androidAD = new AndroidAdvertisement(newAd);
        intent.putExtra("Advertisement", androidAD);
        intent.putExtra("OLD_ADVERTISEMENT",oldAd);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        Toast.makeText(context, "Annons ändrad!", Toast.LENGTH_LONG).show();
    }
}
