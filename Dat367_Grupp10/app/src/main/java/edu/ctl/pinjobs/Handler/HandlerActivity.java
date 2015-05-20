package edu.ctl.pinjobs.Handler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import edu.ctl.pinjobs.Advertisement.AndroidAdvertisement;
import edu.ctl.pinjobs.Advertisement.DetailedAdActivity;
import edu.ctl.pinjobs.Advertisement.ModifyAdActivity;
import edu.ctl.pinjobs.Services.AdvertisementService;
import edu.ctl.pinjobs.Utils.LocationUtils;
import edu.ctl.pinjobs.eventbus.EventBus;
import edu.ctl.pinjobs.Services.IAdvertisementService;
import edu.ctl.pinjobs.Advertisement.IAdvertisement;
import com.example.filips.dat367_grupp10.R;

import java.util.List;


public class HandlerActivity extends ActionBarActivity implements EventBus.IEventHandler {
    IListModel listModel;
    edu.ctl.pinjobs.Handler.ListView listView;
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
            setListView(AdvertisementListHolder.getInstance().getList(), email);
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
        setListView(AdvertisementListHolder.getInstance().getList(),email);
    }

    private void setListView(List<IAdvertisement> adList, String email){
        setContentView(R.layout.activity_ad_list);
        this.listView = new ListView(this.getApplicationContext(),(android.widget.ListView)findViewById(R.id.adListView), email);
        this.listModel = new ListModel(adList);
        listModel.sortForDistance(LocationUtils.getCurrentLocation(this));
        listView.setupView(listModel.getList(), android.R.layout.simple_list_item_1);

    }

    public void openDetailedAdView(Context context, AndroidAdvertisement ad,String distance){

        Intent intent = new Intent(context, DetailedAdActivity.class);
        intent.putExtra("Advertisement", ad);
        intent.putExtra("Distance", distance);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity(intent);
    }


    @Override
    public void onEvent(EventBus.Event evt, Object o) {
        if(evt == EventBus.Event.ADLIST_UPDATED){
            AdvertisementListHolder.getInstance().setList((List<IAdvertisement>)o);
        }
    }
}
