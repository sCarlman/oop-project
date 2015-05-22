package edu.ctl.pinjobs.controller;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.filips.dat367_grupp10.R;

import edu.ctl.pinjobs.eventbus.EventBus;
import edu.ctl.pinjobs.profile.model.IProfile;
import edu.ctl.pinjobs.profile.view.MyProfileView;

public class MyProfileActivity extends ActionBarActivity implements View.OnClickListener{

    private MyProfileView myProfileView;
    private IProfile myProfile;
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();

        myProfile = (IProfile)bundle.getSerializable("sendProfile");
        email = myProfile.getEmail();

        myProfileView = new MyProfileView(this, myProfile, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_my_profile, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_edit:
                Intent intent = new Intent(getApplicationContext(), EditMyProfileActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("sendProfile", myProfile);
                intent.putExtras(bundle);

                startActivityForResult(intent, 1);
                return true;
            case R.id.action_settings:
                //open setings method
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        //EventBus.INSTANCE.publish(EventBus.Event.SHOW_MY_ADS, email);
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        intent.putExtra("myList", true);
        intent.putExtra("Email", email);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(data !=null) {
            this.myProfile = (IProfile) data.getSerializableExtra("sendProfile");
            myProfileView.setProfileInfoOnCreate(myProfile);
        }
    }


}