package edu.ctl.pinjobs.Advertisement;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.filips.dat367_grupp10.R;

public class DetailedAdActivity extends ActionBarActivity {

    private DetailedAdView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_ad);

        Bundle bundle = getIntent().getExtras();

        Advertisement ad = (Advertisement)bundle.getParcelable("Advertisement");
        DetailedAdView view = new DetailedAdView((TextView)findViewById(R.id.titleTextView),
                (TextView)findViewById(R.id.categoryTextView),
                        (TextView)findViewById(R.id.distanceTextView),
                (TextView)findViewById(R.id.descriptionTextView),
                (TextView)findViewById(R.id.nameTextView),
                (TextView)findViewById(R.id.addressTextView),
                (TextView)findViewById(R.id.phoneTextView),
                (TextView)findViewById(R.id.emailTextView),
                ad);
        this.view = view;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detailed_ad, menu);
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
