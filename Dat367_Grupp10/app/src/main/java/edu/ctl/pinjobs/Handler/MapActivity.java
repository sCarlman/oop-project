package edu.ctl.pinjobs.Handler;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.filips.dat367_grupp10.R;
import com.google.android.gms.maps.MapFragment;

import java.util.List;

import edu.ctl.pinjobs.Advertisement.AndroidAdvertisement;
import edu.ctl.pinjobs.Advertisement.IAdvertisement;
import edu.ctl.pinjobs.Services.AdvertisementService;
import edu.ctl.pinjobs.Services.IAdvertisementService;
import edu.ctl.pinjobs.eventbus.EventBus;

public class MapActivity extends ActionBarActivity {

    private MapView mapView;
    private boolean isActive = false;
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
            //from create ad window
            if(bundle.getParcelable("Advertisement")!=null) {
                System.out.println("I CREATE AD");
                AndroidAdvertisement androidAd = (AndroidAdvertisement) bundle.getParcelable("Advertisement");
                IAdvertisement ad = androidAd.getAd();
                mapView = new MapView(this.getApplicationContext(), adList, mapFragment, ad);
            }else if(bundle.getParcelable("UpdateAdvertisement")!=null){
                System.out.println("I UPDATE AD");
            }
        } else { //from mainwindow
            mapView = new MapView(this.getApplicationContext(), adList, mapFragment);
        }
    }
}
