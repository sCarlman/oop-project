package com.example.filips.dat367_grupp10;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class AdListActivity extends ActionBarActivity {
    private ListView adListView;
    private List<Advertisement> adList;
    private List<String> descriptionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_list);

        Database cloud = Database.getInstance();
        adListView = (ListView) findViewById(R.id.adListView);
        adList = cloud.getAdList();
        descriptionList = new ArrayList();
        for (int i = 0; i < adList.size(); ++i) {
            descriptionList.add(adList.get(i).getDescription());
        }
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,
                descriptionList);
        adListView.setAdapter(adapter);
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
