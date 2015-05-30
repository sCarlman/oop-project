package edu.ctl.pinjobs.profile.controller;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.filips.dat367_grupp10.R;

import edu.ctl.pinjobs.advertisement.service.IAdvertisementService;
import edu.ctl.pinjobs.profile.model.IProfile;
import edu.ctl.pinjobs.profile.model.IUserModel;
import edu.ctl.pinjobs.profile.service.IProfileService;
import edu.ctl.pinjobs.profile.view.MyProfileView;
import edu.ctl.pinjobs.profile.model.UserModel;

public class MyProfileActivity extends ActionBarActivity implements View.OnClickListener{

    private MyProfileView myProfileView;
    private IProfile myProfile;
    private String email;
    private IProfileService profileService;
    private IOpenListView iOpenListView;
    private Object iOpenMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null) {
            myProfile = (IProfile) bundle.getSerializable("sendProfile");
            profileService=(IProfileService)bundle.getSerializable("PROFILE_SERVICE");
            iOpenListView = (IOpenListView) bundle.getSerializable("IOPENLISTVIEW");
            iOpenMapView = bundle.getSerializable("OPEN_MAP_VIEW");
        }
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
                bundle.putSerializable("PROFILE_SERVICE",profileService);
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
        //TODO: borde inte se ut såhär...
        iOpenListView.openListViewForEmail(this, email, iOpenMapView);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(data !=null) {
            myProfile = (IProfile) data.getSerializableExtra("SEND_BACK_PROFILE");
            myProfileView.setProfileInfoOnCreate(myProfile);
            IUserModel userModel = UserModel.getInstance();
            userModel.logIn(myProfile);

        }
    }


}
