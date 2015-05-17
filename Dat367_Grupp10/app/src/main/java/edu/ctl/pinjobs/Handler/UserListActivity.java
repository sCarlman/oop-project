package edu.ctl.pinjobs.Handler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.filips.dat367_grupp10.R;

import java.util.List;

import edu.ctl.pinjobs.Advertisement.AndroidAdvertisement;
import edu.ctl.pinjobs.Advertisement.DetailedAdActivity;
import edu.ctl.pinjobs.Advertisement.IAdvertisement;
import edu.ctl.pinjobs.Advertisement.ModifyAdActivity;
import edu.ctl.pinjobs.Services.AdvertisementService;
import edu.ctl.pinjobs.Services.IAdvertisementService;
import edu.ctl.pinjobs.controller.UserModel;
import edu.ctl.pinjobs.profile.IProfile;

public class UserListActivity extends ActionBarActivity {

    IListModel listModel;
    edu.ctl.pinjobs.Handler.ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_list);
        String email = getIntent().getStringExtra("Email");
        IAdvertisementService adService = new AdvertisementService();


        List<IAdvertisement> adList = adService.fetchAdsOfAdvertiser(email);

        setListView(adList, email);
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
        setContentView(R.layout.activity_ad_list);
        this.listView = new ListView(this.getApplicationContext(),(android.widget.ListView)findViewById(R.id.adListView), email);
        this.listModel = new ListModel(adList);
    }

    public void openModifyAdView(Context context, AndroidAdvertisement ad){
        Intent intent = new Intent(this.getApplicationContext(), ModifyAdActivity.class);
        intent.putExtra("Advertisement", ad);
        startActivity(intent);
    }
}
