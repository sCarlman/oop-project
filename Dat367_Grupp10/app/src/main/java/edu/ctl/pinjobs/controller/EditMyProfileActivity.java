package edu.ctl.pinjobs.controller;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.filips.dat367_grupp10.R;

import edu.ctl.pinjobs.eventbus.EventBus;
import edu.ctl.pinjobs.profile.model.IProfile;
import edu.ctl.pinjobs.profile.model.WrongInputExeption;
import edu.ctl.pinjobs.profile.view.EditMyProfileView;

public class EditMyProfileActivity extends ActionBarActivity {

    private IProfile myProfile;
    private EditMyProfileView editMyProfileView;

    private boolean canceledByError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_profile);

        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();

        IProfile profile = (IProfile)bundle.getSerializable("sendProfile");
        this.myProfile = profile;

         editMyProfileView = new EditMyProfileView(profile, this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_my_profile, menu);
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

    public void saveNewInputToProfile(View view){
        setCanceledByError(false);
        try{
            myProfile.setFirstName(editMyProfileView.getTextFromEditFirstName());
        }catch (WrongInputExeption e){
            editMyProfileView.setErrorEditFirstName("Ogiltligt Förnamn!");
            setCanceledByError(true);
        }
        try{
            myProfile.setLastName(editMyProfileView.getTextFromEditLastName());
        }catch (WrongInputExeption e){
            editMyProfileView.setErrorEditLastName("Ogiltligt Efternamn!");
            setCanceledByError(true);
        }
        try{
            myProfile.setPhone(editMyProfileView.getTextFromEditPhone());
        }catch (WrongInputExeption e){
            editMyProfileView.setErrorEditPhone("Ogiltligt Tele!");
            setCanceledByError(true);
        }
        try{
            myProfile.setAddress(editMyProfileView.getTextFromEditAddress() + "," + editMyProfileView.getTextFromEditCity());
        }catch (WrongInputExeption e){
            //SKALL FIXAS!!!!!!
            editMyProfileView.setErrorEditAddress("Ogiltligt!");
            editMyProfileView.setErrorEditCity("Ogiltligt!");
            setCanceledByError(true);
        }
        if(!getCanceledByError()){
            EventBus.INSTANCE.publish(EventBus.Event.UPDATE_PROFILE, myProfile);
        }

        if(!canceledByError){
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("sendProfile", myProfile);
            intent.putExtras(bundle);
            setResult(2, intent);
            finish();
        }
    }

    public void setCanceledByError(boolean cancel){
        this.canceledByError = cancel;
    }

    public boolean getCanceledByError(){
        return canceledByError;
    }
}
